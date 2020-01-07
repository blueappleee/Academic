// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS 2211a Assignment 2 Part 2
// This program takes a base and an exponent and calculates the result

// includes the standard input output
#include <stdio.h>

// this function takes the base and exponent and recursively calculates the result in logarithmic time
float exp_calculator (float base, int exponent) {
	// if the exponent = 0 then the result is always 1
	if (exponent == 0) {
		return 1;

	}

	// if the exponent == 1 the result is the base
	else if (exponent == 1) {
		return base;

	}

	// if the exponent is even the result is recursively calculated by multiplying the result of the base with the exponent/2
	else if (exponent % 2 == 0) {
		return exp_calculator(base, exponent/2) * exp_calculator(base, exponent/2);

	}

	// if the exponent is odd the result is recursively calculated by multiplying the result of the base with the exponent/2 and by the base
	else {
		return exp_calculator(base, (exponent - 1)/2) * exp_calculator(base, (exponent - 1)/2) * base;

	}
}

// main program that runs at the start and calls the function to calculate the result
int main() {
	// initializes the input and output variables
	float userBase;
	int userExponent;
	float result;

	// takes the base input
	printf("Please Enter The Base:\n");
	scanf(" %f", &userBase);
	
	// takes the exponent input
	printf("Please Enter The Exponent:\n");
	scanf(" %d", &userExponent);
	
	// checks to ensure that the base and exponent are positive
	if (userBase > 0 && userExponent >= 0) {
		// calls the calculator and assigns the result
		result = exp_calculator(userBase, userExponent);

	}
	
	// prints the result
	printf("%f \n", result);
	return 0;

}

