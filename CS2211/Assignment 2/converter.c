// Written by Connor Ciavarella, Student Number: 251023554
// Written for CS 2211a Assignment 2 Part 1
// This program takes a base and an exponent and calculates the result

// includes the standard input output
#include <stdio.h>

// takes the users input for which conversion they would like to do
int userAction() {
	int userOption;
	
	printf("Please enter the integer corresponding to what action you would like to perform\n");
	printf("1 for conversion between Kilometer and Mile\n");
	printf("2 for conversion between Meter and Feet\n");
	printf("3 for conversion between Centimeter and Inch\n");
	printf("4 for conversion between Celsius and Fahrenheit\n");
	printf("5 for quit\n");
	printf("Enter any other integer to repeat the options\n");
	
	scanf(" %d", &userOption);

	return userOption;
	
}

// converts from kilometers to miles
float KiloToMile() {
	float userValue;
	float convertedValue;

	printf("Please Enter Value to be Converted:\n");
	scanf(" %f", &userValue);

	convertedValue = userValue * 0.621371;

	return convertedValue;

}

// converts from miles to kilometers
float MileToKilo() {
	float userValue;
	float convertedValue;

	printf("Please Enter Value to be Converted:\n");
	scanf(" %f", &userValue);

	convertedValue = userValue * 1.60934;

	return convertedValue;

}

// converts from meters to feet
float MeterToFeet() {
	float userValue;
	float convertedValue;

	printf("Please Enter Value to be Converted:\n");
	scanf(" %f", &userValue);

	convertedValue = userValue * 3.28084;

	return convertedValue;

}

// converts from feet to meters
float FeetToMeter() {
	float userValue;
	float convertedValue;

	printf("Please Enter Value to be Converted:\n");
	scanf(" %f", &userValue);

	convertedValue = userValue * 0.3048;

	return convertedValue;

}

// converts from centimeters to inches
float CentiToInch() {
	float userValue;
	float convertedValue;

	printf("Please Enter Value to be Converted:\n");
	scanf(" %f", &userValue);

	convertedValue = userValue * 0.393701;

	return convertedValue;

}

// converts from inches to centimeters
float InchToCenti() {
	float userValue;
	float convertedValue;

	printf("Please Enter Value to be Converted:\n");
	scanf(" %f", &userValue);

	convertedValue = userValue * 2.54;

	return convertedValue;

}

// converts from celsius to fahrenheit
float CelsiusToFahrenheit() {
	float userValue;
	float convertedValue;

	printf("Please Enter Value to be Converted:\n");
	scanf(" %f", &userValue);

	convertedValue = (userValue * (9/5.0f)) + 32;

	return convertedValue;

}

// converts from fahrenheit to celsius
float FahrenheitToCelsius() {
	float userValue;
	float convertedValue;

	printf("Please Enter Value to be Converted:\n");
	scanf(" %f", &userValue);

	convertedValue = (userValue - 32) * (5/9.0f);

	return convertedValue;

}

// main function called at start of program
int main() {
	// continues the loop until 5 is selected to exit
	while (1) {
		// Initializes result and input/output variables
		int action = userAction();
		char userConversion;
		float conversionResult;
		int inputValid = 0;

		// if action 1 is chosen asks which sub action to complete
		if (action == 1) {
			printf("Enter K for conversion from Kilometer to Mile or enter M for conversion from Mile to Kilometer\n");
			scanf(" %c", &userConversion);

			// if K then do KiloToMile
			if (userConversion == 'K') {
				conversionResult = KiloToMile();

			}

			// if M then do MileToKilo
			else if (userConversion == 'M') {
				conversionResult = MileToKilo();

			}

			// everything else is invalid
			else {
				printf("Invalid input entered\n");
				inputValid = 1;

			}
		}

		// if action 2 is chosen asks which sub action to complete
		else if (action == 2) {
			printf("Enter M for conversion from Meter to Feet or enter F for conversion from Feet to Meter\n");
			scanf(" %c", &userConversion);

			// if M do MeterToFeet
			if (userConversion == 'M') {
				conversionResult = MeterToFeet();

			}

			// if F do FeetToMeter
			else if (userConversion == 'F') {
				conversionResult = FeetToMeter();

			}

			// Everything else is invalid
			else {
				printf("Invalid input entered\n");
				inputValid = 1;

			}
		}

		// if action 3 is chosen asks which sub action to complete
		else if (action == 3) {
			printf("Enter C for conversion from Centimeter to Inch or enter I for conversion from Inch to Centimeter\n");
			scanf(" %c", &userConversion);

			// if C do CentiToInch
			if (userConversion == 'C') {
				conversionResult = CentiToInch();

			}

			// if I do InchToCenti
			else if (userConversion == 'I') {
				conversionResult = InchToCenti();

			}

			// Everything else is invalid
			else {
				printf("Invalid input entered\n");
				inputValid = 1;

			}
		}

		// if action 4 is chosen asks which sub action to complete
		else if (action == 4) {
			printf("Enter C for conversion from Celsius to Fahrenheit or enter F for conversion from Fahrenheit to Celsius\n");
			scanf(" %c", &userConversion);

			// if C do CelsiusToFahrenheit
			if (userConversion == 'C') {
				conversionResult = CelsiusToFahrenheit();

			}

			// if F do FahrenheitToCelsius
			else if (userConversion == 'F') {
				conversionResult = FahrenheitToCelsius();

			}

			// everything else is invalid
			else {
				printf("Invalid input entered\n");
				inputValid = 1;

			}

		}

		// if action 5 is chosen end the program
		else if (action == 5) {
			break;

		}

		// if action is anything else then don't print any results
		else {
			inputValid = 1;

		}

		// checks for invalid input in step 3 as outlined in program specifics and prints if input was valid
		if (inputValid == 0) {
			printf(" %f \n", conversionResult);

		}
	}
}



