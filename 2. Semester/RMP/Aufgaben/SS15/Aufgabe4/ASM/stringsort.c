#include "stringsort.h"

// Funktion für die Ausgabe des übergebenen Arrays
void PrintStringListe(char *pStringArray[]) {
    unsigned int i = 0;
    // Traversierung über das Array bis der letzte String gleich "\0\0" ist
    while (strcmp(pStringArray[i], "\0\0")) {
        printf("%s\n", pStringArray[i]);
        i++;
    }
}

// Bubblesort von dem übergebenen Array von Strings.
void SortiereStrings(char *pStringArray[]) {
    bool isExchanged = TRUE;

    while (isExchanged) {
        isExchanged = FALSE;
        unsigned int i = 1;
        while (strcmp(pStringArray[i], "\0\0")) {
            // Vergleichen 2 nebenstehenden Strings miteinander und tauschen sie um, 
            // wenn der erste String grösser als zweiter String ist (die Zahl in dem String)
            int curNum = getNum_asm(pStringArray[i]);
            int prevNum = getNum_asm(pStringArray[i - 1]);
            // Die Funktion getNum (Assembler) liefert uns eine dezimale Zahl aus dem String zurück

            if (prevNum > curNum) {
                // Umtausch der Strings mit Hilfe des Hilfspointer
                char *pTemp = pStringArray[i - 1];
                pStringArray[i - 1] = pStringArray[i];
                pStringArray[i] = pTemp;
                isExchanged = TRUE;
            }
            i++;
        }
    }
}
