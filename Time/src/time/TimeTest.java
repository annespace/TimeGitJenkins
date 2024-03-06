package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	public void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly", seconds==18305);
	}
	
	@Test
	public void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, ()-> {Time.getTotalSeconds("10:00");});
	}
	
	@Test
	public void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("23:59:59");
		assertTrue("The seconds were not calculated properly", seconds==86399);
	}	
	
	// ICE 3 =====================================================================
	
	@ParameterizedTest
	@ValueSource(strings = { "05:05:05:05", "23:59:59:00", "12:05:05:05" })
	public void testGetMillisecondsGoodAndBoundary(String candidate) {
		int milliseconds = Time.getMilliseconds(candidate);
		if (candidate.equals("23:59:59:00"))
			assertTrue("The milliseconds were not calculated properly", (milliseconds==0));
		else
			assertTrue("The milliseconds were not calculated properly", (milliseconds==5));
	}
	
	@Test
	public void testGetMillisecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, ()-> {Time.getMilliseconds("10:00:00");});
	}
	
	// ==========================================================================
	
	@ParameterizedTest
	@ValueSource(strings = { "05:05:05", "23:59:59", "10:15:05" })
	public void testGetSecondsGoodAndBoundary(String candidate) {
		int seconds = Time.getSeconds(candidate);
		if (candidate.equals("23:59:59"))
			assertTrue("The seconds were not calculated properly", (seconds==59));
		else
			assertTrue("The seconds were not calculated properly", (seconds==5));
		
	}
	
	@Test
	public void testGetSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, ()-> {Time.getSeconds("10:00");});
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:05:05", "23:59:59", "10:05:15" })
	public void testGetTotalMinutesGoodAndBoundary(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		if (candidate.equals("23:59:59"))
			assertTrue("The minutes were not calculated properly", (minutes==59));
		else
			assertTrue("The minutes were not calculated properly", (minutes==5));
	}
	
	@Test
	public void testGetTotalMinutesBad() {
		assertThrows(StringIndexOutOfBoundsException.class, ()-> {Time.getTotalMinutes("10");});
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:05:05", "23:59:59", "05:10:15" })
	public void testGetTotalHoursGoodAndBoundary(String candidate) {
		int hours = Time.getTotalHours(candidate);
		if (candidate.equals("23:59:59"))
			assertTrue("The hours were not calculated properly", (hours==23));
		else
			assertTrue("The hours were not calculated properly", (hours==5));
	}
	
	@Test
	public void testGetTotalHoursBad() {
		assertThrows(StringIndexOutOfBoundsException.class, ()-> {Time.getTotalHours("");});
	}
	
}
