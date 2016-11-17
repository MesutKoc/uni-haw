#ifndef STRINGSORT_H_INCLUDED
#define STRINGSORT_H_INCLUDED

#include <stdio.h>
#include "main.h"
#include <string.h>
#include "TI_Lib.h"
#include "tft.h"

#define TRUE 1
#define FALSE 0
typedef int bool;  // eigener Typ

void PrintStringListe(char *pStringArray[]);
int getNum(char *pString);
void SortiereStrings(char *pStringArray[]);

#endif // STRINGSORT_H_INCLUDED
