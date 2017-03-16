package rangeTests;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

public class TestContains {
	
	private static final int DEFAULT_TIMEOUT = 2000;
	
	private Range range;

	@Before
	public void setupRange() {
		this.range = new Range(-3.0, 7.0);
	}

	/**
	 * Tests checking if a point exists in the middle of a range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testInCentreOfRange() {
		assertTrue("Range(-3, 7) did not contain 5.0", this.range.contains(5.0));
	}
	
	/**
	 * Tests that the lower bound of a range is contained in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testAtLowerEdgeOfRange() {
		assertTrue("Range(-3, 7) did not contain -3.0", this.range.contains(-3.0));
	}
	
	/**
	 * Tests that the upper bound of a range is contained in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testAtUpperEdgeOfRange() {
		assertTrue("Range(-3, 7) did not contain 7.0", this.range.contains(7.0));
	}
	
	/**
	 * Tests that a value below the edge of a range is not in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testBelowEdgeOfRange() {
		assertFalse("Range(-3, 7) does contain -20.0", this.range.contains(-20.0));
	}
	
	/**
	 * Tests that a value above the edge of a range is not contained in the range
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testAboveEdgeOfRange() {
		assertFalse("Range(-3, 7) does contain 10.0", this.range.contains(10.0));
	}

}
