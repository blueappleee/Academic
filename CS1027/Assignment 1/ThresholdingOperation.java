import java.awt.Color;

//Changes pixel colours to black or white based on their brightness score compared to the brightness threshold
//Written by Connor Ciavarella

public class ThresholdingOperation implements ImageOperation{
	
	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		//constant
		int BRIGHTNESS_THRESHOLD = 100;
		
		// 2-dimensional array to store the processed image
		Color[][] result = new Color[numOfRows][numOfColumns];

		// loops through all rows and columns
		for (int row = 0; row < numOfRows; row++) {
			for (int column = 0; column < numOfColumns; column++) {
				//pixel values
				int current_red = imageArray[row][column].getRed();
				int current_green = imageArray[row][column].getGreen();
				int current_blue = imageArray[row][column].getBlue();
				
				// determine the brightness score of the pixel
				double brightness_score = 0.21 * current_red + 0.71 * current_green + 0.07 * current_blue;
				
				// check if its over the threshold and create the pixel accordingly
				if (brightness_score > BRIGHTNESS_THRESHOLD) {
					result[row][column] = new Color(255,255,255);
				}
				
				else {
					result[row][column] = new Color(0,0,0);
				}
			}
		}
	return result;
	}
}

