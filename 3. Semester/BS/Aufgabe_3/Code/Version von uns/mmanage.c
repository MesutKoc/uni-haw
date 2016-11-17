/* Description: Memory Manager BSP3*/
/* Prof. Dr. Wolfgang Fohl, HAW Hamburg */
/* Winter 2010/2011
 * 
 * This is the memory manager process that
 * works together with the vmaccess process to
 * mimic virtual memory management.
 *
 * The memory manager process will be invoked
 * via a SIGUSR1 signal. It maintains the page table
 * and provides the data pages in shared memory
 *
 * This process is initiating the shared memory, so
 * it has to be started prior to the vmaccess process
 *
 * :
 * currently nothing
 * */

#include "mmanage.h"

#define handle_error(msg) do { perror(msg); exit(EXIT_FAILURE); } while (0)
#define RAGE_FOR_RANDOM 1000

#ifndef DEBUG_MESSAGES
#define DEBUG(A)
#endif

#ifdef DEBUG_MESSAGES
#define DEBUG(A) (A)
#endif

struct vmem_struct *vmem = NULL;
FILE *pagefile = NULL;
FILE *logfile = NULL;
int signal_number = 0,
    pagefault_nr  = 0,
    index_counter = 0;

/* Received signal */

int main(void) {
    struct sigaction sigact;

    /* Init pagefile */
    init_pagefile(MMANAGE_PFNAME);
    if(!pagefile) {
        perror("Error creating pagefile");
        exit(EXIT_FAILURE);
    }

    /* Open logfile */
    logfile = fopen(MMANAGE_LOGFNAME, "w");
    if(!logfile) {
        perror("Error creating logfile");
        exit(EXIT_FAILURE);
    }

    /* Create shared memory and init vmem structure */
    vmem_init();
    if(!vmem) {
        perror("Error initialising vmem");
        exit(EXIT_FAILURE);
    }
#ifdef DEBUG_MESSAGES
    else {
        fprintf(stderr, "vmem successfully created\n");
    }
#endif /* DEBUG_MESSAGES */

    /* Setup signal handler */
    /* Handler for USR1 */
    sigact.sa_handler = sighandler;
    sigemptyset(&sigact.sa_mask);
    sigact.sa_flags = 0;
    if(sigaction(SIGUSR1, &sigact, NULL) == -1) {
        perror("Error installing signal handler for USR1");
        exit(EXIT_FAILURE);
    }
#ifdef DEBUG_MESSAGES
    else {
        fprintf(stderr, "USR1 handler successfully installed\n");
    }
#endif /* DEBUG_MESSAGES */

    if(sigaction(SIGUSR2, &sigact, NULL) == -1) {
        perror("Error installing signal handler for USR2");
        exit(EXIT_FAILURE);
    }
#ifdef DEBUG_MESSAGES
    else {
        fprintf(stderr, "USR2 handler successfully installed\n");
    }
#endif /* DEBUG_MESSAGES */

    if(sigaction(SIGINT, &sigact, NULL) == -1) {
        perror("Error installing signal handler for INT");
        exit(EXIT_FAILURE);
    }
#ifdef DEBUG_MESSAGES
    else {
        fprintf(stderr, "INT handler successfully installed\n");
    }
#endif /* DEBUG_MESSAGES */

    // Schleife, zur Anzeige der eingetroffnen Signale
    while(1) {
        signal_number = 0;
        pause();
        if(signal_number == SIGUSR1) {  /* Ein PageFault tritt auf */
#ifdef DEBUG_MESSAGES
            fprintf(stderr, "Processed SIGUSR1\n");
#endif /* DEBUG_MESSAGES */
            signal_number = 0;
        }
        else if(signal_number == SIGUSR2) {     /* PT dump (PageTable wird ausgegeben) */
#ifdef DEBUG_MESSAGES
            fprintf(stderr, "Processed SIGUSR2\n");
#endif /* DEBUG_MESSAGES */
            signal_number = 0;
        }
        else if(signal_number == SIGINT) {
#ifdef DEBUG_MESSAGES
            fprintf(stderr, "Processed SIGINT\n");
#endif /* DEBUG_MESSAGES */
        }
    }
    return 0;
}

/* Please DO keep this function unmodified! */
void logger(struct logevent le) {
    fprintf(logfile, "Page fault %10d, Global count %10d:\n"
            "Removed: %10d, Allocated: %10d, Frame: %10d\n",
            le.pf_count, le.g_count,
            le.replaced_page, le.req_pageno, le.alloc_frame);
    fflush(logfile);
}

// Initialisierung der VMEM Struktur
void vmem_init(void) {
    key_t shm_key = 23456;
    int shm_id;

    if((shm_id = shmget(shm_key, SHMSIZE, 0664 |IPC_CREAT)) == -1) // SHARED MEMORY ANLEGEN
        handle_error("SHARED MEMORY konnte nicht angelegt werden");

    DEBUG(fprintf(stderr, "SHARED MEMORY successfully allocated\n"));

    if((vmem = shmat(shm_id, NULL, 0)) == (void *)-1) // SHAKRED MEMORY für das Programm verfügbar machen
        handle_error("SHARED MEMORY konnte nicht für das Programm verfügbar gemacht werden");

    DEBUG(fprintf(stderr, "SHARED MEMORY successfully attached to local memory\n"));

    vmem -> adm.size = VMEM_NPAGES * VMEM_PAGESIZE; // Pagetable initialisieren und Admin-Struktur Werte setzen
    vmem -> adm.mmanage_pid = getpid();
    vmem -> adm.shm_id = shm_id;
    /*
     * sem_init initialisiert das namenlose Semaphore an der Adresse des Pointers sem.
     * Das Argument value spezifiziert den Wert des Semaphors. Beispielsweise:
     * pshared = 0 -> dann wird das SEM nur unter Threads eines Prozesses geteilt
     * pshared = 1 -> dann wird das SEM unter mehreren Prozessen geteilt.
     */
    if(sem_init(&(vmem->adm.sema), 1, 0) == - 1) handle_error("Semaphore konnte nicht initialisiert werden");

    DEBUG(fprintf(stderr, "SEMAPHORE wurde erfolgreich installiert\n"));
    return;
}

// Initialisierung der Pagefile
void init_pagefile(const char *pfname) {
    int numbersOfItem = VMEM_NPAGES * VMEM_PAGESIZE,
        data[numbersOfItem],
        result;

    srand(SEED); // Zufallszahlen randomisieren
    for(int i = 0; i < numbersOfItem; i++) data[i] = rand() % RAGE_FOR_RANDOM; // Range 0 bis 999 füllen

    pagefile = fopen(pfname, "w+b");
    if(!pagefile) handle_error("Pagefile konnte nicht erstellt werden");

    result = fwrite(data, sizeof(int), numbersOfItem, pagefile);
    if(!result) handle_error("Pagefile konnte nicht erstellt werden!");

    DEBUG(fprintf(stderr, "Pagefile erstellt!"));
}

// Signal Handler zur Verarbeitung eines Signals
void sighandler(int signo) {
    signal_number = signo;

    switch(signo) {
        case SIGUSR1: allocate_page();
                      break;
        case SIGUSR2: dump_pt();
                      break;
        case SIGINT:  cleanup();
                      exit(EXIT_SUCCESS);
        default:      break;
    }
}

// Funktion zum Aufräumen bei Beendigung des Programms
void cleanup() {
    int shm_id = vmem -> adm.shm_id;

    if(fclose(pagefile)) handle_error("Pagefile konnte nicht geschlossen werden");
    DEBUG(fprintf(stderr, "Pagefile geschlossen!"));

    if(fclose(logfile)) handle_error("Logfile konnte nicht geschlossen werden");
    DEBUG(fprintf(stderr, "Logfile geschlossen!"));

    shmctl(shm_id, IPC_RMID, 0);
    DEBUG(fprintf(stderr, "Programm wurde beendet!"));
}

// Zum Laden der angeforderten Page
void allocate_page(void){
    int req_page_no           = vmem -> adm.req_pageno,
        free_sprace_in_bitmap = VOID_IDX,
        removed_page_id       = VOID_IDX;

    //Wenn die Page schon geladen wurde
    if(vmem->pt.entries[req_page_no].flags & PTF_PRESENT) return;

    //Zähler für den Eintrag der Pages. Wird immer hochgezählt damit eine Page eingetragen werden kann. Bis 15 Fames -> also max_size -1.
    if(index_counter < VMEM_NFRAMES) {
        free_sprace_in_bitmap = index_counter;
        index_counter++;
    }

    if(free_sprace_in_bitmap != VOID_IDX) {
        DEBUG(fprintf(stderr, "Es wurde ein freier Frame gefunden mit der Framenr. %d, Zuteilung erfolgt", free_sprace_in_bitmap));
    } else {  //Wenn kein freier Frame gefunden wurde, machen wir einen Platz frei.
        free_sprace_in_bitmap = find_remove_frame();
        removed_page_id = vmem -> pt.framepage[free_sprace_in_bitmap]; //Schreibe den freien Platz in die Struktur
        pagefault_nr++; // Index hochzählen
        printf("Pagefault Nummer : %d", pagefault_nr);
        printf("Freie Frame Nummer : %d", free_sprace_in_bitmap);
    }

    /* Wenn die Seite modifiziert wurde
     * Wenn das Flag des freigemachten Platzes auf dirty gesetzt ist, dann wird die Seite in der Pagefile gespeichert.
     */
    if(vmem->pt.entries[removed_page_id].flags & PTF_DIRTY) store_page(removed_page_id);

    /*
     * Das Flag wird zurückgesetzt, indem es bitweise mit dem Komplement
     * von dem Present-Bit addiert wird.
     * D.h. Flag 1, Present-Bit 1
     * 0001 (Flag ist 1)
     * &0000 (Komplement von Present-Bit 1)
     * =0000 (Flag ist jetzt 0)
     */
    vmem -> pt.entries[removed_page_id].flags = (vmem->pt.entries[removed_page_id].flags & ~PTF_PRESENT);

    update_pt(free_sprace_in_bitmap); //Freie Stelle wird geupdated
    fetch_page(req_page_no);

    vmem->adm.pf_count++; //Update pagefault counter

    // Log action
    struct logevent levcent;
    levcent.req_pageno    = req_page_no;
    levcent.replaced_page = removed_page_id;
    levcent.alloc_frame   = free_sprace_in_bitmap;
    levcent.pf_count      = vmem->adm.pf_count;
    levcent.g_count       = vmem->adm.g_count;
    logger(levcent);

    sem_post(&(vmem->adm.sema)); // Unblock application
}

// Gibt den Inhalt des PageTable's aus
void dump_pt(void) {
    DEBUG(fprintf(stderr, " <========== DUMP PAGETABLE =========> \n"));
    DEBUG(fprintf(stderr, "admin struct\n"));
    DEBUG(fprintf(stderr, "size: %d, pf_count: %d\n", vmem->adm.size, vmem->adm.pf_count));
    DEBUG(fprintf(stderr, "reg_pageno: %d, next_alloc_idx: %d\n", vmem->adm.req_pageno, vmem->adm.next_alloc_idx));
    DEBUG(fprintf(stderr, "g_count: %d\n\n", vmem->adm.g_count));
    DEBUG(fprintf(stderr, " <========== DATA OF PAGETABLE =========> \n"));
    DEBUG(fprintf(stderr, "index, data\n"));

    for(int i = 0; i < (VMEM_NFRAMES * VMEM_PAGESIZE); i++)
            DEBUG(fprintf(stderr, "idx: %d %d\n", i, vmem->data[i]));
}

// Page aus Pagefile holen
void fetch_page(int pt_idx) {
    int offset  = pt_idx * sizeof(int) * VMEM_PAGESIZE,
        frame   = vmem -> pt.entries[pt_idx].frame,
        *pstart = &(vmem -> data[frame * VMEM_PAGESIZE]);
    /*
     * fseek() dient dazu die aktuelle Schreibe/Lese-Position in der angegebenen Datei
     * bzw im angegebenem Stream relativ zu einem angegebenen Bezugspunkt neu zu setzen.
     * @param @SEEK_SET (Der Start des Streams markiert den Ursprung)
     * @return 0 bei Erfolg, ansonsten Fehlschlag
     */
    if(fseek(pagefile, offset, SEEK_SET) != 0) handle_error("Positionierung in Pagefile failed!");

    /*
     * fread() liest ein Datenarray mit einer gegebenen Anzahl von Elementen von einer
     * gegebenen Größe aus dem angegebenen Stream in den Speicher.
     * Anschließend wird die Schreibposition des Streams um die totale Anzahl gelesener Bytes weitergerückt.
     */
    fread(pstart, sizeof(int), VMEM_PAGESIZE, pagefile);
}

// Page in Pagefile auslagern
void store_page(int pt_idx) {
    int offset  = pt_idx * sizeof(int) * VMEM_PAGESIZE,
            frame   = vmem -> pt.entries[pt_idx].frame,
            *pstart = &(vmem -> data[frame * VMEM_PAGESIZE]);
    /*
     * fseek() dient dazu die aktuelle Schreibe/Lese-Position in der angegebenen Datei
     * bzw im angegebenem Stream relativ zu einem angegebenen Bezugspunkt neu zu setzen.
     * @param @SEEK_SET (Der Start des Streams markiert den Ursprung)
     * @return 0 bei Erfolg, ansonsten Fehlschlag
     */
    if(fseek(pagefile, offset, SEEK_SET) != 0) handle_error("Positionierung in Pagefile failed!");
    /*
     * fwrite() schreibt ein Datenarray mit einer gegebenen Anzahl von Elementen von einer
     * gegebenen Größe aus dem Speicher an die aktuelle Position im angegebenen Stream.
     * Anschließend wird die Schreibposition des Streams um die totale Anzahl geschriebener Bytes weitergerückt.
     * @param ptr (Zeiger), size (die Größe in Bytes), count (die anzahl der Blöcke), stream (Zeiger auf den Stream)
     */
    if(!fwrite(pstart, sizeof(int), VMEM_PAGESIZE, pagefile)) handle_error("Schreiben in Pagefile failed!");
}

// Einträge des PageTables aktualisieren
void update_pt(int frame) {
    int page_idx = vmem -> adm.req_pageno,
        bm_idx   = frame / VMEM_BITS_PER_BMWORD,
        bit      = frame % VMEM_BITS_PER_BMWORD;
    // DEBUG
    DEBUG(fprintf(stderr, "bitmap: %d, bit: %d, frame: %d\n", bm_idx, bit, frame));
    /*
     * Bitmap setzen
     */
    vmem -> adm.bitmap[bm_idx] |= (1U << bit);
    /*
     * Inkrementiere next_alloc_idx (für Seitenersetzungsalgorithmen relevant)
     */
    vmem -> adm.next_alloc_idx = (vmem -> adm.next_alloc_idx + 1) % VMEM_NFRAMES;
    vmem -> pt.framepage[frame] = page_idx;
    /*
     * UPDATE PT_ENTRY
     */
    vmem->pt.entries[page_idx].flags |= PTF_USED | PTF_PRESENT;
    vmem->pt.entries[page_idx].flags &= ~PTF_DIRTY;
    vmem->pt.entries[page_idx].frame = frame;
    vmem->pt.entries[page_idx].count = vmem->adm.g_count;
}

// Zur Findung eines Frames, der geleert werden kann,
// um eine angeforderte Seite dort hineinladen zu können
int find_remove_frame(void) {
    int rm_frame = VOID_IDX;

    /*
     * Je nach Belegung von VMEM_ALGO im Compiler-Flag wird
     * der dazugehörige Algorithmus aufgerufen!
     * Da wir nur Clock-Algo haben, wird dies default aufgerufen!
     */
    switch(VMEM_ALGO) {
        case VMEM_ALGO_CLOCK: rm_frame = find_remove_clock();
                              break;
        case VMEM_ALGO_FIFO:  printf("Not supported yet");
                              break;
        case VMEM_ALGO_LRU:   printf("Not supported yet");
                              break;
        default:              rm_frame = find_remove_clock();
                              break;
    }

    return rm_frame;
}

// CLOCK Seitenersetzungsalgorithmus
int find_remove_clock(void) {
    int rm_frame = vmem -> adm.next_alloc_idx,
        frame    = rm_frame,
        page;
    /*
     * Solange weiterstellen, bis wir ein Frame finden
     * der gerade nicht benutzt wird bzw. ersetzt werden kann (Frame mit gelöschtem Used-Bit)
     * Zu erkennen, da dass Referenced-Bit (PFT_USED) Null ist. Nicht ersetzbare Seiten
     * haben das R-Bit=1, diese werden auf Null geändert und es wird ein Frame weitergegangen.
     */
    while(1) {
        page = vmem -> pt.framepage[frame];
        if(vmem ->  pt.entries[page].flags & PTF_USED) {
            vmem -> pt.entries[page].flags &= ~PTF_USED; // Used-Bit löschen
            frame = (frame + 1) % VMEM_NFRAMES; // 1 Frame vorrücken
        } else {
            rm_frame = frame; // Frame kann ersetzt werden, da R-Bit==0
            break;
        }
    }
    vmem -> adm.next_alloc_idx = rm_frame;
    return rm_frame;
}

// Ermittlung einer freien Stelle in der Speicherbitmap
int search_bitmap(void) {
    int free_bit= VOID_IDX, i;

    for(i = 0; i < VMEM_BMSIZE; i++){
        Bmword bitmap = vmem -> adm.bitmap[i];
        Bmword mask = ((i == VMEM_BMSIZE -1) ? VMEM_LASTBMMASK : 0);
        free_bit = find_free_bit(bitmap,mask);

        if(free_bit != VOID_IDX){
            int offset = i * VMEM_BITS_PER_BMWORD;
            free_bit = free_bit + offset;
            break;
        }
    }

    return free_bit;
}

// Hilfsfunktion zur Ermittlung einer freien Stelle in der Speicherbitmap
int find_free_bit(Bmword bmword, Bmword mask) {
    int free_bit;

    Bmword bitmask = 1;  // Vergleichsmaske initialisieren
    bmword = (bmword | mask); // Obere Begrenzung im Bitstring setzen

    for(free_bit = 0; free_bit < VMEM_BITS_PER_BMWORD; free_bit++){
        if(!bmword & bitmask) break;
        bitmask <<= 1; // shift nach links um 0001
    }

    return free_bit < VMEM_BITS_PER_BMWORD ? free_bit : VOID_IDX;
}