import java.awt.Color;

//adjusts the brightness of pixels based on their distance to the upper left corner
//Written by Connor Ciavarella

public class AdjustmentOperation implements ImageOperation {
	//determines the vector distance of the pixel from the upper left corner based on its x and y components
	public double distance(int row, int column) {
		// squares both components and then takes the square root which is then returned
		int row_square = row*row;
		int column_square = column*column;
		
		double distance = Math.sqrt((row_square + column_square));
		
		return distance;
	}
	
	//determines the maximum distance a pixel can be from the upper left corner based on the final x and final y components of the image
	public double maxdistance(int numOfRows, int numOfColumns) {
		// subtract 1 because at the first pixel the value is zero but numOfRows/Columns counts that as a distance of 1
		int rownum = numOfRows - 1;
		int columnnum = numOfColumns -1;
		
		// squares both components and then takes the square root which is then returned
		int row_square = rownum * rownum;
		int column_square = columnnum * columnnum;
		
		double max_distance = Math.sqrt((row_square + column_square));
		
		return max_distance;
	}
	
	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		//constant
		double MAX_DISTANCE = maxdistance(numOfRows, numOfColumns);
		
		// 2-dimensional array to store the processed image
		Color[][] result = new Color[numOfRows][numOfColumns];

		// loops through all rows and columns
		for (int row = 0; row < numOfRows; row++) {
			for (int column = 0; column < numOfColumns; column++) {
				
				//pixel values
				int current_red = imageArray[row][column].getRed();
				int current_green = imageArray[row][column].getGreen();
				int current_blue = imageArray[row][column].getBlue();
				
				double distance_value = distance(row, column);
				
				double adjustBrightness = distance_value/ MAX_DISTANCE;
				
				// sets the values for the new pixel
				int new_red = (int)Math.round((current_red * adjustBrightness));
				int new_green = (int)Math.round((current_green * adjustBrightness));
				int new_blue = (int)Math.round((current_blue * adjustBrightness));
				
				// creates the new pixel at its spot in the image
				result[row][column] = new Color(new_red, new_green, new_blue);
				
			}
		}
	return result;
	}
}
