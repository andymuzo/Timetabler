package util;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static int GetTimeInMinutes(long dateTimeInMs) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateTimeInMs);
		
		return (cal.get(Calendar.HOUR_OF_DAY) * 60) + cal.get(Calendar.MINUTE);
	}
	
	public static int GetTimeInMinutes(Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		
		return (cal.get(Calendar.HOUR_OF_DAY) * 60) + cal.get(Calendar.MINUTE);
	}
	
	public static long GetDateTimeMs(int timeInMinutes) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, timeInMinutes / 60);
		cal.set(Calendar.MINUTE, timeInMinutes % 60);
		
		return cal.getTimeInMillis();
	}
	
	public static Date GetDate(int timeInMinutes) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, timeInMinutes / 60);
		cal.set(Calendar.MINUTE, timeInMinutes % 60);
		
		return new Date(cal.getTimeInMillis());
	}
}
