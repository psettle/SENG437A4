package rangeTests;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Test;

public class TestGetLength {

	private static final int DEFAULT_TIMEOUT = 2000;
	
	/**
	 * This test checks getLength for proper return from a valid, finite range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testValidLowerLessThanUpper() {
		
		Range r = new Range(-3.5, 4.7);
		
		assertEquals("Testing a Range that has LowerBound < UpperBound", 8.2, r.getLength(), 0.000001);
		
	}
	
	/**
	 * This test checks getLength for proper return from a valid, finite range of length zero
	 * i.e. upper == lower
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testValidLowerEqualsUpper() {
		
		Range r = new Range(3.0, 3.0);
		
		assertEquals("Testing a Range that has LowerBound == UpperBound", 0, r.getLength(), 0.000001);
		
	}
	
	/**
	 * This test checks for the return of NaN when one of the bounds is NaN
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testBoundNotANumber() {
		
		Range r = new Range(-2.0, Double.NaN);
		
		assertEquals("Testing a Range that has LowerBound == NaN", Double.NaN, r.getLength(), 0);
		
	}
	
	/**
	 * This test checks for the proper return from getLength when the range extends to 
	 * negative infinity
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testLowerBoundNegativeInfinity() {
		
		Range r = new Range(Double.NEGATIVE_INFINITY, 3.0);
		
		assertEquals("Testing a Range that has LowerBound == -Infinity", Double.POSITIVE_INFINITY, r.getLength(), 0);
		
	}
	
	/**
	 * This test checks for the proper return from getLength when the range extends to 
	 * positive infinity
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testUpperBoundPositiveInfinity() {
		
		Range r = new Range(-4.7, Double.POSITIVE_INFINITY);
		
		assertEquals("Testing a Range that has UpperBound == +Infinity", Double.POSITIVE_INFINITY, r.getLength(), 0);
		
	}


}
