#! /bin/bash

CC=gcc 
CFLAGS= -pthread -Wall -O2 -g -DBIG=512 


mmanage.o: mmanage.c mmanage.h vmem.h
 ${CC} ${CFLAGS} -c -o mmanage.c -lm


vmappl.o: vmappl.c vmappl.h vmaccess.h
	${CC} ${CFLAGS} -c -o vmappl.c -lm
	
	
vmaccess.o: vmaccess.c vmaccess.h
	${CC} ${CFLAGS} -c -o vmaccess.c -lm
	
	
	
vmappl: vmappl.o vmaccess.o
${CC} ${CFLAGS} -o vmappl vmappl.o  vmaccess.o -lm

mmanage: mmanage.o
 ${CC} ${CFLAGS} -o mmanage mmanage.o -lm
	
	
 	
	

exit 0