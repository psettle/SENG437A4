package rangeTests;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Test;

/**
 * This tests the constuctor for range
 * @author Patrick
 *
 */
public class TestRange {

	private static final int DEFAULT_TIMEOUT = 2000;

	@Test(timeout=DEFAULT_TIMEOUT)
	public void testValidRange() {
		Range range = new Range(-2, 3);
		
		//calling the constructor to test the constructor wouldn't find any problems, so 
		//we check each limit separately
		assertEquals("Lower bound assigned wrong", range.getLowerBound(), -2, 0.000001);
		assertEquals("Upper bound assigned wrong", range.getUpperBound(), 3, 0.000001);
	}
	
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testZeroRange() {
		Range range = new Range(0, 0);
		
		//calling the constructor to test the constructor wouldn't find any problems, so 
		//we check each limit separately
		assertEquals("Lower bound assigned wrong", range.getLowerBound(), 0, 0.000001);
		assertEquals("Upper bound assigned wrong", range.getUpperBound(), 0, 0.000001);
	}
	
	@Test(timeout=DEFAULT_TIMEOUT, expected=IllegalArgumentException.class)
	public void testInvalidRange() {
		new Range(1, 0.999);
	}
	
	
}
