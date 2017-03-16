package rangeTests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.Range;
import org.junit.Test;

public class TestShiftNoCross {
	
	private static final int DEFAULT_TIMEOUT = 2000;
	
	private Range range;

	private void spanningSetup() {
		this.range = new Range(-2, 2);		
	}
	
	private void subZeroSetup() {
		this.range = new Range(-75, -70);	
	}
	
	private void pastZeroSetup() {
		this.range = new Range(4, 7);
		
	}
	
	/**
	 * This tests shifting a range that is below zero towards neg_inf
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testSubZeroRangeNegativeShift() {
		this.subZeroSetup();
		assertEquals("Testing negative shift of a sub zero range", new Range(-76, -71), Range.shift(this.range, -1));
	}
	
	/**
	 * This tests shift a range that is below zero towards 0, but no past 0
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testSubZeroRangePositiveShiftStaysBelowZero() {
		this.subZeroSetup();
		assertEquals("Testing positive shift of a sub zero range", new Range(-15, -10), Range.shift(this.range, 60));
	}
	
	/**
	 * This tests shifting a range that is below zero exactly to zero
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testSubZeroRangePositiveShiftToZero() {
		this.subZeroSetup();
		assertEquals("Testing positibve shift past zero of a sub zero range", new Range(-5, 0), Range.shift(this.range, 70));
	}
	
	/**
	 * This tests shifting a range that is below zero towards and past 0
	 * should not extend past 0.
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testSubZeroRangePositiveShiftBlocksAtZero() {
		this.subZeroSetup();
		assertEquals("Testing positive shift past zero of a sub zero range", 
				new Range(0.0, 0.0), Range.shift(this.range, 80));
	}

	/*
	 * This tests shifting an above zero range toward pos_inf
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testPastZeroRangePositivehift() {
		this.pastZeroSetup();
		assertEquals("Testing positive shift of positive range", new Range(5, 8), Range.shift(this.range, 1));
	}
	
	/**
	 * This tests shift an above zero range towards 0 in a way that should leave it above zero
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testPastZeroRangeNegativeShiftStaysAboveZero() {
		this.pastZeroSetup();
		assertEquals("Testing negative shift of a positive range", new Range(3, 6), Range.shift(this.range, -1));
	}
	
	/**
	 * This tests a negative shift of an above zero range in a way that brings it right to zero
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testPastZeroRangeNegativeShiftToZero() {
		this.pastZeroSetup();
		assertEquals("Testing shift to zero of a positive range", new Range(0, 3), Range.shift(this.range, -4));
	}
	
	/**
	 * This tests a negative shift of an above zero range past zero.
	 * Should not allow the range to cross zero according to specification.
	 */
	@Test(timeout=DEFAULT_TIMEOUT) 
	public void testPastZeroRangeNegativeShiftBlocksAtZero() {
		this.pastZeroSetup();
		assertEquals("Testing shift past zero of a positive range", new Range(0, 2.0), Range.shift(this.range, -5));
	}
	
	/**
	 * Tests a positive shift of a spanning range, in such a way that the bottom range doesn't pass zero
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testSpanningRangePositivehiftStaysBelowZero() {
		this.spanningSetup();
		assertEquals("Test small posivite shift of spanning range", new Range(-1, 3), Range.shift(this.range, 1));
	}
	
	/**
	 * Tests a negative shift of a spanning range such that the top range doesn't pass zero
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testSpanningRangeNegativeShiftStaysAboveZero() {
		this.spanningSetup();
		assertEquals("Test small negative shift of spanning range", new Range(-4, 0), Range.shift(this.range, -2));
	}
	
	/**
	 * Tests a positive shift of a spanning range in such a way that the range should get stuck at zero
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testSpanningRangePositiveShiftBlocksAtZero() {
		this.spanningSetup();
		assertEquals("Test small posivite shift of spanning range", new Range(0, 5.0), Range.shift(this.range, 3));
	}
	
	/**
	 * Tests a negative shift of a spanning range such that the result should get blocked at zero
	 */
	@Test(timeout=DEFAULT_TIMEOUT) 
	public void testSpanningRangeNegativeShiftBlocksAtZero() {
		this.spanningSetup();
		assertEquals("Test blocking negative shift of spanning range", new Range(-5.0, 0), Range.shift(this.range, -3));
	}
	
	/**
	 * Tests shifting a range by zero
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testRangeNoShift() {
		this.spanningSetup();
		assertEquals("Testing no shift of spanning range", new Range(-2, 2), Range.shift(this.range, 0));
	}
	
	/**
	 * This tests that attempting to shift a null reference will thrw an exception
	 */
	@Test(timeout=DEFAULT_TIMEOUT, expected=InvalidParameterException.class)
	public void testNullShift() {
		Range.shift(null, 0);
	}
}
