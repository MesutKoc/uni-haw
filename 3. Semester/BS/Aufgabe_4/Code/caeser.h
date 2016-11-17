/*
 * caeser.h
 */

#ifndef SRC_CAESER_H_
#define SRC_CAESER_H_
#define NELEMS(x)  ((sizeof(x) / sizeof(x[0])-1))

char caeser(char move_char, int shift);

#endif 