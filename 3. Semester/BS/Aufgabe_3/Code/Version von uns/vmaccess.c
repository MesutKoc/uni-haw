/*
 * File:   vmaccess.c
 * Author: Mesut Koc
 *
 * Created on 28. November 2015, 14:33
 */
#include "vmaccess.h"

// handle_error
#define handle_error(msg) do { perror(msg); exit(EXIT_FAILURE); } while (0)

// struct
struct vmem_struct *vmem = NULL;

/*
 * Connect to shared memory (key from vmem.h)
 * @param void
 * @return void
 */
void vm_init(void) {
    key_t shm_key = 23456;
    int shm_id;
    /*
     * Mit shmget legen wir dem gemeinsamen Speicher an
     * @param shm_key (Schlüsselzahl), SHMSIZE (die Größe), shmflag (Berechtigungsbits kombiniert)
     * @return -1 im worst case, ansonsten die SHARED MEMORY ID, für den nächsten Aufruf
     */
    if((shm_id = shmget(shm_key, SHMSIZE, 0664 |IPC_CREAT)) == -1) handle_error("SHARED MEMORY konnte nicht angelegt werden");
    /*
     * Mit shmat (shared memory attach) binden wir den Speicher ein.
     * @param shm_id (die ermittelte SHM-ID), shmaddr (das System sucht eine passende Stelle), shmflag
     * @return -1 im worst case
     */
    if((vmem = shmat(shm_id, NULL, 0)) == (void *) -1) handle_error("SHARED MEMORY konnte nicht eingebindet werden");
}

/*
 * Read from "virtual" address
 * @param adress
 * return der Inhalt des Speicherss
 */
int vmem_read(int address) {
    int ad = get_phys_index(address, 0);
    return vmem->data[ad]; // Besorge die Physikalische Adresse && Inhalt des Speichers zurückgeben
}

/*
 * Write data to "virtual" address
 * @param adress, data
 * return void
 */
void vmem_write(int address, int data) {
    int ad = get_phys_index(address, 1);
    vmem->data[ad] = data; // Besorge die Physikalische Adresse & Inhalt des Speichers verändern
    return;
}

/*
 * Holt die Physikalische Adresse
 * @param adress, permision
 * return die Physikalische Adresse (int)
 */
int get_phys_index(int address, int permission) {
    //vmem != NULL ? vmem : vm_init(); // Initiallisere den vm, falls noch nicht ist!
    if(vmem == NULL) vm_init();

    int page           = address / VMEM_PAGESIZE, // Pages berechnen
        offset         = address % VMEM_PAGESIZE, // Offset berechnen
        flags          = vmem->pt.entries[page].flags, // Flags von der Page holen
        reg_loaded     = ((flags & PTF_PRESENT) == PTF_PRESENT); // Ist Present-Bit gesetzt (XX1 & 1 == 1 || XX0 & 1 == 0)

    // Wurde page nicht geladen?
    if(!reg_loaded){
        vmem -> adm.req_pageno = page;  // Adresse der Page in mmanage speichern
        kill(vmem -> adm.mmanage_pid, SIGUSR1); //und mmange zum Laden der Page aufgefordert
        sem_wait(&vmem -> adm.sema); //vmaccess blockieren, um auf das Signal von mmange zu warten
    }

    vmem -> pt.entries[page].flags |= PTF_USED; // R-Bit (Used-Bit) setzen
    /*
     * Wir prüfen, ob eine Schreiboption durchgeführt wurde
     * dies wird allerdings nur dann durchgeführt,
     * wenn wmem_write diese Funktion aufruft
     */
    if(permission)
        vmem -> pt.entries[page].flags |= PTF_DIRTY; // Dirty Bit setzen

    vmem -> adm.g_count++; // Inkrementiere den Global-Count (Zugriff auf den Speicher ist erfolgt, relevant fur LRU)
    vmem -> pt.entries[page].count = vmem->adm.g_count; // Speicher den GC-Wert im Attribut COUNT für den aktuellen Frame
    return (vmem->pt.entries[page].frame * VMEM_PAGESIZE) + offset;
}