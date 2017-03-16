package dataUtilitiesTests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.junit.Test;

public class TestCreateNumberArray2D {

	private static final int DEFAULT_TIMEOUT = 2000;
	
	/**
	 * This test checks for the proper exception being thrown when an invalid parameter is
	 * inputed.
	 */
	@Test (expected = InvalidParameterException.class)
	public void test_Double_Array_Is_Null() {
		
		DataUtilities.createNumberArray2D(null);
		
	}
	
	
	/**
	 * This test checks for the proper exception being thrown when a row in the matrix is null
	 */
	@Test (expected = InvalidParameterException.class, timeout = DEFAULT_TIMEOUT)
	public void test_With_Row_Argument_Null() {
		
		double[][] testArr = {{7.7}, {88.66, 444.4, 6.6}, null, {-44.3, 0.00002}};
		
		DataUtilities.createNumberArray2D(testArr);
			
	}
	
	/**
	 * This test checks for the proper output when an empty 2D array is inputed
	 */
	@Test (timeout = DEFAULT_TIMEOUT)
	public void test_Empty_Array() {
		
		double[][] testArr = {{}};
		
		Number[][] expected = {{}};
		
		Number[][] actual = DataUtilities.createNumberArray2D(testArr);
		
		assertArrayEquals("Testing using an empty array argument", expected, actual);
		
	}

	
	/**
	 * This test checks for the proper output when a valid 2D array is inputed
	 * Note: all of the numbers have been properly formatted as doubles an not implicit doubles,
	 * as would be the expected input. That is instead of inputting 8, 8.0 is inputed.
	 */
	@Test (timeout = DEFAULT_TIMEOUT)
	public void test_Valid_2D_Number_Array_Created() {
		
		double[][] testArr = {{-9.9, 0.000077, 4.454, 99.0330, 13.234}, {-5.5, -4.4, 8.0}, {3.3, -33.5}};
		
		Number[][] expected = {{-9.9, 0.000077, 4.454, 99.0330, 13.234}, {-5.5, -4.4, 8.0}, {3.3, -33.5}};
		
		Number[][] actual = DataUtilities.createNumberArray2D(testArr);
		
		assertArrayEquals(expected, actual);
			
	}

}
