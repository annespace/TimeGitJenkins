package time;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
/**
 * Test class for the Time.java file. Will be used to demo Junit testing in SYST 38634
 * Liz Dancy, 2021
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	/**
	 * A test on good input. We expect this test to return the correct number of seconds.
	 */
	@Test
	void testGetTotalSecondsGood() 
	{
		int seconds = Time.getTotalSeconds("12:05:05");
		assertTrue("The seconds were not calculated properly", seconds==43505);
	}
	
	/**
	 * A method to check that when we enter a String in the wrong format we get a number format exception
	 */
	@Test
	void testGetTotalSecondsBad() 
	{
		assertThrows(StringIndexOutOfBoundsException.class, ()-> {Time.getTotalSeconds("10:00");});
		
	}
	
	/**
	 * A method to check that the method will still work properly on boundary input
	 */
	@Test
	void testGetTotalSecondsBoundary() 
	{
		int seconds = Time.getTotalSeconds("00:00:00");
		assertTrue("The seconds were not calculated properly", seconds==0);
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59" })
	void testGetTotalHours(String candidate) 
	{
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours were not calculated properly", hours ==5);
	}
}
