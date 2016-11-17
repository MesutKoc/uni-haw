//compile== gcc vmaccess.c -o vmaccess -lrt
//-lrt damit es nicht zu einem linkerfehler kommt start: ./vmaccess
//compile== gcc vmaccess.c vmappl.c -o vmaccess -lrt
#include "vmaccess.h"
#define ALLACCESS_MODE (S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP | S_IROTH | S_IWOTH)
#define SHMEM_PATH "/sharedmem"

struct vmem_struct *vmemp;

void vm_init(void){
  
  key_t key;
  int shmid;
  
  key = ftok(SHMKEY,SHMPROCID);
  
  shmid=shmget(key,SHMSIZE,USE_FLAG);
  
  if(shmid<0){
    perror("shmget failed");
    exit(EXIT_FAILURE);
  }
  
  vmemp = shmat(shmid,NULL,0);
}
  
  
//beide muessen pruefen ob schon vm_init gemacht wurde
/* Read from "virtual" address */
int vmem_read(int address){
  if(address<0){
    perror("invalid address");
    exit(1);
  }
  
  #ifdef DEBUG_MESSAGES
  fprintf(stderr, "requested address : %d\n",address);
  #endif /* DEBUG_MESSAGES */
  int page_num = 0;
  int frame_num = 0;
  int phy_addr = 0;
  int offset = 0;
  
  if(vmemp == NULL){
    vm_init();
  }
  
  page_num = address/VMEM_PAGESIZE;
  #ifdef DEBUG_MESSAGES
  fprintf(stderr, "from pagenumber : %d\n",page_num);
  #endif /* DEBUG_MESSAGES */  

  if(!(vmemp->pt.entries[page_num].flags & PTF_PRESENT)){
    vmemp->adm.req_pageno = page_num;
    kill(vmemp->adm.mmanage_pid,SIGUSR1);
    sem_wait(&(vmemp->adm.sema));
  }
  vmemp->adm.g_count++;
  vmemp->pt.entries[page_num].count = vmemp->adm.g_count;
  vmemp->pt.entries[page_num].flags |= PTF_USED;
  offset = address % VMEM_PAGESIZE;
  frame_num = vmemp->pt.entries[page_num].frame;
  
  if(frame_num >=(VMEM_NFRAMES)){
    perror("ERROR during read (frame_num)");
    exit(1);
  }
  phy_addr = ((frame_num * VMEM_PAGESIZE)+offset);
  
  if(phy_addr > (VMEM_NFRAMES * VMEM_PAGESIZE)){
    perror("ERROR during read (phy_addr)");
    exit(1);
  }
  
  #ifdef DEBUG_MESSAGES
  fprintf(stderr, "returning data : %d  from phy_addr : %d\n",vmemp->data[phy_addr],phy_addr);
  #endif /* DEBUG_MESSAGES */
  return vmemp->data[phy_addr];
  
}

/* Write data to "virtual" address */
//der benutzer-prozess will etwas an eine adresse schreiben, dabei kennt er nur seinen
//virtuellen speicherbereich
void vmem_write(int address, int data){
  
	if(address<0){
	  perror("invalid address");
	  exit(1);
	}
	if(vmemp==NULL){
	  vm_init();
	}
	  
	int page_num;
	int frame_num;
	int phy_addr;
	int offset;
	
	page_num = address/VMEM_PAGESIZE;
	
	if(page_num >= VMEM_NPAGES){
	  perror("invalid address 2");
	  exit(1);
	}
	if(!(vmemp->pt.entries[page_num].flags & PTF_PRESENT)){
	  vmemp->adm.req_pageno = page_num;
	  kill(vmemp->adm.mmanage_pid,SIGUSR1);
	  sem_wait(&(vmemp->adm.sema));
	}
	vmemp->adm.g_count++;
	vmemp->pt.entries[page_num].count = vmemp->adm.g_count;
	vmemp->pt.entries[page_num].flags |= PTF_USED;
	vmemp->pt.entries[page_num].flags |= PTF_DIRTY;
	offset = address % VMEM_PAGESIZE;
	frame_num = vmemp->pt.entries[page_num].frame;
	phy_addr = ((frame_num *VMEM_PAGESIZE)+offset);
	vmemp->data[phy_addr]=data;
		
	
}
