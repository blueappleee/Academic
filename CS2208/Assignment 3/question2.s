	AREA question2, CODE, READONLY
	ENTRY
	
	ADR r5, STRING						; r5 points to the string
	ADR r6, EoS							; r6 points to the end of the line character
	
Loop1									; this loops until forward and backwards point to same character in the string
Load1									; this loops till a forward valid byte is found
	LDRB r3, [r5], #1 					; r3 becomes the forward byte
	
	CMP r3, #90 						; check if r3 is below 90 meaning its uppercase or less
	ADDLE r3, r3, #32 					; if it is uppercase add 32 to make it lowercase or if its below uppercase ASCII then it wont pass the next statement
	
	CMP r3, #'a' 						; check if r3 is at least 'a'
	RSBGES r1, r3, #'z' 				; if r3 is 'a' or greater check if its 'z' or less to check if its a letter
	
	BLT Load1 							; if r3 is not a lowercase letter then load the next forward byte and repeat
	
Load2									; this loops till a back valid byte is found
	LDRB r4, [r6], #-1 					; r4 becomes the backward byte
	
	CMP r4, #90 						; check if r4 is below 90 meaning its uppercase or less
	ADDLE r4, r4, #32 					; if uppercase or less add 32 to make it lowercase or if its below uppercase ASCII then it wont pass the next statement
	
	CMP r4, #'a' 						; check if r4 is at least 'a'
	RSBGES r1, r4, #'z' 				; if r4 is 'a' or greater check if its 'z' or less to check if its a letter
	
	BLT Load2 							; if r4 is not a lowercase letter it is invalid so load the next backward byte and repeat
	
	CMP r3, r4 							; check if the forward and backward character are the same

	MOVNE r0, #2 						; if the characters are not the same then the string is not palindrome 
		
	CMP r5,r6 							; check if the forward and backward pointers have crossed
	
	BLT Loop1 							; if the pointers have not crossed they do not point to the same thing so check the next value
	
	TEQ r0, #2 							; check if the string has been labelled as not a palindrome
	
	MOVNE r0, #1 						; if it is not labeled then its a palindrome 
	
Park B Park 							; parking loop
	
STRING DCB "~He lived as a devil, eh?" 	;string that tests for capital to lowercase, above 'z' and below 'a', result is palindrome
EoS DCB 0x00 							;end of string
	
	END