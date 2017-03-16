package dataUtilitiesTests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.junit.Test;

public class TestCreateNumberArray {

	private static final int DEFAULT_TIMEOUT = 2000;

	/**
	 * This test checks for the proper exception being thrown when an invalid parameter is inputed
	 * 
	 */
	@Test (expected = InvalidParameterException.class, timeout = DEFAULT_TIMEOUT)
	public void test_Double_Array_Is_Null() {
		
		DataUtilities.createNumberArray(null);
		
	}
	
	
	/**
	 * This test checks for expected behavior when inputing an empty array into the function
	 */
	@Test (timeout = DEFAULT_TIMEOUT)
	public void test_Empty_Array() {
		
		double[] testArr = {};
		
		Number[] expected = {};
		
		Number[] actual = DataUtilities.createNumberArray(testArr);
		
		assertArrayEquals("Testing using an empty array argument", expected, actual);
		
	}
	
	/**
	 * This test checks for a correctly outputed Number when a valid double array is inputed.
	 * Note: all of the numbers have been properly formatted as doubles an not implicit doubles,
	 * as would be the expected input. That is instead of inputting 4, 4.0 is inputed.
	 */
	@Test (timeout = DEFAULT_TIMEOUT)
	public void test_Valid_Double_Array_Created() {
		
		double[] testArr = {0.00000005, 4.0, -8.88, 7.9};
		
		Number[] expected = {0.00000005, 4.0, -8.88, 7.9};
		
		Number[] actual = DataUtilities.createNumberArray(testArr);
		
		assertArrayEquals("Testing using a valid double array", expected, actual);
			
	}
	
}
