package rangeTests;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestContains.class,
	TestGetLength.class,
	TestGetLowerBound.class,
	TestGetUpperBound.class,
	TestShiftNoCross.class,
	TestCombine.class,
	TestConstrain.class,
	TestEquals.class,
	TestRange.class,
})


public class RangeTestSuite {}
