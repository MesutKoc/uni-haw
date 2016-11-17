#ifndef TASK2MAIN_H
#define	TASK2MAIN_H

void* p_1_w(void *pid); // P1
void* p_2_w(void *pid); // P2
void* consumer(void *pid); // C Prozess
void* control(void *pid);  // Control
void* producing(void *pid, pthread_cond_t *restart, int *prod_stopped, char a_begin, char a_end, int sleep_time);

char get_char();

#endif