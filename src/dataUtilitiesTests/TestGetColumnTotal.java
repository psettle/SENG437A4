package dataUtilitiesTests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.*;
import org.junit.*;

public class TestGetColumnTotal {

	private Mockery mockingContext;
	private Values2D values;
	
	private static final int DEFAULT_TIMEOUT = 2000;
	
	
	@Before
	public void setup() {
		
		mockingContext = new Mockery();
		values = mockingContext.mock(Values2D.class);
		
	}

	/**
	 * Tests trying to get the total for a column that is out of range in the positive direction
	 */
	@Test(expected=IndexOutOfBoundsException.class, timeout=DEFAULT_TIMEOUT)
	public void testColumnTooLarge() {
		
		mockingContext.checking(new Expectations() {
			{
				atLeast(0).of(values).getRowCount();
				will(returnValue(0));
				oneOf(values).getColumnCount();
				will(returnValue(0));
			}
		});
		
		DataUtilities.calculateColumnTotal(this.values, 3);
	}
	
	/**
	 * Tests trying to sum a column that is out of range in the bottom direction
	 */
	@Test(expected = IndexOutOfBoundsException.class, timeout=DEFAULT_TIMEOUT)
	public void testColumnTooSmall() {
		
		mockingContext.checking(new Expectations() {
			{
				atLeast(0).of(values).getRowCount();
				will(returnValue(0));
				oneOf(values).getColumnCount();
				will(returnValue(0));
			}
		});
		
		DataUtilities.calculateColumnTotal(this.values, -1);
	}
	
	/**
	 * Tests summing the total value when the values are ints
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testTotalWithInts() {
		
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(4));
				one(values).getValue(0, 1);
				will(returnValue(3));
				one(values).getValue(1, 1);
				will(returnValue(4));
				one(values).getValue(2, 1);
				will(returnValue(0));
				one(values).getValue(3, 1);
				will(returnValue(63));
			}
		});
		
		double result = DataUtilities.calculateColumnTotal(values, 1);
		
		assertEquals("Testing a column total with a column of integers", 70, result, 0.0000001d);
	}

	/**
	 * Tests summing the column total when the values are doubles
	 */
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testColumnTotalWithDoubles() {
		
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));
				one(values).getValue(0, 1);
				will(returnValue(3.3));
				one(values).getValue(1, 1);
				will(returnValue(4.4));
				one(values).getValue(2, 1);
				will(returnValue(5.5));
			}
		});
		
		double result = DataUtilities.calculateColumnTotal(values, 1);
		
		assertEquals("Testing a column total with a column of doubles", 13.2, result, 0.0000001d);
	}
	
	/**
	 * Tests summing a column that has no elements.
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void testColumnTotalWithNoRows() {
		
		mockingContext.checking(new Expectations() {
			{
			one(values).getRowCount();
			will(returnValue(0));
			}
		});
		
		double result = DataUtilities.calculateColumnTotal(values, 1);
		
		assertEquals("Testing a column total without any rows.", 0, result, 0);
	}
	
	/**
	 * Tests that an invalid {@link Values2D} object is not actually summed
	 */
	@Test (timeout=DEFAULT_TIMEOUT, expected = InvalidParameterException.class)
	public void testColumnTotalWithNullArgument() {
		
		DataUtilities.calculateColumnTotal(null, 0);
		
	}
	
	/**
	 * Tests that a null value in the data array causes the invalid input exception
	 */
	@Test (timeout=DEFAULT_TIMEOUT, expected =  InvalidParameterException.class)
	public void testColumnTotalWithNullValues() {
		
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));
				one(values).getValue(0, 1);
				will(returnValue(null));
				one(values).getValue(1, 1);
				will(returnValue(1));
				one(values).getValue(2, 1);
				will(returnValue(2));
			}
		});

		
		DataUtilities.calculateColumnTotal(values, 1);
	}
	
	
	
	
	


}
