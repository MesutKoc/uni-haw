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
pthread_mutex_t ctrl_mutex   	   = PTHREAD_MUTEX_INITIALIZER;

/*|------------------------------------------------------------
  | Wir erstellen hier unsere Condition Variablen.
  |------------------------------------------------------------*/
pthread_cond_t not_empty_condvar   = PTHREAD_COND_INITIALIZER;
pthread_cond_t not_full_condvar    = PTHREAD_COND_INITIALIZER;
pthread_cond_t consumer_restart    = PTHREAD_COND_INITIALIZER;
pthread_cond_t proc1_signal 	   = PTHREAD_COND_INITIALIZER;
pthread_cond_t proc2_signal 	   = PTHREAD_COND_INITIALIZER;
pthread_cond_t read_condvar 	   = PTHREAD_COND_INITIALIZER;
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
	int z_var = a_begin - 1;

	 while(1){
	 	/* Begin critical section */
        while(!*prod_stopped){
	        printf("Producer %d: -> Unlock Producer!\n",*(int*)pid);
            pthread_cond_wait(restart, &ctrl_mutex);
            if(running == 0) return (NULL);
        }
        z_var++; //nächstes Zeichen
        if (z_var > a_end) z_var = a_begin; //nicht über Zirkel ausschreiten
        pthread_mutex_lock(&rb_mutex); // mutex locken
        while(p_rb ->count >= MAX-1) { // gucken, ob rb voll ist
            printf("Producer %d: Ringbuffer is Full\n",  *(int*)pid);
            pthread_cond_wait(&not_full_condvar, &rb_mutex);
            printf("Producer %d: count %d\n", *(int*)pid, p_rb -> count);
        }
        printf("Producer %d is writing in Ringbuffer: %c\n",*(int*)pid, z_var);
        *(p_rb -> p_in) = z_var;  //p_rb->p_in ist Adresse *(p_rb->p_in) ist Inhalt der Adresse, und dieser wird auf z_var gesetzt
        (p_rb -> p_in)++; // die Adresse wird incrementiert -> hier um 4, weil int vier zeichen (chars ein zeichen)
        if( (p_rb -> p_in) > p_end) p_rb -> p_in = p_start; //Zirkel garantieren
        (p_rb -> count)++; // count
        if(p_rb -> count > 0) pthread_cond_signal(&not_empty_condvar);
        pthread_mutex_unlock(&rb_mutex);
        sleep(3);
    }
    return (NULL);
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
			case '1': p1_stop = (p1_stop == 0) ? 1 : 0;
					  printf("p1_stop = %d\n",p1_stop);
					  pthread_cond_signal(&proc1_signal);
					  break;
			case '2': p2_stop = (p2_stop == 0) ? 1 : 0;
					  printf("p2_stop = %d\n",p2_stop);
					  pthread_cond_signal(&proc2_signal);
					  break;
			case 'c':
			case 'C': consumer_stop = (consumer_stop == 0) ? 1 : 0;
					  printf("consumer = %d\n",consumer_stop);
					  pthread_cond_signal(&read_condvar);
					  break;
			case 'q':
			case 'Q': printf("Alle laufenden Threads beenden!\n");
					  running = 0;
					  p1_stop = 1;
					  p2_stop = 1;
					  consumer_stop = 1;
					  pthread_cond_signal(&proc1_signal);
					  pthread_cond_signal(&proc2_signal);
					  pthread_cond_signal(&read_condvar);
					  pthread_cond_signal(&not_running_condvar);
					  break;
			case 'h': printf("\nHilfe für die Tastatureingabe\n");
					  printf("\n| Taste | ---------------------------\n");
					  printf("\n--- 1	  --- Start/Stopp Producer_1 ---\n");
					  printf("\n--- 2	  --- Start/Stopp Producer_2 ---\n");
					  printf("\n--- c|C   --- Start/Stopp Consumer ---\n");
					  printf("\n--- q|Q   --- Terminiert alle Threads ---\n");
					  printf("\n--- h     --- Ausgabe der Hilfe ---\n");
					  break;
			default: break;
		}
		pthread_mutex_unlock( &ctrl_mutex);
    }
	return (NULL);
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
  | Funktion: consumer
  | Description: 
  | @param pid
  | @return 
  |------------------------------------------------------------*/
void* consumer(void *pid){
	while(1){
		/* Begin critical section */
        while(!consumer_stop){
            printf("\nRingbuffer -> unlock consumer \n");
            pthread_cond_wait(&read_condvar, &ctrl_mutex);
        }
        if(running == 0) return (NULL);
		pthread_mutex_lock( &rb_mutex);

	 	while(p_rb -> count < 1) {
            printf("\nRingbuffer -> is Empty \n");
            pthread_cond_wait(&not_empty_condvar, &rb_mutex);
        }
        printf("Ringbuffer -> Output:  %c:\n", (char)*(p_rb -> p_out)); //p_out
        printf("\n");
        (p_rb -> count)--;
        (p_rb -> p_out)++;
        if((p_rb -> p_out) > p_end) p_rb -> p_out = p_start;
        //  (<=) weniger     ==0
        if(p_rb -> count < MAX) pthread_cond_signal(&not_full_condvar);
		/* End critical section */
		pthread_mutex_unlock(&rb_mutex);
        sleep(sleep_consumer);
    }
    return(NULL);
}
/*|------------------------------------------------------------
  | main
  |------------------------------------------------------------*/
int main(int argc, char* argv[]){
    int i;	// Zählvariable
    pthread_t threads[4]; // Erstellen von Threads
    void *result;

    printf("Starting the Main: \n");

	p_rb -> p_in = p_start; // Setzen von p_im Pointer im Ringpuffer auf p_start
	p_rb -> p_out = p_start; // Setzen von p_out Pointer im Ringpuffer auf p_start
	p_rb -> count = 0;	// Setzen vom con im Ringpuffer auf 0

	//printf("Counter value %d\n", p_rb ->count); // Zählen

	// Threads werden gestartet
	int t1 = pthread_create(&threads[0], NULL, &control, (void *)&thread_id[0]);
	pthread_create(&threads[1], NULL, &p_1_w,   (void *)&thread_id[1]);
	pthread_create(&threads[2], NULL, &p_2_w,   (void *)&thread_id[2]);
	pthread_create(&threads[3], NULL, &consumer,(void *)&thread_id[3]);
	pthread_cond_wait(&not_running_condvar, &ctrl_mutex);


	// Prüfen, ob Terminierung bzw. Join klappt
    for(i = 1; i < 4; i++){
	    printf("CANCEL THREAD %d\n",i);
		pthread_cancel(threads[i]);
		result = (void *)pthread_join(threads[i], NULL);
		if(result == NULL) printf("Thread canceled %d\n", i);
        else 			   printf("Thread not canceled %d\n", i);
        printf("End after joining Threads %d\n", i );
    }
    pthread_cancel(threads[0]);
	return 0;
}