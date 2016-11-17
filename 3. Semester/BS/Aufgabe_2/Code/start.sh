echo "compile Code..."

gcc -pthread -Wall -O2 -g -DBIG=512 -o aufgabe2 aufgabe2.c && ./aufgabe2

#
#VERSION = 4.8
#CC	= /usr/bin/gcc
#CFLAGS	= -Wall -g -lpthread -D_REENTRANT -DVERSION=\"$(VERSION)\"
#LDFRALGS= -lm -lpthread `gtk-config --cflags` `gtk-config --libs`

#OBJ = consumer.o control.o getch.o main.o producer.o

#compile: $(OBJ)
	#$(CC) $(CFLAGS) -o compile $(OBJ) $(LDFLAGS)

#%.o: %.c
	#$(CC) $(CFLAGS) -c $<