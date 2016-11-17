/*
vmaccess.c

Author: Olga Mychova, Jan Rübbelke
Aus Hilfscode für Aufgabe 3 (Author: Schwarz)
*/

#include "vmaccess.h"

void vmem_init(void){
    key_t shm_key = 0; int shm_id = -1;
    /* Create shared memory */
    shm_key = ftok(SHMKEY, SHMPROCID);
    /* Set the IPC_CREAT flag */
    shm_id = shmget(shm_key, SHMSIZE, 0664 | IPC_CREAT);
    
    
     /*TODO*/
     
     
    vmem = shmat(shm_id, NULL, 0);
    
    
     /*TODO*/
     
     
    /* Initialise the Pagetable: */ /* size of ? 128*8=1024 */
    vmem->adm.size = VMEM_NPAGES * VMEM_PAGESIZE;
    vmem->adm.mmanage_pid = getpid();
    vmem->adm.shm_id = shm_id;
    /* shared among processes */
    sem_init(&(vmem->adm.sema), 1, 0);
    
    
     /*TODO*/
     
     
    return;
}

void allocate_page(void){
    int req_pageno = vmem->adm.req_pageno;
    int frame = VOID_IDX;
    int page_removed_idx = VOID_IDX;
    /* Page not allocated? */
    if(vmem->pt.entries[req_pageno].flags & PTF_PRESENT){
    
    
    /*TODO*/
    
    
    }
    /* Free frames? */
    frame = search_bitmap();
    if(frame != VOID_IDX) {
        fprintf(stderr, "Found free frame no %d, allocating page\n", frame);
        update_pt(frame);
        fetch_page(vmem->adm.req_pageno); 
    }
    /* end if FRAME_VOID */ /* No free frames: Which page to remove? */
    else
    { frame = find_remove_frame();
    
    
         /*TODO*/
         
         
        page_removed_idx = vmem->pt.framepage[frame];

        /* Store page to be removed and clear present-bit */
        if(vmem->pt.entries[page_removed_idx].flags & PTF_DIRTY) {
            store_page(page_removed_idx); 
        }
        mem->pt.entries[page_removed_idx].flags &= ~PTF_PRESENT;
        /* Load new page */
        update_pt(frame);
        fetch_page(vmem->adm.req_pageno); 
    }
    /* Update page fault counter */
    vmem->adm.pf_count++;
    /* Log action */

    /* Unblock application */
    sem_post(&(vmem->adm.sema))
}



void fetch_page(int pt_idx){
    int offset = pt_idx * sizeof(int) * VMEM_PAGESIZE;
    int frame = vmem->pt.entries[pt_idx].frame;
    int *pstart = &(vmem->data[frame * VMEM_PAGESIZE]);
    /* fseek: change the file position indicator for the specified stream*/
    if(fseek(pagefile, offset, SEEK_SET) == -1) {
        perror("Positioning in pagefile failed! ");
        exit(EXIT_FAILURE); 
    }
    fread(pstart, sizeof(int), VMEM_PAGESIZE, pagefile)
}


void store_page(int pt_idx){
    int offset = pt_idx * sizeof(int) * VMEM_PAGESIZE;
    int frame = vmem->pt.entries[pt_idx].frame;
    int *pstart = &vmem->data[frame * VMEM_PAGESIZE];
    ifseek(pagefile, offset, SEEK_SET);
    
    
     /*TODO*/
     
     
    write(pstart, sizeof(int), VMEM_PAGESIZE, pagefile));
    
    
     /*TODO*/
     
     
}

void update_pt(int frame){
    int page_idx = vmem->adm.req_pageno;
    int bm_idx = frame / VMEM_BITS_PER_BMWORD;
    int bit = frame % VMEM_BITS_PER_BMWORD;
    /* Update bitmap */
    vmem->adm.bitmap[bm_idx] |= (1U << bit);
    /* Increment of next_alloc_idx */
    vmem->adm.next_alloc_idx = (vmem->adm.next_alloc_idx + 1) % VMEM_NFRAMES;
    /* Update framepage */
    vmem->pt.framepage[frame] = page_idx;
    /* Update pt_entry */
    vmem->pt.entries[page_idx].flags |= PTF_USED | PTF_PRESENT;
    vmem->pt.entries[page_idx].flags &= ~PTF_DIRTY;
    vmem->pt.entries[page_idx].frame = frame;
    vmem->pt.entries[page_idx].startcount = vmem->adm.g_count;
    vmem->pt.entries[page_idx].count = 0;
}

int find_remove_frame(void){
    int remove_frame = VOID_IDX;
    switch (VMEM_ALGO) {
        case VMEM_ALGO_LRU: remove_frame = find_remove_lru(); break;
        case VMEM_ALGO_CLOCK: remove_frame = find_remove_clock(); break;
        case VMEM_ALGO_FIFO:
        default: remove_frame = find_remove_fifo(); break; 
    }
    return remove_frame;
}


int find_remove_clock(void){
    int remove_frame = vmem->adm.next_alloc_idx;
    int frame = remove_frame;
    int page;
    while(1) {
        page = vmem->pt.framepage[frame];
        if(vmem->pt.entries[page].flags & PTF_USED) {
            vmem->pt.entries[page].flags &= ~PTF_USED;
            /* clear used flag*/
            frame = (frame + 1) % VMEM_NFRAMES; }
        else { /* frame not marked as used */
            remove_frame = frame; break; }
    }
    /* end while */
    vmem->adm.next_alloc_idx = remove_frame ;
    return remove_frame;
}