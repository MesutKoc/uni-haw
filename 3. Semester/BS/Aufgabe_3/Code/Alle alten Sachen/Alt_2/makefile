CFLAGS = -Wall -pthread
CFLAGS += -DDEBUG_MESSAGES
LDFLAGS = -lpthread
CC = /usr/bin/gcc
 
all:   vmappl mmanage_fifo mmanage_lru mmanage_clock

vmappl: vmappl.c vmaccess.c vmappl.h vmaccess.h vmem.h
	$(CC) $(CFLAGS) $(LDFLAGS) -o $@ vmaccess.c vmappl.c

mmanage_fifo: mmanage.c mmanage.h
	$(CC) $(CFLAGS) -DFIFO $(LDFLAGS) -o $@ mmanage.c

mmanage_lru: mmanage.c mmanage.h
	$(CC) $(CFLAGS) -DLRU $(LDFLAGS) -o $@ mmanage.c

mmanage_clock: mmanage.c mmanage.h
	$(CC) $(CFLAGS) -DCLOCK $(LDFLAGS) -o $@ mmanage.c

clean:
	rm -f vmappl mmanage_fifo mmanage_lru mmanage_clock