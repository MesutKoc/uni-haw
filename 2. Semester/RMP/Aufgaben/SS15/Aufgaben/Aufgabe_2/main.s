;******************** (C) COPYRIGHT HAW-Hamburg ********************************
;* File Name          : main.s
;* Author             : Mesut Koc & Anton Kirakozov
;* Version            : V1.0
;* Date               : 11.05.2015
;* Description        : This is a simple main.
;					  : The output is send to UART 1. Open Serial Window when 
;					  : when debugging. Select UART #1 in Serial Window selection.
;					  :
;					  : Replace this main with yours.
;
;*******************************************************************************

;********************************************
; Wir sagen hier, dass bestimmte Werte EQU
; sind. Zum Beispiel ist 1 = TRUE.
;********************************************

TRUE		EQU 	1
FALSE		EQU		0
DATASIZE	EQU		4	; Byte=1, Halbwort=2, Wort=4 

;********************************************
; Hier definieren wir unsere "Zahlentabelle"
;********************************************
	
	AREA MyData, DATA,  align = 4

DataList		DCD       	35, -1, 13, -4096, 511, 101, -3, -5, 0, 65
DataListEnd		DCD			0

	GLOBAL	DataList    
	GLOBAL	DataListEnd  

;--------------------------------------------
; Hier fängt unsere Main an
;--------------------------------------------
	AREA MyCode, CODE, readonly, align = 4
    GLOBAL main
    
; r0 - Aktueller Wert
; r1 - Folgender Wert
; r2 - Zeiger auf den ersten Wert
; r3 - Zeiger auf den letzten Wert
; r4 - Getauscht	


main PROC
									[35, -1, 13, -4096, 511, 101, -3, -5, 0, 65]  
	mov r4 , #TRUE				 ; Hier setzen wir die Variable ,,getauscht" auf ,,Ja" -> also TRUE. 
	
while_01						 ; Hier fängt unsere Kopfgesteuerteschleife an
	cmp r4 , #FALSE              ; Hier bestimmen wir die Abbruchbedingung
	beq endwhile_01				 ; wenn getauscht auf false ist, springe zum Ende der Schleife (Abbruchanweiseung) Rn-N = 0.
	
	mov r4 , #FALSE			     ; Setze ,,getauscht" auf FALSE.
	ldr r2, = DataList			 ; Objekt referenziert auf DataList , Laden die Adressen von Datalist.
    ldr r3, = DataListEnd		 ; bjekt referenziert auf DataListEnd 
	sub r3, #DATASIZE			 ; Zeiger auf den letzten Wert ermitteln
while_02
    cmp r2,r3					 ; Wir vergleichen, ob der Zeiger auf den letzten Wert zeigt 
	beq endwhile_02
	 
	ldr r0,[r2]				     ; Wir laden das aktuelle Element in r4  r0 = 35;
	ldr r1,[r2,#DATASIZE]        ; Wir gehen ein Wort weiter und laden das Wort in das Register 1, Es wird erst das in den eckigen Klammern ausgeführt. r1= [-1]
	 
if_01
	cmp r0,r1				 	 ; Vergleiche aktuellen Wert mit folgendem Wert r0 =[35] r1= [-1].
	ble endif_01			 	 ; Abbruchbedingung, wenn aktueller Wert < folgender Wert ist lower equal Rn - N >=0.
	 
then_01					    	 ; Wenn aktueller Wert > folgender Wert
	str r0,[r2,#DATASIZE]        ; R2 ist unser Zeiger und geh ein wort weiter, offset update. r0= [-1]
	str r1,[r2]			         ; r1= [35].
	mov r4,#TRUE			     ; 
	b endif_01				     ;
endif_01					     ;
	 
	add r2,#DATASIZE    		 ; Zeiger auf den folgenden Eintrag setzen Die addresse von -1 ist da jetzt drin zB.
	b while_02					 ; Innere Schleife durchführen
	 
endwhile_02						 ; Innere Schleife fertig
	b while_01			
	
endwhile_01						 ; Äußere Schleife fertig
	

loop
	b loop
	
    ENDP
	ALIGN 4
		
    END
		
		
		