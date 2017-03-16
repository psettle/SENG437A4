package rangeTests;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

public class TestConstrain {
	
	private static final int DEFAULT_TIMEOUT = 2000;
	
	private Range range;

	@Before
	public void setupRange() {
		// Set up a basic range, covering positive and negative values.
		this.range = new Range(-3.0, 7.0);
	}

	/**
	 * Tests constraining of a value in the middle of a range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testConstrain_MiddleofRange() {
		assertEquals("Testing constrain of value in middle of range [-3.0, 7.0]", 0, range.constrain(0), 0);	
	}
	
	/**
	 * Tests that the lower bound of a range is contained in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testConstrain_AtMinEdgeOfRange() {
		assertEquals("Testing constrain of value at lower bound of range [-3.0, 7.0]", -3.0, range.constrain(-3.0), 0);
	}
	
	/**
	 * Tests that the lower bound of a range + 0.1 is contained in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testConstrain_AtMinPlusOne() {
		assertEquals("Testing constrain of value at lower bound + 0.1 of range [-3.0, 7.0]", -2.9, range.constrain(-2.9), 0);
	}
	
	/**
	 * Tests that the upper bound of a range is contained in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testConstrain_AtMaxEdgeOfRange() {
		assertEquals("Testing constrain of value at upper bound of range [-3.0, 7.0]", 7.0, range.constrain(7.0), 0);
	}
	
	/**
	 * Tests that the upper bound of a range - 0.1 is contained in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testConstrain_AtMaxMinusOne() {
		assertEquals("Testing constrain of value at uppder bound - 0.1 of range [-3.0, 7.0]", 6.9, range.constrain(6.9), 0);
	}
	
	/**
	 * Tests that a value below the lower bound of a range is constrained to the minimum value in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testConstrain_BelowMinEdgeOfRange() {
		assertEquals("Testing constrain of value in middle of range [-3.0, 7.0]", -3.0, range.constrain(-5.0), 0);
	}
	
	/**
	 * Tests that a value above the upper bound of a range is constrained to the maximum value in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testConstrain_AboveUpperEdgeOfRange() {
		assertEquals("Testing constrain of value in middle of range [-3.0, 7.0]", 7.0, range.constrain(10.0), 0);
	}
}
