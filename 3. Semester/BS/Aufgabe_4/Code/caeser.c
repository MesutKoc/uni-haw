/*
 * caeser.c
 *
 *  Created on: 06.12.2014
 *      Author: torbenhaug
 */
#include "caeser.h"

/**
 * A table of chars which are being shifted by caeser encryption.
 * Chars not included in <shift_table[]> are not being encrypted by caeser.
 */
const char table[] = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz"};

/*
 * Gibt den Index von einem gegebenen char (in einem Array) zurück
 * @param: a[], the size from the array, the value to search
 * @return: the index or in worst-case -1
 */
int find_index(const char a[], const int size, const char value) {
	int i = 0;
	for (i = 0; i < size; i++) if (a[i] == value) return i;
	return(-1);
}

/**
 * Shifts the char code of <move_char> by <shift> times,
 * if <move_char> is present in <shift_table>. Otherwise <move_char> is not being shifted.
 */
char caeser(char move_char, int shift) {
	int index = find_index(table, NELEMS(table), move_char);
	if (index >= 0) {
		int new_index = (index + shift);
		while (new_index < 0)
			new_index = new_index + NELEMS(table);
		// end while
		new_index %= NELEMS(table);
		move_char = table[new_index];
	}
	return move_char;
}