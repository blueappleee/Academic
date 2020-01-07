import java.awt.Color;

// Detects contours in an image and changes those pixels to black and the rest to white by determining the color distance of one pixel to that of its neighbours 
//Written by Connor Ciavarella

public class ContourOperation implements ImageOperation  {
	
	//determines color distance based on the RGB values
	public double Colordistance(int current_red, int current_green, int current_blue, int red, int green, int blue) {
		// finds the delta between the current and neighbour pixels which is then squared
		int redDistance = (current_red - red) * (current_red - red);
		int greenDistance = (current_green - green) * (current_green - green);
		int blueDistance = (current_blue - blue) * (current_blue - blue);
		
		// adds up the squared values then squareroots them to determine the distance
		double distanceAdd = redDistance + greenDistance + blueDistance;
		
		double distance = Math.sqrt(distanceAdd);
		
		return distance;
			
	}
	
// runs through a loop to check the threshold of each neighbour threshold instead of making a loop for each non 8 neighbour pixel
// shift changes if the pixel is on a border in order to not get a out of bounds error on the array
	public boolean THRESHOLDCHECK(Color[][] imageArray, int row, int column, int rshiftmin, int rshiftmax, int cshiftmin, int cshiftmax, int current_red, int current_green, int current_blue) {
		// constants
		int THRESHOLD = 65;
		boolean	 THRESHOLD_CHECK = false;
		
		for (int rshift = rshiftmin; rshift < rshiftmax; rshift++) {
			// breaks the loop if the condition is already made true
			if (THRESHOLD_CHECK == true) {
				break;
			}
			
			else {
				for (int cshift = cshiftmin; cshift < cshiftmax; cshift++) {
					// neighbour values
					int nrow = row + rshift;
					int ncolumn = column + cshift;
					int red = imageArray[nrow][ncolumn].getRed();
					int green = imageArray[nrow][ncolumn].getGreen();
					int blue = imageArray[nrow][ncolumn].getBlue();
					
					//ensure not current pixel
					if (nrow != row && ncolumn != column) {
						double current_distance = this.Colordistance(current_red, current_green, current_blue, red, green, blue);
						
						// sets the threshold to true so that the pixel and be made the correct colour
						if (current_distance > THRESHOLD) {
							THRESHOLD_CHECK = true;
							break;
						}
					}
					
				}
			}
		}

		return THRESHOLD_CHECK;
	}
		
	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		// 2-dimensional array to store the processed image
		Color[][] result = new Color[numOfRows][numOfColumns];

		// loops through all rows and columns
		for (int row = 0; row < numOfRows; row++) {
			for (int column = 0; column < numOfColumns; column++) {
				
				// changes based on the pixel
				boolean threshold_pass = false;
				int current_red = imageArray[row][column].getRed();
				int current_green = imageArray[row][column].getGreen();
				int current_blue = imageArray[row][column].getBlue();
				
				
				// for when 3 neighbours pixels
				if (column == 0 || column == numOfColumns - 1 && row == 0 || row == numOfRows - 1){
					// following statements to determine the corner
					// top left
					if (column == 0 && row == 0) {
						threshold_pass = THRESHOLDCHECK(imageArray, row, column, 0, 2, 0, 2, current_red, current_green, current_blue);
					}
					
					// bottom left
					else if (column == 0 && row == numOfRows -1) {
						threshold_pass = THRESHOLDCHECK(imageArray, row, column, -1, 1, 0, 2, current_red, current_green, current_blue);
					}
					
					// top right
					else if (column == numOfColumns -1 && row == 0) {
						threshold_pass = THRESHOLDCHECK(imageArray, row, column, 0, 2, -1, 1, current_red, current_green, current_blue);
					}
					
					//bottom right
					else if (column == numOfColumns -1 && row == numOfRows - 1) {
						threshold_pass = THRESHOLDCHECK(imageArray, row, column, -1, 1, -1, 1, current_red, current_green, current_blue);
					}
					
					
				}
				
				// for when 5 neighbours pixels
				else if (row == 0 || row == numOfRows - 1 || column == 0 || column == numOfColumns - 1){
					// top border
					if (row == 0) {
						threshold_pass = THRESHOLDCHECK(imageArray, row, column, 0, 1, -1, 2, current_red, current_green, current_blue);
					}
					
					// bottom border
					else if (row == numOfRows -1) {
						threshold_pass = THRESHOLDCHECK(imageArray, row, column, -1, 0, -1, 2, current_red, current_green, current_blue);
					}
					
					// left column
					else if (column == 0) {
						threshold_pass = THRESHOLDCHECK(imageArray, row, column, 0, 2, -1, 1, current_red, current_green, current_blue);
					}
					
					// right column
					else if (column == numOfColumns -1) {
						threshold_pass = THRESHOLDCHECK(imageArray, row, column, -1, 1, -1, 1, current_red, current_green, current_blue);
					}
					
				}
				
				// for when 8 neighbour pixels (the whole picture besides borders and corners)
				else {
					threshold_pass = THRESHOLDCHECK(imageArray, row, column, -1, 2, -1, 2, current_red, current_green, current_blue);
					//System.out.println(THRESHOLDCHECK(imageArray, row, column, -1, 2, -1, 2, current_red, current_green, current_blue));
					}
					
				// determine the colour of the pixel to be made and create it
				if (threshold_pass == true) {
					result[row][column] = new Color(0,0,0);
					
				}
				
				else {
					result[row][column] = new Color(255,255,255);
					
				}	
			}
		}
		
	return result;
	}
}

