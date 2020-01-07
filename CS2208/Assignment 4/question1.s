	AREA question1, CODE
	ENTRY
	
T 	EQU 't'						; assigns T to 't'
H 	EQU 'h'						; assigns H to 'h'
E	EQU 'e' 					; assigns E to 'e'
EStr EQU 0x00 					; assigns EStr to 0x00 for end of string
spce EQU ' '					; assigns spce to ' '
	
	ADR r0, STRING2 			; r0 points to the string to be inserted into
	ADR r1, STRING1 			; r1 points to the string to be read
	MOV r6, #T					; stores 't' in a register to insert into the string later

Loop1							; Loops through until the end of the string
	LDRB r3, [r1],#1			; grab the next letter in the string
	
	CMP r3, #EStr	 			; if the character is endstring then end the execution
	BEQ Park 					; branch to park to end execution
	
	CMP r3, #T					; if the letter is t then start check for 'the'
	STRBNE r3,[r0], #1			; if the letter is not t add it to the string
	BNE Loop1					; loop through if its not the to prevent unnecessary checks
	
	
	LDRB r3, [r1, #-2]! 		; grab the letter before t to ensure its space or beginning of string
	
	CMP r3, #spce 				; check if begining is a space to indicate new word
	
	CMPNE r3, #EStr 			; if the character before t is not a space then check if its begining of line 
	STRBNE r6,[r0], #1 			; if its not endline add t to the string
	ADDNE r1, #2				; add 2 to the pointer to continue with the next letter after t 
	BNE Loop1 					; loop through if its not the to prevent unnecessary checks


	LDRB r3, [r1, #2]! 			; if the t is at a start of a new word grab the letter after t to check if its h
	
	CMP r3, #H 					; check if the letter after t is h
	STRBNE r6,[r0], #1 			; if it is not h then store t and continue at the letter in the next loop
	BNE Loop1 					; loop through if its not the to prevent unnecessary checks
	
	
	LDRB r3, [r1, #1]! 			; if the letter after t is h check if the next letter is e
	
	CMP r3, #E 					; check if the letter after h is e
	STRBNE r6,[r0], #1 			; if the letter is not e then store t
	SUBNE r1, #1				; if the letter after h is not e then continue from h so subtract 1 from the pointer
	BNE Loop1 					; loop through if its not the to prevent unnecessary checks
	
	
	LDRB r3, [r1, #1]! 			; if the sequence of letters is 'the' then check if followed by space of EoS to ensure its a independent word
	
	CMP r3, #EStr  				; if the word the ends with 0x00 then park loop
	BEQ Park 					; ends execution by parking
	
	CMP r3, #spce  				; check if 'the' is followed by a space making it a word
	STRBNE r6,[r0], #1 			; if the word is not the then add t
	SUBNE r1, #2				; since the letter t is added if its not the then subtract 2 so that you continue from the next letter

	B Loop1 					; repeat the program 
	
	
Park B Park 					; parking loop
	
BoS DCB 0x00 					; start of string1
STRING1 DCB "the them the the1" ; String1
EoS DCB 0x00 					; end of string1
STRING2 space 0x7F 				; just allocating 127 bytes
	
	END