#include "stringsort.h"

char *pMeineStrings[] = {
    "Haller       25 EUR",
    "Kandinsky    13 EUR",
    "Brombach      5 EUR",
    "Zaluskowski 120 EUR",
    "Osman        17 EUR",
    "\0\0"
};

int main(void) {    
    Init_TI_Board();

    PrintStringListe(pMeineStrings);
    printf("\n");
    SortiereStrings(pMeineStrings);
    PrintStringListe(pMeineStrings);
    return 0;
}
