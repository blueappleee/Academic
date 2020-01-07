// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS 2211a Assignment 3 Part 2
// This program takes a integer and outputs the seven segment display version

// includes the standard input output and library
#include <stdio.h>
#include <stdlib.h>

// function determines if the user would like to continue operation of the program
char continueInput() {
	char userAnswer;
	printf("Would you like to continue? Enter Y for yes or N for no\n");

	scanf(" %c", &userAnswer);

	// if the answer is yes or no then return the answer
	if (userAnswer == 'Y' || userAnswer == 'N') {
		return userAnswer;

	}

	// if the answer is something else then print a error statement and call self again to get answer
	else {
		printf("Invalid Value entered\n");

		return continueInput();

	}
}

// main which processes the array which gives the numbers in 7 segment version
int main() {
	// define the segments for 0-9 and plus or minus for 10 and 11
	const char segments[12][3][3] = {{{' ', '_', ' '}, {'|', ' ', '|'}, {'|', '_', '|'}},
				{{' ', ' ', ' '}, {' ', ' ', '|'}, {' ', ' ', '|'}},
				{{' ', '_', ' '}, {' ', '_', '|'}, {'|', '_', ' '}},
				{{' ', '_', ' '}, {' ', '_', '|'}, {' ', '_', '|'}},
				{{' ', ' ', ' '}, {'|', '_', '|'}, {' ', ' ', '|'}},
				{{' ', '_', ' '}, {'|', '_', ' '}, {' ', '_', '|'}},
				{{' ', '_', ' '}, {'|', '_', ' '}, {'|', '_', '|'}},
				{{' ', '_', ' '}, {' ', ' ', '|'}, {' ', ' ', '|'}},
				{{' ', '_', ' '}, {'|', '_', '|'}, {'|', '_', '|'}},
				{{' ', '_', ' '}, {'|', '_', '|'}, {' ', '_', '|'}},
				{{' ', ' ', ' '}, {'_', '|', '_'}, {' ', '|', ' '}},
				{{' ', ' ', ' '}, {'_', '_', '_'}, {' ', ' ', ' '}}};

	// until the user breaks
	while(1) {
		// initialize variables
		char userContinue;
		int atPos;

		int userInputValue;
		int InputValueConversion;
		int InputValue;
		int resultSize;
		int sizeOfInput = 0;

		// get user input and clone to two variables
		scanf(" %d", &userInputValue);

		InputValueConversion = userInputValue;
		InputValue = userInputValue;

		// determine number of digits in the input by dividing by 10 repeatedly
		while (userInputValue != 0) {
			userInputValue = userInputValue / 10;
			sizeOfInput++;

		}

		// create a array of input size
		int userInteger[sizeOfInput];

		// set the size for the result array and if the value is not zero include a space for the sign
		if (InputValueConversion != 0) {
			resultSize = sizeOfInput + 1;

		}

		else {
			resultSize = 1;

		}

		// create the result array
		char result[resultSize][3][3];

		// make one of the value clones positive if its negative
		if (InputValue < 0) {
			InputValueConversion = InputValueConversion * -1;

		}

		// get each digit and put it in the array by taking modulo and reversing the array and dividing my 10 to not get same value repeatedly
		if (InputValue != 0) {
			for (int i = 0; i < sizeOfInput; i++) {
				userInteger[sizeOfInput -1 - i] = (InputValueConversion % 10);
				InputValueConversion = InputValueConversion / 10;

			}
		}

		// if zero then add zero to the array
		else {
			userInteger[0] = 0;

		}

		//add either plus or minus to the array is the value is not zero
		if (InputValue != 0) {
			if (InputValue < 0) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						result[0][i][j] = segments[11][i][j];

					}
				}
			}

			else {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						result[0][i][j] = segments[10][i][j];

					}
				}
			}

			// add the seven segmented numbers to the result array based on the input array
			for (int i = 1; i < resultSize; i++) {
				atPos = userInteger[i - 1];

				for (int j = 0; j < 3; j ++) {
					for (int k = 0; k < 3; k++) {
						result[i][j][k] = segments[atPos][j][k];

					}
				}
			}
		}

		// add zero to the array if the input was zero
		else {
			for (int j = 0; j < 3; j ++) {
				for (int k = 0; k < 3; k++) {
					result[0][j][k] = segments[0][j][k];

				}
			}
		}

		// print the result array
		for (int i = 0; i < 3; i++) {
			if (i != 0) {
				printf("\n");
			}

			for (int j = 0; j < resultSize; j ++) {
				printf(" ");
				for (int k = 0; k < 3; k++) {
					printf("%c", result[j][i][k]);
				}
			}
		}

		printf("\n");

		// determine if user would like to continue
		userContinue = continueInput();

		// if no then break
		if (userContinue == 'N') {
			break;

		}

	}
	// return from main
	return 0;
}




