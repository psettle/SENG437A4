package dataUtilitiesTests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class TestGetRowTotal {
	
	private static final int DEFAULT_TIMEOUT = 2000;

	private Mockery mockingContext;
	private Values2D values;
	
	
	@Before
	public void setup() {
		
		mockingContext = new Mockery();
		values = mockingContext.mock(Values2D.class);
		
	}
	
	/**
	 * Tests summing a row index that is out of range in the positive direction
	 */
	@Test(expected=IndexOutOfBoundsException.class, timeout=DEFAULT_TIMEOUT)
	public void testRowTooLarge() {
		
		mockingContext.checking(new Expectations() {
			{
				oneOf(values).getRowCount();
				will(returnValue(0));
				atLeast(0).of(values).getColumnCount();
				will(returnValue(0));
			}
		});
		
		DataUtilities.calculateColumnTotal(this.values, 3);
	}
	
	/**
	 * Tests summing a row index that is out of range in the negative direction
	 */
	@Test(expected = IndexOutOfBoundsException.class, timeout=DEFAULT_TIMEOUT)
	public void testRowTooSmall() {
		
		mockingContext.checking(new Expectations() {
			{
				oneOf(values).getRowCount();
				will(returnValue(0));
				atLeast(0).of(values).getColumnCount();
				will(returnValue(0));
			}
		});
		
		DataUtilities.calculateColumnTotal(this.values, -1);
	}
	
	/**
	 * Tests summing a row that contains int values
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testRowTotalWithInts() {
		
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(4));
				one(values).getValue(1, 0);
				will(returnValue(3));
				one(values).getValue(1, 1);
				will(returnValue(0));
				one(values).getValue(1, 2);
				will(returnValue(9));
				one(values).getValue(1, 3);
				will(returnValue(4));
			}
		});
		
		double result = DataUtilities.calculateRowTotal(values, 1);
		
		assertEquals("Testing row total with a row on integers", 16, result, 0.0000001d);
	}
	
	/**
	 * 
	 * Tests summing a row with double values
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testRowTotalWithDoubles() {
		
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(3));
				one(values).getValue(1, 0);
				will(returnValue(3.695));
				one(values).getValue(1, 1);
				will(returnValue(0));
				one(values).getValue(1, 2);
				will(returnValue(8.888));
			}
		});
		
		double result = DataUtilities.calculateRowTotal(values, 1);
		
		assertEquals("Testing row total with row of doubles", 12.583, result, 0.0000001d);
	}
	
	/**
	 * Tests summing an empty row
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testRowTotalWithNoColumns() {
		
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(0));
			}
		});
		
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		assertEquals("Testing row total with no columns", 0, result, 0.0000001d);
	}
	
	/**
	 * Tests that trying to sum an invalid Values2D object throws an error
	 */
	@Test (expected = InvalidParameterException.class,timeout=DEFAULT_TIMEOUT)
	public void testRowTotalWithNullArgument() {
		
		DataUtilities.calculateRowTotal(null, 0);
		
	}
	
	/**
	 * Tests that a null value in the data array causes the invalid input exception
	 */
	@Test (timeout=DEFAULT_TIMEOUT, expected =  InvalidParameterException.class)
	public void testRowTotalWithNullValues() {
		
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(3));
				one(values).getValue(1, 0);
				will(returnValue(null));
				one(values).getValue(1, 1);
				will(returnValue(1));
				one(values).getValue(1, 2);
				will(returnValue(2));
			}
		});

		
		DataUtilities.calculateRowTotal(values, 1);
	}
	

	
	



}
