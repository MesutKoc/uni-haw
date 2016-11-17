;********************************************
; Data section, aligned on 4-byte boundary
;********************************************
	
	AREA MyData, DATA, align = 2
TRUE 	 equ 	1
FALSE 	 equ    0
DATASIZE equ    1
	
ACCU 	   RN   5
POINTER    RN   6
CHAR       RN   7

;********************************************
; Code section, aligned on 4-byte boundary
;********************************************

	AREA MyCode, CODE, readonly, align = 2

	GLOBAL getNum_asm

;---------------------------------------------------		
;--- Überprüfung, ob R0 eine dezimale Ziffer enthält
;--- in: R0: Charakter, der wir überprüfen
;--- out: R1 = 1 R0 enthält eine dezimale Zahl	
;--- out: R1 = 0 R0 enthält keine dezimale Zahl
;---------------------------------------------------	
is_Decimal
	push {LR, R0}
	mov R1, #FALSE ; Behauptung, dass es keine Ziffer ist
if_02
	cmp R0, #'0'   ; eine dezimale Ziffer liegt zwischen '0' und '9' in ASCII
	bhs then_02    ; >= '0'?
	b endif_02
then_02
if_03
	cmp R0, #'9'   ; eine dezimale Ziffer liegt zwischen '0' und '9' in ASCII
	bls then_03    ; <= '9'?
	b endif_03     ; Sonst keine Ziffer
then_03
	mov R1, #TRUE  ; R0 ist eine dezimale Ziffer
endif_03
endif_02
	pop {LR, R0}
	bx LR

;---------------------------------------------------
;--- Konversion: ASCII Charakter -> eine dezimale Ziffer
;--- in: R0: Charakter für die Konversion
;--- out: R1: Integer Wert
;---------------------------------------------------
charToInt
	push {LR, R0}
	mov R1, R0     ; R1 <- ASCII Wert von R0
	sub R1, #'0'   ; Differenz zwischen ASCII R0 und ASCII '0'	
	pop {LR, R0}
	bx LR

;---------------------------------------------------
;--- Suche und Konversion der dezimalen Ziffern in einem String 
;--- in: R0 (Stringadresse)
;--- out: R0 (Zahlenwert)
;--- Verwendete Register retten und restaurieren.
;---------------------------------------------------
getNum_asm	PROC
	push {R1-R4}       ; Eine per C-Konvention (werden kurzfristig verwendet)
	push {R5-R7, LR}   ; verwendete Register retten
	mov ACCU, #0       ; Initialisierung des Akku (Ergebnis)
for_01
	mov POINTER, R0    ; Pointer <- Startadresse des Strings
step_01
	ldrb CHAR, [POINTER], #DATASIZE ; Lesen des Charakters im String und setzen Pointer auf 1 Byte weiter
until_01
	cmp CHAR, #'\0'    ; Ende des Strings?
	beq endfor_01
	bne do_01
do_01
if_01
	mov R0, CHAR       ; Speichern den Charakter in R0 für das Unterprogramm (Call by value)
	bl is_Decimal
	cmp R1, #TRUE      ; Ist dieser Charakter eine dezimale Ziffer?
	beq then_01
	bne endif_01	
then_01
	bl charToInt            ; Konversion: Char -> Integer
	mov R4, #10             ; Basis für dezimale Zahlen
	mla ACCU, ACCU, R4, R1	; Akkumulieren den Wert aus mehreren dezimalen Ziffern
                                ; ACCU * R4 + R1
endif_01
	b step_01
endfor_01
	mov R0, ACCU
    pop {R5-R7, LR}
	pop {R1-R4}
    bx LR
	

forever	b	forever		; nowhere to return if main ends		
		ENDP
		ALIGN  4
		END