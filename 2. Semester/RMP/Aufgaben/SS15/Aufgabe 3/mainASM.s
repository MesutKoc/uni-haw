;  This example accompanies the book
;  "Embedded Systems: Introduction to the Arm Cortex M3",
;  ISBN: 978-1469998749, Jonathan Valvano, copyright (c) 2012
;  Section 3.3.10, Program 3.12
;
;   Copyright 2012 by Jonathan W. Valvano, valvano@mail.utexas.edu
;   You may use, edit, run or distribute this file
;   as long as the above copyright notice remains
;
;**************************************************************
;
; HAW Hamburg, Department Informatik
; Praktikum Rechnerstrukturen und Maschinennahe Programmierung
;
; Zweck:        Asm-Programm zu A3: 'Reaktionstester'
; Dateiname:    mainASM.s
; History:
;
; 5'2014  Cnz    Aufbereitung für Aufgabenstellung
; 7'2013  Beh    Erstversion
;
;**************************************************************


        IMPORT  TFT_puts
        IMPORT  TFT_cls
        IMPORT  TFT_gotoxy
        IMPORT  Delay

;********************************************
; Data section, aligned on 4-byte boundary
;********************************************
   
    AREA MyData, DATA, align = 2
   
ADC3_DR         equ         0x4001224C

PERIPH_BASE     equ      0x40000000
AHB1PERIPH_BASE equ      (PERIPH_BASE + 0x00020000)
   
;blaue LEDs: output
GPIOG_BASE      equ      (AHB1PERIPH_BASE + 0x1800)
GPIO_G_SET        equ      GPIOG_BASE + 0x18
GPIO_G_CLR        equ      GPIOG_BASE + 0x1A
GPIO_G_PIN        equ      GPIOG_BASE + 0x10

;rote LEDs / Taster: input
GPIOE_BASE      equ      (AHB1PERIPH_BASE + 0x1000)
GPIO_E_PIN      equ      GPIOE_BASE + 0x10

Start_Text      DCB     "Zum Starten  - die S7 Taste druecken\0"
Achtung_Text    DCB        "Achtung !!!\0"
Stop_Text        DCB     "Stopped !!!\0"



;********************************************
; Code section, aligned on 4-byte boundary
;********************************************

    AREA MyCode, CODE, readonly, align = 2

;--------------------------------------------
; main subroutine
;--------------------------------------------
            GLOBAL mainASM
           
   
;---------------------------------------------------
;--- Testet ob die S0 Taste gedrueckt wurde
;--- in:  void
;--- out: R0 = 0  S0 Taste wurde nicht gedrueckt
;--- out: R0 = 1  S0 Taste wurde gedrueckt
;!!!! für A3 nach S7 ändern!!!!
;---------------------------------------------------
testIfPushButtonPressed
            
            push {r3, r4}             ;gerade Anzahl von Registern!              
           
            mov R0, #0x00             ;default Wert
            ldr R3, =GPIO_E_PIN       ;Tasten lesen
            ldr R4, [R3]
            and R4, #0x80             ;S0 maskieren --> S7!
            cmp r4, #0x80             ;S0 testen --> S7!
            movne r0, #0x01           ;Taster gedrückt -> Pin ist 0
           
           
            pop {r3, r4}          
            bx   LR
   
   
;---------------------------------------------------
;--- Laesst die Anzahl LEDs leuchten, die in R0 steht 
;--- in:  R0 = Anzahl LEDs
;--- out: void
;---------------------------------------------------   
outputLEDBar

            push {r3-r5, lr}         ;gerade Anzahl von Registern!   
         
            ldr  R3, =GPIO_G_CLR    ;alle LEDs loeschen
            mov  R4, #0xffff
            strh R4, [R3]
           
           
            mov r4, #0x01
            lsl    r4, r4, r0
            sub    r4, #0x01
           
            ldr  R3, =GPIO_G_SET    ;LEDs anzeigen
            strh R4, [R3]
           
            pop  {r3-r5, lr}
            bx   LR
       
;---------------------------------------------------
;--- Ermittelt ob alle LEDs leuchten
;--- in:  void
;--- out: R0 = 0 Sie leuchten nicht alle
;--- out: R0 = 1 Sie leuchten alle
;---------------------------------------------------           
LEDBarEndReached

            push {r3, r4, r5, lr} ;gerade Anzahl von Registern!   
           
            mov R0, #0x01         ;default Wert
            ldr R3, =GPIO_G_PIN   ;LEDs lesen
            ldr R4, [R3]
            ldr R5, =0xffff
            and R4, R5            ;LEDs maskieren
            cmp r4, r5            ;LEDs testen
            movne r0, #0
           
           
            pop {r3, r4, r5, lr}
            bx   LR
           
;---------------------------------------------------
;--- Delay, ohne dass sich Register aendern
;--- in:  R0 = Millisekunden
;--- out: void
;---------------------------------------------------           
SafeDelay
            push {r1-r5, lr}          ;gerade Anzahl von Registern!

            bl    Delay

            pop  {r1-r5, lr}
            bx    LR

;---------------------------------------------------
;--- TFT_cls, ohne dass sich Register aendern
;--- in:  void
;--- out: void
;---------------------------------------------------           
SafeTFT_cls
            push {r1-r5, lr}          ;gerade Anzahl von Registern!

            bl    TFT_cls

            pop  {r1-r5, lr}
            bx    LR

;---------------------------------------------------
;--- TFT_gotoxy, ohne dass sich Register aendern
;--- in:  R0 = x-Koordinate
;--- in:  R1 = y-Koordinate
;--- out: void
;---------------------------------------------------           
SafeTFT_gotoxy
            push {r1-r5, lr}          ;gerade Anzahl von Registern!

            bl    TFT_gotoxy

            pop  {r1-r5, lr}
            bx    LR

;---------------------------------------------------
;--- TFT_puts, ohne dass sich Register aendern
;--- in:  R0 = Stringadresse
;--- out: void
;---------------------------------------------------           
SafeTFT_puts
            push {r1-r5, lr}          ;gerade Anzahl von Registern!

            bl    TFT_puts

            pop  {r1-r5, lr}
            bx    LR

;---------------------------------------------------
;--- Gibt Text auf dem Bildschirm aus
;--- in:  R0 = Adresse des Textes
;--- in:  R1 = x Koordinate (1..40)
;--- in:  R2 = y Koordinate (1..16)
;--- out: void
;---------------------------------------------------   
SafeTFT
            push {r0-r4, lr}             ;gerade Anzahl von Registern!
           
            mov R4, R0                   ;Adresse des Strings sichern   
            bl  SafeTFT_cls              ;TFT loeschen
            mov R0, R1                   ;Cursor positionieren
            mov R1, R2
            bl  SafeTFT_gotoxy
            mov R0, R4                   ;Text anzeigen
            bl    SafeTFT_puts
           
            pop  {r0-r4, lr}
            bx    LR
           
           
;-------------------------------------------------------------------------------------------------   
mainASM        PROC
;Dies ist die Hauptaufgabe; nur 3 Codeschnipsel für die Anwendung der Bibliothek
           
REPEAT_01	 									;Zustand: START_WAIT
			ldr  R3, =GPIO_G_CLR    			;alle LEDs loeschen
            mov  R4, #0xffff
            strh R4, [R3]
			
            ldr R0, =Start_Text					;Starttext anzeigen
            mov R1, #4
            mov R2, #16
            bl    SafeTFT

WHILE_02										;Polling von Taste S7
            bl testIfPushButtonPressed
            cmp r0, #0x01
            beq ENDWHILE_02

DO_02       b WHILE_02

ENDWHILE_02										;Zustand: START_DELAY
            ldr r0, =Achtung_Text				;Achtung ausgeben
            mov R1, #29                        ;verschieben die Register für x und y Koordinaten
            mov R2, #16					    ;für die Anzeige des Textes
            bl    SafeTFT

			mov    r8, #0						;LED-Balken loeschen
            mov r0, #0           
            bl    outputLEDBar

            mov R0, #3000						;3s warten
            bl  SafeDelay

WHILE_03										;Zustand: RUN
            bl LEDBarEndReached					;Balkenlaenge = 15?
            cmp r0, #1
            beq ENDWHILE_03
           
            bl testIfPushButtonPressed			;Taste S7 gedrueckt?
            cmp r0, #1
            beq ENDWHILE_03
           
           
DO_03
            mov R0, #20							;20ms warten
            bl SafeDelay

            add r8, r8, #1						;Balkenlaenge um 1 erhoehen
            mov r0, r8

            bl outputLEDBar						;Balkenlaenge ausgeben
            b WHILE_03

ENDWHILE_03										;Zustand: SHOW_RESULT
            ldr r0, =Stop_Text					;Stopped anzeigen
            bl SafeTFT

            mov R0, #3000						;Zeitverzoegerung 3s
            bl SafeDelay

UNTIL_01
            b REPEAT_01       

       
forever     b		forever						; nowhere to retun if main ends       



            ENDP
   
            ALIGN 4
            END