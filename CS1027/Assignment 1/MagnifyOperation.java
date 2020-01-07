import java.awt.Color;

// Doubles the size of a given image
// Written by Connor Ciavarella

public class MagnifyOperation implements ImageOperation {
	
	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		// 2-dimensional array to store the processed image
		Color[][] result = new Color[2*numOfRows][2*numOfColumns];

		// loops through all rows and columns
		for (int row = 0; row < numOfRows; row++) {
			for (int column = 0; column < numOfColumns; column++) {
				// pixel values
				int current_red = imageArray[row][column].getRed();
				int current_green = imageArray[row][column].getGreen();
				int current_blue = imageArray[row][column].getBlue();
				
				// making the 3 pixels around it the same colour
				result[row*2][column*2] = new Color(current_red, current_green, current_blue);
				result[(row*2)+1][column*2] = new Color(current_red, current_green, current_blue);
				result[row*2][(column*2)+1] = new Color(current_red, current_green, current_blue);
				result[(row*2)+1][(column*2)+1] = new Color(current_red, current_green, current_blue);
				
				
			}
		}
		return result;
	}
}
