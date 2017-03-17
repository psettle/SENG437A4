package rangeTests;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

import junit.runner.Version;

public class TestCombine {
private static final int DEFAULT_TIMEOUT = 2000;
	
	private Range range;

	@Before
	public void setupRange() {
		this.range = new Range(-3.0, 7.0);
	}
	
	/**
	 * Tests that the second argument being null causes the first argument to be returned
	 */
	@Test(timeout=DEFAULT_TIMEOUT) 
	public void testNullSecondArg() {
		System.out.println(Version.id());
		assertTrue("The non-null range was not returned", Range.combine(range, null) == range);
	}
	
	/**
	 * Tests that the first value being null causes the second value to be returned
	 */
	@Test(timeout=DEFAULT_TIMEOUT) 
	public void testNullFirstArg() {
		assertTrue("The non-null range was not returned", Range.combine(null, range) == range);
	}
	
	/**
	 * Tests that two ranges can be successfully combined
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testValidRanges() {
		assertEquals("Two valid ranges were combined wrong", new Range(-3, 3), Range.combine(new Range(-3, 1), new Range(-1, 3)));
	}
	
	/**
	 * Tests combine when the first provided range surrounds the second
	 */
	//@Test(timeout=DEFAULT_TIMEOUT)
	public void testFirstRangeDominant() {
		assertEquals("Two valid ranges were combined wrong", new Range(-3, 3), Range.combine(new Range(-3, 3), new Range(-1, 1)));
	}
	
	/**
	 * Tests combine when the first provided range surrounds the second
	 */
	//@Test(timeout=DEFAULT_TIMEOUT)
	public void testSecondRangeDominant() {
		assertEquals("Two valid ranges were combined wrong", new Range(-3, 3), Range.combine(new Range(-1, 1), new Range(-3, 3)));
	}
}
