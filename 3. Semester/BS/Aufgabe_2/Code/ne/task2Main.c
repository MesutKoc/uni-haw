/*|------------------------------------------------------------
  | Unsere Libarys die wir nutzen
  |------------------------------------------------------------*/
#include <unistd.h> //  wegen POSIX operating system API.
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <sched.h>
#include <termios.h>
#include "task2Main.h" // Header-Datei 
/*|------------------------------------------------------------
  | Unsere Konstanten
  |------------------------------------------------------------*/
#define MAX_THREADS 4		// Threads Größe
#define MAX 	    16	    // Buffer-Memory Größe

/*|------------------------------------------------------------
  | Wir erstellen hier unsere Mutex Variablen.
  |------------------------------------------------------------*/
pthread_mutex_t rb_mutex   		   = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t ctrl_mutex 		   = PTHREAD_MUTEX_INITIALIZER;

/*|------------------------------------------------------------
  | Wir erstellen hier unsere Condition Variablen.
  |------------------------------------------------------------*/
pthread_cond_t not_empty_condvar   = PTHREAD_COND_INITIALIZER;
pthread_cond_t not_full_condvar    = PTHREAD_COND_INITIALIZER;
pthread_cond_t consumer_restart    = PTHREAD_COND_INITIALIZER;
pthread_cond_t proc1_signal 	   = PTHREAD_COND_INITIALIZER;
pthread_cond_t proc2_signal 	   = PTHREAD_COND_INITIALIZER;
pthread_cond_t not_running_condvar = PTHREAD_COND_INITIALIZER;

/*|------------------------------------------------------------
  | Eindeutige identifizierung vom Thread durch Indizies.
  |------------------------------------------------------------*/
int thread_id[MAX_THREADS] = {0, 1, 2, 3};

/*|-------------------------------------------------------------------
  | 0 -> Prozess läuft | 1 -> Prozess läuft nicht || Same for Consumer
  |-------------------------------------------------------------------*/
int p1_stop = 0,
    p2_stop = 0,
    consumer_stop = 0,
    sleep_consumer = 2,
    running = 1;

/*|------------------------------------------------------------
  | Datenstruktur vom Ringpuffer
  |------------------------------------------------------------*/
typedef struct {
    int buffer[MAX];
    int *p_in;
    int *p_out;
    int count;
 }rb;
 rb x = { {0}, NULL, NULL, 0 }; // Leerer Ringpuffer 
 rb *p_rb = &x;					// Adresse auf den Ringpuffer

#define p_start  (int *)(p_rb -> buffer) 			// Pointer auf erstes Element im Puffer
#define p_end 	(int *)((p_rb -> buffer) + MAX-1)  // Pointer auf letztes Element im Puffer

/*|------------------------------------------------------------
  | Funktion: p_1_w
  | Description: ruft producing auf
  | @param pid bzw. Prozess-ID
  | @return NULL
  |------------------------------------------------------------*/
void* p_1_w(void *pid) {
	return (void *)producing(pid, &proc1_signal, &p1_stop, 'a', 'z', 3);
}

/*|------------------------------------------------------------
  | Funktion: p_2_w
  | Description: ruft producing mit Großbuchstaben auf
  | @param pid bzw. Prozess-ID
  | @return NULL
  |------------------------------------------------------------*/
void* p_2_w(void *pid) {
	return (void *)producing(pid, &proc2_signal, &p2_stop, 'A', 'Z', 3);
}


/*|------------------------------------------------------------
  | Funktion: producing
  | Description: Produziert 3 Sekunden lang Zeichen in den RP
  | @param restart, prod_stopped_ a_begin_ a_end, sleep_time
  | @return NULL
  |------------------------------------------------------------*/
void* producing(void *pid, pthread_cond_t *restart, int *prod_stopped, char a_begin, char a_end, int sleep_time) {
	int i = 0;
	int z_var = a_begin - 1;
	/*|------------------------------------------------------------
	  | Debug-Message, dass Producer startet
      |------------------------------------------------------------*/
	printf("Producer started; %d: \n", *(int*)pid);

	while(1){
		i++; // Zähl
		while(*prod_stopped){
			printf("Producer %d: -> Unlock Producer!\n",*(int*)pid);
            pthread_cond_wait(restart, &ctrl_mutex);
            if(running == 0) return (NULL);
        }
        z_var++; // nächstes Zeichen setzen
		/*|------------------------------------------------------------
		  | Sichern bei jedem Sprung, solange z_var größer ist, 
		  | auf Anfangswert zu setzen
		  |------------------------------------------------------------*/
		if (z_var > a_end) z_var = a_begin;
		/*|------------------------------------------------------------
		  | Mutex Locken
		  |------------------------------------------------------------*/
		pthread_mutex_lock(&rb_mutex);
		/*|------------------------------------------------------------
		  | Ist der Ringpuffer voll oder der Producer wurde angehalten?
		  |------------------------------------------------------------*/
		while((p_rb -> p_in == p_rb -> p_out && p_rb -> count == MAX) || *prod_stopped) {
			// Ist der Speicher voll?
			if (p_rb -> p_in == p_rb -> p_out && p_rb -> count == MAX) {
				printf("Producer -> %d: RingBuffer is full.\n", *(int*)pid);
				pthread_cond_wait(&not_full_condvar, &rb_mutex);
				printf("Producer -> %d: RingBuffer not longer full.\n", *(int*)pid);
			}
			// Ist der Producer angehalten wurden?
			else if(*prod_stopped) {
				printf("Producer -> %d: Stopped.\n", *(int*)pid);
				pthread_cond_wait(restart, &ctrl_mutex);
				printf("Producer -> %d: Started.\n", *(int*)pid);
			}
		}
		/*|------------------------------------------------------------
		  | In den Puffer schreiben
		  |------------------------------------------------------------*/
		printf("Producer -> %d: Write '%c' to Ringbuffer.\n", *(int*)pid, z_var);
		*(p_rb -> p_in) = z_var; // Setze in Inhalt der Adresse auf z_var
		/*|------------------------------------------------------------
		  | Inputzeiger muss vorgeschoben werden
		  |------------------------------------------------------------*/
		(p_rb -> p_in)++; // die Adresse inkrementieren wir um 4, weil int vier zeichen (chars ein zeichen)
		if( (p_rb -> p_in) > p_end) p_rb -> p_in = p_start;
		/*|------------------------------------------------------------
		  |Puffer erhöhen
		  |------------------------------------------------------------*/
		(p_rb -> count)++;
		/*|------------------------------------------------------------
		  |Signal an den consumer, dass der Puffer gefüllt ist
		  |------------------------------------------------------------*/
		if(p_rb -> count != 0) pthread_cond_signal(&not_empty_condvar);
		/*|------------------------------------------------------------
		  | Mutex freigeben und Thread 3 Sekunden anhalten
		  |------------------------------------------------------------*/
		pthread_mutex_unlock(&rb_mutex);
		sleep(sleep_time);
	}
	return NULL;
}

/*|------------------------------------------------------------
  | Funktion: get_char_without_echo
  | Description: Für die Eingabe bei der Methode control()
  | Lösungside: http://stackoverflow.com/questions/7469139/what-is-equivalent-to-getch-getche-in-linux 
  | @return char
  |------------------------------------------------------------*/
char get_char() {
	static struct termios old, new;
	char result;

	tcgetattr(0, &old); // grab old terminal i/o settings
    new = old; // make new settings same as old settings
    new.c_lflag &= ~ICANON; // disable buffered i/o
    new.c_lflag &= ~ECHO; // set echo mode
    tcsetattr(0, TCSANOW, &new); // use these new terminal i/o settings now
    result = getchar();
    tcsetattr(0, TCSANOW, &old);
    return result;
}

/*|------------------------------------------------------------
  | Funktion: control
  | Description: Für die Kontrolle des Programms
  | @param pid
  | @return 
  |------------------------------------------------------------*/
void *control(void *pid) {
	char input;
	pthread_mutex_lock(&ctrl_mutex);

	while(running) {
		input = get_char();
		switch (input) {
			case '1':
					 if(p1_stop) {
					    pthread_cond_signal(&proc1_signal);
						p1_stop = 0;
					  } else { p1_stop = 1; }
					  break;

			case '2': if(p2_stop) {
						pthread_cond_signal(&proc2_signal);
						p2_stop = 0;
					  } else{ p2_stop = 1; }
					  break;
			case 'c':
			case 'C': if(consumer_stop){
					    pthread_cond_signal(&consumer_restart);
					    consumer_stop = 0;
					  } else{ consumer_stop = 1; }
					  break;
			case 'q':
			case 'Q': running = 0;
				  	  pthread_cond_signal(&proc1_signal);
				  	  pthread_cond_signal(&proc2_signal);
				  	  pthread_cond_signal(&consumer_restart);
				  	  pthread_cond_signal(&not_running_condvar);
				  	  break;
			case 'h': printf("\nHilfe für die Tastatureingabe\n");
					  printf("\n| Taste | ---------------------------\n");
					  printf("\n--- 1 	  --- Start/Stopp Producer_1 ---\n");
					  printf("\n--- 2 	  --- Start/Stopp Producer_2 ---\n");
					  printf("\n--- c|C   --- Start/Stopp Consumer ---\n");
					  printf("\n--- q|Q   --- Terminiert alle Threads ---\n");
					  printf("\n--- h     --- Ausgabe der Hilfe ---\n");
					  break;
			default: ;
		}
	}
	pthread_mutex_unlock(&ctrl_mutex);
}

/*|------------------------------------------------------------
  | Funktion: consumer
  | Description: 
  | @param pid
  | @return 
  |------------------------------------------------------------*/
void* consumer(void *pid) {
	int i = 0;

	while(1) {
		i++;
		while(consumer_stop) {
			printf("\nRingbuffer -> unlock consumer \n");
			pthread_cond_wait(&consumer_restart, &ctrl_mutex);
        }
        if(running == 0) return (NULL);
		pthread_mutex_lock(&rb_mutex); // lock mutext
		while(p_rb -> count == 0 || consumer_stop) { // Die Prüfung hier, ob der Ringpuffer voll ist oder nicht
			if(p_rb -> count == 0) { // Der Fall: RP leer
				printf("Method -> consumer: RingBuffer is empty.\n");
				pthread_cond_wait(&not_empty_condvar, &rb_mutex);
				printf("Method -> consumer: RingBuffer is not longer empty.\n");
			}
			else if(consumer_stop) { // Der Fall: Consumer wurde gestoppt vom User
				printf("Method -> consumer: Stopped by User.\n");
				pthread_cond_wait(&consumer_restart, &rb_mutex);
				printf("Method -> consumer: Started by User.\n");
			}
		}

		(p_rb -> count)--; // Dekrementieren
		// Prüfen, ob das das 30 Zeichen war?
		if(i%30 == 0) {
            printf("%c \n", *(p_rb->p_out));
        } else {
	        printf("%c", *(p_rb->p_out));
            fflush(stdout); // cursor im output bleibt auf gleicher Zeile
        }
		(p_rb -> p_out)++; // Zeiger erhöhen
		if(p_rb -> p_out > p_end) p_rb -> p_out = p_start; // Sicher gehen, dass immer Start ist
		if(p_rb -> count <= MAX) pthread_cond_signal(&not_full_condvar); // Signalisieren, dass RB nicht voll ist! Sehr wichtig
		pthread_mutex_unlock(&rb_mutex); // Mutex freigeben 
	    sleep(sleep_consumer); // sleep consumer = 2
 	}
}

/*|------------------------------------------------------------
  | main
  |------------------------------------------------------------*/
int main(int argc, char* argv[]){
    int i;	// Zählvariable
    void *result;
    pthread_t threads[4]; // Erstellen von Threads

    printf("Start des Beispiels: \n");

	p_rb -> p_in = p_start; // Setzen von p_im Pointer im Ringpuffer auf p_start
	p_rb -> p_out = p_start; // Setzen von p_out Pointer im Ringpuffer auf p_start
	p_rb -> count = 0;	// Setzen vom con im Ringpuffer auf 0

	printf("Counter value %d\n", p_rb ->count); // Zählen

	// Threads werden gestartet
	pthread_create(&threads[0], NULL, &control, (void *)&thread_id[0]);
	pthread_create(&threads[1], NULL, &p_1_w,   (void *)&thread_id[1]);
	pthread_create(&threads[2], NULL, &p_2_w,   (void *)&thread_id[2]);
	pthread_create(&threads[3], NULL, &consumer,(void *)&thread_id[3]);
	pthread_cond_wait(&not_running_condvar, &ctrl_mutex);


	// Prüfen, ob Termin ierung bzw. Join klappt
    for(i = 0; i < 4; i++){
	    pthread_cancel(threads[i]);
		result = pthread_join(threads[i], NULL);
		if(result == NULL) printf("Thread canceled %d\n", i);
		else 		       printf("Thread not canceled %d\n", i);
		// end
		printf("End after joining Threads %d\n", i );
    }
	pthread_cancel(threads[0]);
	return 0;
}