package dataUtilitiesTests;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import java.util.List;
import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class TestGetCumulativePercentages {
	
	private Mockery mockingContext;
	private KeyedValues values;
	
	private static final int DEFAULT_TIMEOUT = 2000;
	
	
	@Before
	public void setup() {
		
		mockingContext = new Mockery();
		values = mockingContext.mock(KeyedValues.class);
		
	}
	
	/**
	 * This test checks for throwing of InvalidParameterException given null input.
	 */
	@Test(expected=java.security.InvalidParameterException.class, timeout=DEFAULT_TIMEOUT)
	public void test_Null_KeyedValues_exception() {
		DataUtilities.getCumulativePercentages(null);
	}
	
	/**
	 * This test Checks for throwing of InvalidParameterException given empty input.
	 */
	@Test(expected=java.security.InvalidParameterException.class, timeout=DEFAULT_TIMEOUT)
	public void test_Empty_KeyedValues_exception() {
		
		mockingContext.checking(new Expectations() {
			{
			allowing(values).getKeys();
			will(returnValue(Arrays.asList()));
			allowing(values).getItemCount();
			will(returnValue(0));
			}
		});
		
		DataUtilities.getCumulativePercentages(this.values);
	}

	/**
	 * This test covers normal integer inputs for data.
	 */
	@Test//(timeout=DEFAULT_TIMEOUT)
	public void test_Normal_IntegerValues_KeyedValues() {
		
		mockingContext.checking(new Expectations() {
			{
			allowing(values).getItemCount();
			will(returnValue(3));
			allowing(values).getKeys();
			will(returnValue(Arrays.asList(0,1,2) ));
			atLeast(1).of(values).getValue(new Integer(0));
			will(returnValue(1));
			atLeast(1).of(values).getValue(new Integer(1));
			will(returnValue(2));
			atLeast(1).of(values).getValue(new Integer(2));
			will(returnValue(3));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(this.values);
		
		
		assertDoubleListEquals("Testing cumulative percentages with integer KeyedValues",
						Arrays.asList((1.0/6), (3.0/6), (6.0/6)), 
								Arrays.asList(
								result.getValue(result.getKey(0)),
								result.getValue(result.getKey(1)),
								result.getValue(result.getKey(2)) ));

	}
	
	/**
	 * This test covers negative numbers being used as keys, and positive integers
	 * being used for values.
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void test_NegativeKeys_IntegerValues_KeyedValues() {
		//ASSUME it's acceptable for the keys in KeyedValues to be negative numbers.
		mockingContext.checking(new Expectations() {
			{
			allowing(values).getKeys();
			will(returnValue(Arrays.asList(-3,-2,-1) ));
			allowing(values).getItemCount();
			will(returnValue(3));
			
			atLeast(1).of(values).getValue(new Integer(-3));
			will(returnValue(1));
			atLeast(1).of(values).getValue(new Integer(-2));
			will(returnValue(2));
			atLeast(1).of(values).getValue(new Integer(-1));
			will(returnValue(3));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(this.values);
	
		assertEquals("Testing returned keys with negative integer keys in KeyedValues", Arrays.asList(-3,-2,-1),
				result.getKeys());
		
		assertDoubleListEquals("Testing cumulative percentages with integer KeyedValues",
						Arrays.asList((1.0/6), (3.0/6), (6.0/6)), 
								Arrays.asList(
								result.getValue(result.getKey(0)),
								result.getValue(result.getKey(1)),
								result.getValue(result.getKey(2)) ));

	}
	/**
	 * This test covers the use of floating-point numbers as values in data,
	 * and positive integers being used as keys.
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void test_Normal_FloatValues_KeyedValues() {
		
		mockingContext.checking(new Expectations() {
			{
			allowing(values).getKeys();
			will(returnValue(Arrays.asList(0,1,2) ));
			allowing(values).getItemCount();
			will(returnValue(3));
			
			atLeast(1).of(values).getValue(new Integer(0));
			will(returnValue(1.1));
			atLeast(1).of(values).getValue(new Integer(1));
			will(returnValue(2.2));
			atLeast(1).of(values).getValue(new Integer(2));
			will(returnValue(3.3));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(this.values);
	
		assertDoubleListEquals("Testing cumulative percentages with floating-point KeyedValues",
						Arrays.asList((1.1/6.6), (3.3/6.6), (6.6/6.6)), 
							Arrays.asList(
							result.getValue(result.getKey(0)),
							result.getValue(result.getKey(1)),
							result.getValue(result.getKey(2)) ));
		
	}

	/**
	 * This test covers positive integers used as keys and data, but
	 * the values of indices do not match the values of keys.
	 */
	@Test(timeout=DEFAULT_TIMEOUT)
	public void test_IntegerValues_KeyedValues_KeysDoNotMatchIndices() {
		
		mockingContext.checking(new Expectations() {
			{
			allowing(values).getKeys();
			will(returnValue(Arrays.asList(4,5,6) ));
			allowing(values).getItemCount();
			will(returnValue(3));
			atLeast(1).of(values).getValue(new Integer(4));
			will(returnValue(2));
			atLeast(1).of(values).getValue(new Integer(5));
			will(returnValue(4));
			atLeast(1).of(values).getValue(new Integer(6));
			will(returnValue(6));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(this.values);
		
		//ASSUME that the result generated has the same keys as the input.
		assertEquals("Testing returned keys with integer KeyedValues", Arrays.asList(4,5,6),
				result.getKeys());
		
		assertDoubleListEquals("Testing cumulative percentages with integer KeyedValues",
						Arrays.asList((2.0/12), (6.0/12), (12.0/12)), 
								Arrays.asList(
								result.getValue(result.getKey(0)),
								result.getValue(result.getKey(1)),
								result.getValue(result.getKey(2)) ));

	}
	
	/**
	 * This test Checks for throwing of InvalidParameterException given negative values
	 * in the data input.
	 */
	@Test(expected=java.security.InvalidParameterException.class, timeout=DEFAULT_TIMEOUT)
	public void test_NegativeValue_KeyedValues_exception() {
		
		mockingContext.checking(new Expectations() {
			{
			allowing(values).getKeys();
			will(returnValue(Arrays.asList(0,1) ));
			allowing(values).getItemCount();
			will(returnValue(2));
			
			allowing(values).getValue(new Integer(0));
			will(returnValue(-1));
			allowing(values).getValue(new Integer(1));
			will(returnValue(-1));
			}
		});

		DataUtilities.getCumulativePercentages(this.values);

	}
	
	/**
	 * This test Checks for throwing of InvalidParameterException given zeros as values
	 * in the data input.
	 */
	@Test(expected=java.security.InvalidParameterException.class, timeout=DEFAULT_TIMEOUT)
	public void test_ZeroValue_KeyedValues_exception() {
		
		mockingContext.checking(new Expectations() {
			{
			allowing(values).getKeys();
			will(returnValue(Arrays.asList(0,1) ));
			allowing(values).getItemCount();
			will(returnValue(2));
			
			allowing(values).getValue(new Integer(0));
			will(returnValue(0));
			allowing(values).getValue(new Integer(1));
			will(returnValue(0));
			}
		});

		DataUtilities.getCumulativePercentages(this.values);

	}
	
	/**
	 * This test Checks for throwing of InvalidParameterException given null values
	 * in the data input.
	 */
	@Test(expected=java.security.InvalidParameterException.class, timeout=DEFAULT_TIMEOUT)
	public void test_nullValue_KeyedValues_exception() {
		
		mockingContext.checking(new Expectations() {
			{
			allowing(values).getKeys();
			will(returnValue(Arrays.asList(0,1) ));
			allowing(values).getItemCount();
			will(returnValue(2));
			
			allowing(values).getValue(new Integer(0));
			will(returnValue(null));
			allowing(values).getValue(new Integer(1));
			will(returnValue(null));
			}
		});

		DataUtilities.getCumulativePercentages(this.values);

	}
	/**
	 * This test Checks for throwing of InvalidParameterException given NaN values
	 * in the data input.
	 */
	@Test(expected=java.security.InvalidParameterException.class, timeout=DEFAULT_TIMEOUT)
	public void test_NaNValue_KeyedValues_exception() {
		
		mockingContext.checking(new Expectations() {
			{
			allowing(values).getKeys();
			will(returnValue(Arrays.asList(0,1) ));
			allowing(values).getItemCount();
			will(returnValue(2));
			
			allowing(values).getValue(new Integer(0));
			will(returnValue(Double.NaN));
			allowing(values).getValue(new Integer(1));
			will(returnValue(Double.NaN));
			}
		});

		DataUtilities.getCumulativePercentages(this.values);

	}
	
	/**
	 * Asserts that two lists of numbers are equal
	 * @param message
	 * @param expected
	 * @param actual
	 */
	public static void assertDoubleListEquals(String message, List<Number> expected, List<Number> actual) {
		assertTrue(message, expected.size() == actual.size());
		
		for(int i = 0; i < expected.size(); i++) {
			assertEquals(message, expected.get(i).doubleValue(), actual.get(i).doubleValue(), 0.000001d);
		}
	}
}
