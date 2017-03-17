package givenTestSuite;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.*;

public class DataUtilitiesTest
{
	@Before
	public void setUp()
	{
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values = testValues;
		
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
	}
	
	@After
	public void tearDown()
	{
		values = null;
	}
	
    @Test (expected = InvalidParameterException.class)
	public void testNullDataColumnTotal()
	{
		assertEquals(0.0, DataUtilities.calculateColumnTotal(null, 0), 0.0000001d);
	}
	
	@Test
	public void testValidDataAndColumnColumnTotal()
	{
		assertEquals(5.0, DataUtilities.calculateColumnTotal(values, 0), 0.0000001d);
	}

    @Test (expected = InvalidParameterException.class)
	public void testValidDataInvalidColumnColumnTotal()
	{
		assertEquals(0.0, DataUtilities.calculateColumnTotal(null, 1), 0.0000001d);
	}
	
	private Values2D values;
}
