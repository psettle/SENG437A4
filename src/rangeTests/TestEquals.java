package rangeTests;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.Test;

/**
 * Tests Range.equals()
 * @author Patrick
 *
 */
public class TestEquals {

	/**
	 * Default timeout.
	 */
	private static final int DEFAULT_TIMEOUT = 2000;
	
	/**
	 * Tests that two identical ranges are considered equal
	 */
	//@Test(timeout=DEFAULT_TIMEOUT)
	public void testEqualRanges() {
		assertTrue("Identical Ranges", new Range(-3, 4).equals(new Range(-3, 4)));
	}
	
	/**
	 * Tests that the wrong object type is not equal to a range
	 */
	//@Test(timeout=DEFAULT_TIMEOUT)
	public void testWrongObjectType() {
		assertFalse("Wrong object type", new Range(-3, 4).equals(new Integer(4)));
	}

	/**
	 * Tests different lower bounds on ranges
	 */
	//@Test(timeout=DEFAULT_TIMEOUT)
	public void testWrongLowerBound() {
		assertFalse("Identical Ranges", new Range(-3, 4).equals(new Range(-4, 4)));
	}
	
	/**
	 * Tests different upper bounds on rnages
	 */
	//@Test(timeout=DEFAULT_TIMEOUT)
	public void testWrongUpperBound() {
		assertFalse("Identical Ranges", new Range(-3, 4).equals(new Range(-3, 5)));
	}
}
