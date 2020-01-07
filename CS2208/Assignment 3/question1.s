	AREA question1, CODE, READONLY
	ENTRY

	MOV r1, #12 			; loop1 counter set at the length of a upc code
	ADR r5, UPC 			; r5 points to the UPC code
	
Loop1						; loops through 12 times adding each upc value to its respective sum
	LDRB r4, [r5], #1 		; r4 becomes the upc numbers value 
	SUB r4, r4, #48 		; subtract 48 to get int value from ascii value
	
	TST r1, #1 				; determines if the counter is even or odd based on if the last bit is 1 then its odd otherwise its even
	
	ADDEQ r2, r2, r4 		; add to first sum at r2 if even
	ADDNE r3, r3, r4 		; add to second sum at r3 if odd  
	
	SUBS r1, r1, #1 		; de increments the loop counter by 1 and updates flags for comparison
	
	BNE Loop1 				; continue the loop until r1 is zero
	
	ADD r2, r2, r2, LSL #1 	; multiply r2 by 2 and add r2 which is equivilant to multiplying by three for the first sum 
	ADD r2, r2, r3 			; adds the first sum and the second sum to get the final sum stored in r2
	
Loop2 						; loops through subtracting 10 from the final sum each time to determine if the value is divisible by 10
	SUBS r2, r2, #10 		; subtract 10 from the final sum and store in r2 then set flags for compare 
	
	MOVEQ r0, #1			; if zero then it is divisible so store 1 in r0 because the upc is valid
	MOVLT r0, #2 			; if less then zero its not divisible by 10 so store 2 in r0 because the upc is invalid
	
	BGT Loop2 				; continue loop until r2 is either zero or less then zero 
	
Park B Park 				; parking loop

UPC DCB "065633454712" 		;UPC string 

	END