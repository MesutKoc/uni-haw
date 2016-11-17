;  This example accompanies the book
;  "Embedded Systems: Introduction to the Arm Cortex M3",
;  ISBN: 978-1469998749, Jonathan Valvano, copyright (c) 2012
;  Section 3.3.10, Program 3.12
;
;Copyright 2012 by Jonathan W. Valvano, valvano@mail.utexas.edu
;   You may use, edit, run or distribute this file
;   as long as the above copyright notice remains
;
;********************************************
; 
; 
; Assembler-Programm für ADC-Konvertierung, RMP-Praktikum
; 
; history
; 9'2014    Msl Template hat nur Ausgabe im Binärformat
; 4'2014	Cnz	Ohne Mittelwert f Aufgabenstellung   
; 7'2013	Beh	Erstversion
; 
;********************************************

	IMPORT Delay

;********************************************
; Data section, aligned on 4-byte boundery
;********************************************
	
	AREA MyData, DATA, align = 2
	
ADC3_DR    		equ		0x4001224C

PERIPH_BASE     equ		0x40000000
AHB1PERIPH_BASE equ		(PERIPH_BASE + 0x00020000)
GPIOE_BASE      equ		(AHB1PERIPH_BASE + 0x1000)
GPIOG_BASE      equ		(AHB1PERIPH_BASE + 0x1800)
GPIO_G_SET		equ		GPIOG_BASE + 0x18
GPIO_G_CLR		equ		GPIOG_BASE + 0x1A

;          RN: Direktive, um Registern ‘Namen’ zu geben
adc_wert   RN   7	; Wert!!!
adc_dr	   RN	8	; Adresse!!	
gpio_set   RN   9
gpio_clr   RN   10
;********************************************
; Code section, aligned on 8-byte boundery
;********************************************

	AREA MyCode, CODE, readonly, align = 2

;--------------------------------------------
; main subroutine
;--------------------------------------------
	GLOBAL mainASM
	
	
mainASM	PROC
        
; I/O-Adressen in Registern speichern
		ldr     adc_dr, 	=ADC3_DR        ; Adresse des ADC
		ldr     gpio_clr, 	=GPIO_G_CLR		; I/O löschen
		ldr     gpio_set, 	=GPIO_G_SET		; I/O setzen


messschleife
		mov r1 , #0     				;Zähler
		mov r2 , #0	    				;Summe der Messwerte
for_01
		ldr adc_wert ,[adc_dr]      	;Messwert ablesen //Ziel , Quelle
 		mov r0, adc_wert				;12-bit Messwert ablesen;
		lsr r0, #8						;Verkürzen auf 4-Bit Die Länge des anzuzeigenden LED-Balkens steht als Binärzahl in den vorderen 4 Bit
										;des vom AD-Wandlers gelesenen 12-Bit-Wertes (Bit 8 - 11).
		add r2, r0						;Summe bilden mit dem gemessenem Wert	
step_01
		add r1, #1						;r1 ++ der Zähler wird immer um 1 erhöht.
until_01
		cmp r1, #16						;Vergleich der von r1 und der Konstante 16, falls der Zähler kleiner ist , dann wird weiter for_01 ausgegührt
		blo for_01
endfor_01
		
		lsr r2, #4                      ;Teilung aller 16 Messungen durch 16 2^4 = 16
		mov r4, #0x01					;Wir bringen die LED's vor dem gemessenem Wert zum leuchten.
										;0000000000000001 um Balkenlänge=5 nach links geschoben ergibt
		lsl r4, r2						;0000000000100000 davon 1 subtrahiert ergibt
				
		sub r4, #0x01					;Wir ziehen das eine Bit wieder ab. 
										;0000000000011111 z.B. Balken der Länge 5

		
; LED Ausgabe				
		mov     r5, #0xffff
		strh    r5, [gpio_clr]	; LEDs löschen
		strh    r4, [gpio_set]	; Ausgabe Bitmuster
	
	
		mov     r0, #0x20       
		bl      Delay
		
		b       messschleife



forever	b		forever			; nowhere to return if main ends		
	ENDP
	
	ALIGN 4
	END
	
		