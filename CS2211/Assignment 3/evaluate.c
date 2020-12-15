// Written by Connor Ciavarella, Student Number: 
// Written for CS 2211a Assignment 3 Part 2
// This program does simple arithmetic

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

// returns the next operator
char get_op() {
	char op;
	scanf("%c", &op);

	// find a operator that is not white space and returns it
	while (op == ' ') {
		scanf("%c", &op);

	}

	return op;

}

// gets the next number in the input stream
float get_num() {
	float num;
	scanf("%f", &num);

	return num;

}

//deals with division and multiplication characters
float m_exp(float sub_exp, char op) {
	// if the op is plus, minus, or newline return it to the stdin and return the value to be dealt with by s_exp
	if (op == '+' || op =='-' || op == '\n') {
		ungetc(op, stdin);
		return sub_exp;

	}

	// if the operator is * then multiply and re call self to continue with next operator and number
	else if(op == '*') {
		float num = get_num();
		char op = get_op();
		sub_exp = sub_exp * m_exp(num,op);

	}

	// if the operator is / then divide and re call self to continue with next operator
	else if (op == '/') {
		float num = get_num();
		char op = get_op();
		num = m_exp(num, op);

		// check for division by zero and exit if so
		if (num == 0) {
			printf("Division by zero\n");
			exit(0);

		}

		sub_exp = sub_exp / num;

	}

	// everything else is invalid so exit
	else {
		printf("Invalid input entered\n");
		exit(0);

	}

	// return if others do not for some reason
	return sub_exp;

}

// calls the execution of * and / and deals with + and - along with ending at \n
float s_exp() {
	// gets initial values
	float value = get_num();
	char op = get_op();
	float num;

	// while not at a new line
	while (op != '\n') {

		// if + then add and call m_exp to check for any / and *
		if (op == '+') {
			num = get_num();
			op = get_op();

			value = value + m_exp(num,op);

		}

		// if - then subtract and call m_exp to check for any / and *
		else if (op == '-') {
			num = get_num();
			op = get_op();

			value = value - m_exp(num,op);

		}

		// if / or * allow m_exp to hand given value and op
		else if (op == '/' || op == '*') {
			value = m_exp(value, op);

		}

		// other values are invalid so exit
		else {
			printf("Invalid operator Entered\n");
			exit(0);

		}

		// get the next operator
		op = get_op();

	}

	// return value at new line
	return value;

}

// main which loops through starting the process and calls functions until user stops it
int main() {
	char userContinue;

	while (1) {
		// start execution
		float value = s_exp();

		// output result
		printf(" %f\n", value);

		// get user continue value
		userContinue = continueInput();

		//if no then break
		if (userContinue == 'N') {
			break;

		}
	}
}
