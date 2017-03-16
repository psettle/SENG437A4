package rangeTests;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Test;

public class TestGetLowerBound {
	
	private static final int DEFAULT_TIMEOUT = 2000;
	
	/**
	 * This test checks to see if getLowerBound correctly returns with a graph that extends to 
	 * negative infinity
	 */
	@Test (timeout = DEFAULT_TIMEOUT)
	public void testLowerBoundNegativeInfinity() {
		
		Range range = new Range(Double.NEGATIVE_INFINITY, 5);
		
		assertEquals("Testing getLowerBound with Lower == -infinity", Double.NEGATIVE_INFINITY, range.getLowerBound(), 0);
		
	}
	
	/**
	 * This test checks to see if getLowerBound correctly returns with a graph that extends to 
	 * positive infinity
	 */
	@Test (timeout = DEFAULT_TIMEOUT)
	public void testUpperBoundPositiveInfinity() {
		
		Range range = new Range(44, Double.POSITIVE_INFINITY);
		
		assertEquals("Testing getLowerBound with Upper == +infinity", 44, range.getLowerBound(), 0);
		
	}
	
	/**
	 * This test checks that getLowerBound correctly returns with a finite, valid range
	 */
	@Test (timeout = DEFAULT_TIMEOUT)
	public void testValidRangeNeitherInfinity() {
		
		Range range = new Range(-1000, -444);
		
		assertEquals("Testing a valid range, (-1000, -444)", -1000, range.getLowerBound(), 0);
		
	}
	
	/**
	 * This test checks to see if getLowerBound correctly returns with a finite range of zero length
	 * i.e. upper == lower
	 */
	@Test (timeout = DEFAULT_TIMEOUT)
	public void testLowerEqualsUpper() {
		
		Range range = new Range(0, 0);
		
		assertEquals("Testing getLowerBound with Lower == Upper", 0, range.getLowerBound(), 0);
		
	}

	
	
}
