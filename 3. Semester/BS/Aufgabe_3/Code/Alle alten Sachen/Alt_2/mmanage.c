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
 * TODO:
 * currently nothing
 * */

#include "mmanage.h"

struct vmem_struct *vmem = NULL;
FILE *pagefile = NULL;
FILE *logfile = NULL;
int signal_number = 0;          /* Received signal */
int remove_method = VMEM_ALGO_FIFO;//für switch auswahl der remove-page-from-frame methode
//int stopvariable = 0;
//int clockpos = 0;//clock-pointer. muss wiederverwendbar sein, darum global

int
main(void)/**aufruf der pagefile-init, shared-mem-init, erstellen des logfiles, init des signalhandlers */
{
    struct sigaction sigact;
      
    #ifdef FIFO
      remove_method = VMEM_ALGO_FIFO;
    #endif /* FIFO */
      
    #ifdef LRU
      remove_method = VMEM_ALGO_LRU;
    #endif /* LRU */
      
    #ifdef CLOCK
      remove_method = VMEM_ALGO_CLOCK;
    #endif /* CLOCK */

    /* Init pagefile */
    init_pagefile(MMANAGE_PFNAME);
    if(!pagefile) {
        perror("Error creating pagefile");
        exit(EXIT_FAILURE);
    }
#ifdef DEBUG_MESSAGES
    else {
        fprintf(stderr, "pagefile successfully created\n");
    }
#endif /* DEBUG_MESSAGES */

    /* Open logfile *///selberbauen des logfile-namen
    char logfname1part[20];
    char logfname2part[20];
    switch(remove_method){//je nach remove-methoden definition wird in part2 einer der drei endungen gespeichert
      default:
      case VMEM_ALGO_FIFO :{
	sprintf(logfname2part,"_fifo%d.txt",VMEM_PAGESIZE);
	break;
      }
      case VMEM_ALGO_LRU :{
	sprintf(logfname2part,"_lru%d.txt",VMEM_PAGESIZE);
	break;
      }
      case VMEM_ALGO_CLOCK :{
	sprintf(logfname2part,"_clock%d.txt",VMEM_PAGESIZE);
	break;
      }
    }
    strcpy(logfname1part,MMANAGE_LOGFNAME);//der vordefinierte logfname wird in part1 kopiert
    logfname1part[strlen(MMANAGE_LOGFNAME)-4]='\0';//string mit einer 0 vorher abschließen (.txt wegmachen)
    strcat(logfname1part,logfname2part);//strings aneinander hängen
    logfile = fopen(logfname1part, "w");//logfile zum schreiben öffnen
    if(!logfile) {
        perror("Error creating logfile");
        exit(EXIT_FAILURE);
    }
#ifdef DEBUG_MESSAGES
    else {
        fprintf(stderr, "logfile successfully created\n");
    }
#endif /* DEBUG_MESSAGES */

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

    /* Signal processing loop */
    while(1) {
        signal_number = 0;
        pause();
        if(signal_number == SIGUSR1) {  /* Page fault */
#ifdef DEBUG_MESSAGES
            fprintf(stderr, "Processed SIGUSR1\n");
#endif /* DEBUG_MESSAGES */
            signal_number = 0;
        }
        else if(signal_number == SIGUSR2) {     /* PT dump */
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

void
init_pagefile(const char *pfname){/**erstellen einer pagefile-datei und init. mit 0 */

  int val;
  int i;
  int rc = 0;
  
  pagefile = fopen(pfname, "w+b");//read+write bitweise
  if(pagefile==NULL){
    perror("Error occured during opening or creating the pagefile");
    exit(EXIT_FAILURE);
  }
  
  for(i = 0; i < PAGEFILE_SIZE; i++){
   
    val = 0;
    
    rc = fwrite(&val,sizeof(val),1,pagefile);//pagefile voll 0en schreiben
    
    if(rc != 1){
      perror("Error occured during writing in the pagefile");
      cleanup();
      exit(EXIT_FAILURE);
    }
  }
}

void
vmem_init(){/**erstellen des shared memory und init. der inhalte */
  int shmid;
  key_t key;
  key = ftok(SHMKEY, SHMPROCID); //name für shared memory
  
  if((shmid = shmget(key, SHMSIZE, USE_FLAG | IPC_CREAT )) == -1){
    perror("Error occured during shm initialisation");
    cleanup();
    exit(EXIT_FAILURE);
  }
#ifdef DEBUG_MESSAGES
    else {
        fprintf(stderr, "shm initialisation successful shmid:%d\n",shmid);
    }
#endif /* DEBUG_MESSAGES */

  vmem = shmat(shmid,NULL,0);//vmem zeigt auf anfang des shared mem

  int i;
  //init vmem_adm_struct
  vmem->adm.size = SHMSIZE;
  int pid = getpid();
  printf("%d",pid);
  vmem->adm.mmanage_pid = getpid();
  vmem->adm.shm_id = shmid;
  //alle arrays/variablen auf 0
  int rc = sem_init(&vmem->adm.sema,2,1);
  if(rc != 0){
    perror("Error occured during semaphore initialisation");
    cleanup();
    exit(EXIT_FAILURE);
  }
  #ifdef DEBUG_MESSAGES
    else {
        fprintf(stderr, "semaphore successfully created\n");
    }
#endif /* DEBUG_MESSAGES */

  vmem->adm.req_pageno=-1;
  vmem->adm.next_alloc_idx=0;
  vmem->adm.pf_count=0;
  vmem->adm.g_count=0;
  
  vmem->adm.bitmap[0] = 0;

  //end init vmem_adm_struct

  //init pt_struct
  for(i = 0; i < (VMEM_NPAGES); i++){
    vmem->pt.entries[i].flags = 0;
    vmem->pt.entries[i].frame = 0;
    vmem->pt.entries[i].count = 0;
    vmem->pt.entries[i].load_time = 0;
  }
  for(i = 0; i < (VMEM_NFRAMES); i++){
    vmem->pt.framepage[i] = -1;
  }
  //end init pt_struct
  
  //init data
  for(i = 0; i < (VMEM_NFRAMES * VMEM_PAGESIZE); i++){
    vmem->data[i]=-1;
  }
  //end init data
}

void
sighandler(int signo){/**wird bei ankommendem signal/pagefault aufgerufen. wenn schon alle frames besetzt sind, wird einer freigeräumt.
  sonst wird die req_page direkt in einen frame geladen und dem wartenden vmaccess am ende ein sem_post geschickt. */

  int replaced_page = -1;
  
  switch(signo){
    case SIGUSR1 :{
      //schreibe daten in pagetable um.
      #ifdef DEBUG_MESSAGES
      fprintf(stderr, "\nSIGUSR1 Signal received\n");
      #endif/* DEBUG_MESSAGES */
      vmem->adm.pf_count++;//pagefault++
      
      if(search_bitmap(0) == -1){//alle frames voll? bitmap(0)=ohne bitmap(1)=mit reservierung
	#ifdef DEBUG_MESSAGES
	fprintf(stderr, "Remove Frame\n");
	#endif/* DEBUG_MESSAGES */
	
	//stopvariable++;
	//dump_pt();
	
	replaced_page = find_remove_frame();//page die aus ihrem frame geworfen wurde. (methode je nach define gewählt)  
	
	#ifdef DEBUG_MESSAGES
	fprintf(stderr, "Replaced Page : %d\n",replaced_page);
	#endif/* DEBUG_MESSAGES */
	//dump_pt();
	//if(stopvariable > 3){exit(1);}
      }
      fetch_page(vmem->adm.req_pageno);//allocate bzw. laden der req_page
      struct logevent log;//logger daten setzen
      log.req_pageno = vmem->adm.req_pageno;
      log.replaced_page = replaced_page;
      log.alloc_frame = vmem->pt.entries[vmem->adm.req_pageno].frame;
      log.pf_count = vmem->adm.pf_count;
      log.g_count = vmem->adm.g_count;
      logger(log);
      sem_post(&(vmem->adm.sema));//dem wartenden vmaccess signalisieren
      break;
    }
    case SIGUSR2 :{
      //dump pagetable
      #ifdef DEBUG_MESSAGES
      fprintf(stderr, "SIGUSR2 Signal received\n");
      #endif/* DEBUG_MESSAGES */
      break;
    }
    case SIGINT :{
      #ifdef DEBUG_MESSAGES
      fprintf(stderr, "SIGINT Signal received\n");
      #endif/* DEBUG_MESSAGES */
      cleanup();
      exit(EXIT_SUCCESS);
      break;
    }
    default : {
      perror("unknown Signal");
      cleanup();
      exit(EXIT_FAILURE);
    }
    
  }
  
}


int find_remove_frame(void){//auswahl der tatsächlichen remove funktion
  
  int removed_page = -1;
  
  switch(remove_method){
    default:
    case VMEM_ALGO_FIFO :{
      #ifdef DEBUG_MESSAGES
      fprintf(stderr, "USE FIFO TO REMOVE\n");
      #endif/* DEBUG_MESSAGES */
      removed_page = find_remove_fifo();
      break;
    }
    case VMEM_ALGO_LRU :{
      #ifdef DEBUG_MESSAGES
      fprintf(stderr, "USE LRU TO REMOVE\n");
      #endif/* DEBUG_MESSAGES */
      removed_page = find_remove_lru();
      break;
    }
    case VMEM_ALGO_CLOCK :{
      #ifdef DEBUG_MESSAGES
      fprintf(stderr, "USE CLOCK TO REMOVE\n");
      #endif/* DEBUG_MESSAGES */
      removed_page = find_remove_clock();
      break;
    }
  }
    int frame_index = vmem->pt.entries[removed_page].frame;//frame in dem die rausgelöschte page stand
    vmem->adm.bitmap[0] &= ~(1<<frame_index);//frame den die removete page besetzt hat, auf leer setzen
    
    #ifdef DEBUG_MESSAGES
    fprintf(stderr, "removed_page : %d | frame : %d\n",removed_page,vmem->pt.entries[removed_page].frame);
    fprintf(stderr, "bitmap : %X\n",vmem->adm.bitmap[0]);
    #endif/* DEBUG_MESSAGES */
   
    return removed_page;
}

int find_remove_fifo(void){
  /**sucht unter allen im physikalischen speicher liegenden pages die mit dem höchsten alter raus, speichert ihre
   daten evtl. im pagefile ab und macht den phys. speicher wieder verfügbar, indem die flags der page gelöscht werden*/
  int i;
  int oldest = vmem->pt.entries[vmem->pt.framepage[0]].load_time;//absolutes alter der page im ersten frame, speichern
  int removed_page = vmem->pt.framepage[0];
  for(i = 0; i < VMEM_NFRAMES;i++){//dann werden alle frames durchgegangen
    if(oldest > vmem->pt.entries[vmem->pt.framepage[i]].load_time){//wenn die page in einem anderen frame noch älter ist
      oldest = vmem->pt.entries[vmem->pt.framepage[i]].load_time;//diese als älteste merken
      removed_page = vmem->pt.framepage[i];//und als rausschmeißkandidat merken
    }
  }
  if(vmem->pt.entries[removed_page].flags & PTF_DIRTY){//wenn die raus zu schmeißende page modifiziert wurde->pagefile
    #ifdef DEBUG_MESSAGES
    fprintf(stderr, "REMOVED PAGE IS DIRTY...HAVE TO STORE PAGE\n");
    #endif/* DEBUG_MESSAGES */
    store_page(removed_page);
  }
  vmem->pt.entries[removed_page].flags = 0;//alle flags der removten page auf 0, besonders das present bit
  
  return removed_page;
}

int find_remove_lru(void){
/**sucht unter allen im physikalischen speicher liegenden pages die am längsten nicht modifizierte heraus, speichert ihre
   daten evtl. im pagefile ab und macht den phys. speicher wieder verfügbar, indem die flags der page gelöscht werden*/
  int i;
  int oldest = vmem->pt.entries[vmem->pt.framepage[0]].count;
  int removed_page = vmem->pt.framepage[0];
  for(i = 0; i < VMEM_NFRAMES;i++){
    if(oldest > vmem->pt.entries[vmem->pt.framepage[i]].count){
      oldest = vmem->pt.entries[vmem->pt.framepage[i]].count;
      removed_page = vmem->pt.framepage[i];
    }
  }
  
  if(vmem->pt.entries[removed_page].flags & PTF_DIRTY){
    #ifdef DEBUG_MESSAGES
    fprintf(stderr, "REMOVED PAGE IS DIRTY...HAVE TO STORE PAGE\n");
    #endif/* DEBUG_MESSAGES */
    store_page(removed_page);
  }
  vmem->pt.entries[removed_page].flags = 0;
  
  return removed_page;
}

int find_remove_clock(void){/**überprüfung startet bei vorherigem clockindex und setzt das uses-bit aller übergangenen
  pages auf 0. ist das uses-bit einer erreichten page schon 0, wird diese als nächstes removet.*/
  static int clockpos = 0;
  int removed_page = -1;
  int i;
  int done = 0;
  int startpos = clockpos;
  
  while(done == 0){
    for(i = startpos; i < (VMEM_NFRAMES); i++){//zuerst starte bei clockpos dann bei 0
      if((vmem->pt.entries[vmem->pt.framepage[i]].flags & PTF_USED) == 0){
	removed_page = vmem->pt.framepage[i];
	clockpos = i+1; //script p.353
	done = 1;
	break;
      }else{
	vmem->pt.entries[vmem->pt.framepage[i]].flags = vmem->pt.entries[vmem->pt.framepage[i]].flags & (~PTF_USED);//used bit auf 0
      }
    }
    startpos = 0;
  }
  
  if(vmem->pt.entries[removed_page].flags & PTF_DIRTY){//wenn die rauszulöschende page modifiziert wurde, in pagefile speichern
    #ifdef DEBUG_MESSAGES
    fprintf(stderr, "REMOVED PAGE IS DIRTY...HAVE TO STORE PAGE\n");
    #endif/* DEBUG_MESSAGES */
    store_page(removed_page);
  }
  vmem->pt.entries[removed_page].flags = 0;//flags auf 0 

  return removed_page;
}


void fetch_page(int pt_idx){/**lädt die page nummer idx in den vorher freigeräumten und jetzt leer stehenden frame und setzt ihre flags entsprechend*/
  int rc = 0;
  int *element = 0;
  
  #ifdef DEBUG_MESSAGES
  fprintf(stderr, "fetch pt_idx : %d\n",pt_idx);
  #endif/* DEBUG_MESSAGES */  
  rc = fseek(pagefile,pt_idx *sizeof(int) * VMEM_PAGESIZE, SEEK_SET);//file cursor auf pageanfang setzen
  if(rc != 0){
    perror("Error occured during fseek() from pagefile");
    cleanup();
    exit(EXIT_FAILURE);
  }
  
  vmem->pt.entries[pt_idx].flags |=PTF_PRESENT;//presentbit der in einen frame zu ladenden page auf 1
  vmem->pt.entries[pt_idx].flags |=PTF_USED;//ebenso das used bit
  vmem->pt.entries[pt_idx].frame = search_bitmap(1);//nach leerem frame suchen + reservieren lassen
  
  vmem->pt.framepage[vmem->pt.entries[pt_idx].frame] = pt_idx;//dem framepage array sagen welche page jetzt im frame liegt
  
  element = &vmem->data[(vmem->pt.entries[pt_idx].frame *VMEM_PAGESIZE)];//startadresse ab der die page in den phys. speicher geladen werden soll
  
  rc = fread(element, sizeof(int), VMEM_PAGESIZE, pagefile);//an adresse aus element, pagesize mal 4 byte, aus dem pagefile laden.
  if(rc!= VMEM_PAGESIZE){
    perror("Error occured during fread on pagefile");
    cleanup();
    exit(EXIT_FAILURE);
  }
  fflush(pagefile);
  
  vmem->pt.entries[pt_idx].load_time = vmem->adm.g_count;//ladezeitpunkt der page auf globale zeit setzen
  
  
}


void store_page(int pt_index){/**speichert die page mit nummer pt_index in die pagefile.bin */
      #ifdef DEBUG_MESSAGES
      fprintf(stderr, "STORE PAGE : %d\n",pt_index);
      #endif/* DEBUG_MESSAGES */  
      int *data = 0;
      int rc = 0;
      rc = fseek(pagefile, pt_index*sizeof(int)*VMEM_PAGESIZE, SEEK_SET);//cursor an die anfangsadresse der page in der datei setzen
      
      if(rc != 0){
	perror("Error occured during fseek from pagefile");
	cleanup();
	exit(EXIT_FAILURE);
      }
      
      data = &vmem->data[(vmem->pt.entries[pt_index].frame * VMEM_PAGESIZE)];//pointer auf pageanfang im phys. speicher
      
      #ifdef DEBUG_MESSAGES
      fprintf(stderr, "DATA : %d\n",*data);
      #endif/* DEBUG_MESSAGES */  
      
      rc = fwrite(data, sizeof(int), VMEM_PAGESIZE, pagefile);//schreibe den inhalt am adresse data, pagesize mal 4 byte, an die stelle auf der der cursor steht
      if(rc!= VMEM_PAGESIZE){
	perror("Error occured during fwrite on pagefile");
	cleanup();
	exit(EXIT_FAILURE);
      }
      
      fflush(pagefile);
        
}

int search_bitmap(int get){
  /**geht die bitmap durch und gibt die stelle der ersten gefundenen 0 zurück. wird -1 zurück gegeben existiert keine 0 in der bitmap 
     d.h. es existiert kein leerer frame. übergibt man beim aufruf eine 1 mit, wird die gefundene leere stelle auf 1, also auf benutzt gesetzt*/
  int i;
  int mask = 1;
  int result = -1;
  for(i = 0; i < VMEM_NFRAMES; i++){
    if(vmem->adm.bitmap[0] & mask){
      mask <<= 1;//mask*=2;
    }else{
      result = i;
      if(get){
	vmem->adm.bitmap[0] |= mask;
      }
      break;
    }
  }
  #ifdef DEBUG_MESSAGES
  fprintf(stderr, "SEARCH_RES: resu: %d    bitmap: %X\n",result,vmem->adm.bitmap[0]);
  #endif/* DEBUG_MESSAGES */
  
  return result;
}


void dump_pt(void){/**ausgabe aller pagedaten, framedateninhalte und frame-page zuordnungen */
  int i;
  for(i = 0; i<VMEM_NPAGES; i++){
    fprintf(stderr, "page %d | frame %d | flags %d | count %d \n",i,vmem->pt.entries[i].frame,vmem->pt.entries[i].flags,vmem->pt.entries[i].count);    
  }
  for(i = 0; i<VMEM_NFRAMES*VMEM_PAGESIZE; i++){
    fprintf(stderr, "phyaddr %d | data %d  \n",i,vmem->data[i]);    
  }
  for(i = 0; i<VMEM_NFRAMES; i++){
    fprintf(stderr, "frame %d | page %d  \n",i,vmem->pt.framepage[i]);    
  }
  
}

void cleanup(void){/**schließen+löschen der pagefile, sowie des semaphoren und des shared mem */
  int rc = 0;
  
  #ifdef DEBUG_MESSAGES
  dump_pt();
  #endif/* DEBUG_MESSAGES */
  
  rc = fclose(pagefile);
  if(rc != 0){
    perror("Error occured during close the pagefile");
    exit(EXIT_FAILURE); 
  }
  
  rc = remove(MMANAGE_PFNAME);
  if(rc != 0){
    perror("Error occured during remove the pagefile");
    exit(EXIT_FAILURE);
  }
  
  rc = sem_destroy(&(vmem->adm.sema));
  if(rc == -1){
    perror("Error occured during destroy semaphore");
    exit(EXIT_FAILURE);
  }
  rc = shmctl(vmem->adm.shm_id, IPC_RMID, NULL);
  if(rc == -1){
    perror("Error occured during cleaning shared memory");
    exit(EXIT_FAILURE);
  }
  rc = shmdt(vmem);
  if(rc == -1){
    perror("Error occured during detaching shared memory");
    exit(EXIT_FAILURE);
  }
}

/* Please DO keep this function unmodified! */
void
logger(struct logevent le)
{
    fprintf(logfile, "Page fault %10d, Global count %10d:\n"
            "Removed: %10d, Allocated: %10d, Frame: %10d\n",
            le.pf_count, le.g_count,
            le.replaced_page, le.req_pageno, le.alloc_frame);
    fflush(logfile);
}
