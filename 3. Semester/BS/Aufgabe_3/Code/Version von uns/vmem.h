/* File: vmem.h
 * Global Definitions for BSP3 sample solution
 * Model of virtual memory management
 */

#ifndef VMEM_H
#define VMEM_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>
#include <semaphore.h>
#include <sys/stat.h>
#include <sys/fcntl.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

#define SHMKEY          "/vmem.h"
#define SHMPROCID       'C'

typedef unsigned  int Bmword;    /* Frame bitmap */

/* Sizes */
#define VMEM_VIRTMEMSIZE 1024   /* Process address space / items */
#define VMEM_PHYSMEMSIZE  128   /* Physical memory / items */
#define VMEM_PAGESIZE       8   /* Items per page */
#define VMEM_NPAGES     (VMEM_VIRTMEMSIZE / VMEM_PAGESIZE)     /* Total number of pages */
#define VMEM_NFRAMES (VMEM_PHYSMEMSIZE / VMEM_PAGESIZE)   /* Number of available frames */
#define VMEM_LASTBMMASK (~0U << (VMEM_NFRAMES % (sizeof(Bmword) * 8)))
#define VMEM_BITS_PER_BMWORD     (sizeof(Bmword) * 8)
#define VMEM_BMSIZE     ((VMEM_NFRAMES - 1) / VMEM_BITS_PER_BMWORD + 1) //Wie gross ist die Anzahl bvenoetigter BMWords um den Speicher zu verwalten | 1 bit pro Frame

/* Page Table */
#define PTF_PRESENT     1       /* page is loaded? */
#define PTF_DIRTY       2       /* store: need to write */
#define PTF_USED        4       /* For clock algo only */

struct pt_entry {
    int flags;                  /* see defines above */
    int frame;                  /* Frame idx */
    int count;                  /* For LRU algo */
};

struct vmem_adm_struct {
    int size;                   /* Groesse unserer Speicherstruktur im Speicher */
    pid_t mmanage_pid;          /* Prozessid */
    int shm_id;                 /* ID des geteilten Speichers*/
    sem_t sema;                 /* Coordinate acces to shm */
    int req_pageno;             /* Number of requested page */
    int next_alloc_idx;         /* Next frame to allocate (FIFO, CLOCK) 
                                 */
    int pf_count;               /* Page fault counter */
    int g_count;                /* Global counter for LRU algo */
    Bmword bitmap[VMEM_BMSIZE]; /* 0 = free */
};

struct pt_struct {
    struct pt_entry entries[VMEM_NPAGES];   /* Informationen ueber jede Page des Prozesses */
    int framepage[VMEM_NFRAMES];            /* pages on frame */
};

/* This is to be located in shared memory */
struct vmem_struct {
    struct vmem_adm_struct adm;             /* Administrierungsdaten */
    struct pt_struct pt;                    /* Pagetable */
    int data[VMEM_NFRAMES * VMEM_PAGESIZE]; /* Daten */
};

#define SHMSIZE (sizeof(struct vmem_struct))

#endif /* VMEM_H */

/*
Frame: Speicherseite im Physikalische Speicher
Page:  Speicherseite im Logischen Speicher
*/