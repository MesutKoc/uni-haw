#!/usr/bin/make -f
all: task2Main

CC = gcc
INCLUDE = .
CFLAGS = -Wall -std=c99


task2Main : task2Main.o 
	$(CC) -o task2Main task2Main.o -pthread
task2Main.o : task2Main.c task2Main.h
	  $(CC) -I$(INCLUDE) $(CFLAGS) -c task2Main.c
install: task2Main
