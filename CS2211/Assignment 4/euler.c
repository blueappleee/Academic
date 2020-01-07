// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS 2211a Assignment 4 Part 2
// This program calculates eulers value to a user given accuracy

// includes input output library
#include <stdio.h>

// gets the value from the user
double userValue() {
	// uses double so number can be very small
	double userInput;

	printf("Please enter a value such that the smallest calculation of e will be value input <= 1/x! or enter 0 to exit.\n");

	scanf("%lf", &userInput);

	return userInput;

}

// calculates the factorial of a value
long long factorial(int value) {
	// uses long long so number can be large
	long long factCalc = value;

	// multiplies each number below the value against the value to calculate the factorial
	for (int i = 1; i < value; i++) {
		factCalc = factCalc * i;

	}

	return factCalc;

}

// calculates euler value based on user input
double calculate(double minValue) {
	//euler value starts at 1 since add 1
	double eValue = 1;
	// counter starts at one since no point in adding zero
	int counter = 1;
	// gets the factorial value of the counter
	long long factValue = factorial(counter);

	// checks if the value is less the user input value and if so does not add
	while (1.0/factValue >= minValue)  {
		// add 1/ factorial of the counter
		eValue += 1.0/factValue;

		counter += 1;

		// recall factorial on new counter value to add next value to euler number
		factValue = factorial(counter);

	}

	return eValue;

}

// calls functions in a infinite loop to take user input until the user chooses to exit
int main() {
	while (1) {
		// take input
		double input = userValue();

		// if input is not zero calculate eulers value
		if (input != 0) {
			double euler = calculate(input);
			printf("Euler's value is: %lf\n", euler);

		}

		// if input is zero break
		else {
			break;

		}
	}
}
