package data;

/**
 * defines a timeslot between two times defined by int values as minutes since
 * midnight on Sunday (0:00am Monday)
 * 
 * @author ajrog_000
 * 
 */
public class TimeSlot {
	public static final int MONDAY_9_AM = 9 * 60;
	public static final int MONDAY_5_PM = 17 * 60;
	public static final int ONE_DAY = 24 * 60;
	public static final int ONE_HOUR = 60;
	public final static int TIME_AT_END_OF_WEEK = ONE_HOUR * ONE_DAY * 7;
	
	private int startTime;
	private int endTime;
	
	/**
	 * checks if the two given time slots are on the same day
	 * @param timeSlot1
	 * @param timeSlot2
	 * @return
	 */
	public static boolean areOnSameDay(TimeSlot timeSlot1, TimeSlot timeSlot2) {
		return (timeSlot1.getStartTime() / ONE_DAY) == (timeSlot2.getStartTime() / ONE_DAY);
	}

	/**
	 * use when time already contains day information
	 * @param startTime
	 * @param endTime
	 */
	public TimeSlot(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * use when the time doesn't contain day information (i.e. time from midnight on any day rather than from Monday)
	 * @param startTime
	 * @param startDay
	 * @param endTime
	 * @param endDay
	 */
	public TimeSlot(int day, int startTime, int endTime) {
		this.startTime = startTime + (ONE_DAY * day);
		this.endTime = endTime + (ONE_DAY * day);
	}

	public int getStartTime() {
		return startTime;
	}
	
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	/**
	 * returns the length of the time slot in minutes
	 * 
	 * @return
	 */
	public int getSessionLength() {
		return endTime - startTime;
	}

	/**
	 * checks to see if the time passed is contained within the start and end
	 * times (inclusive)
	 * 
	 * @param time
	 * @return
	 */
	public boolean containsTime(int time) {
		return startTime <= time && endTime >= time;
	}

	/**
	 * pre-condition: endTime is greater than startTime
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean containsTime(int startTime, int endTime) {
		return this.startTime <= startTime && this.endTime >= endTime;
	}

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean containsTime(TimeSlot timeSlot) {
		return this.startTime <= timeSlot.getStartTime()
				&& this.endTime >= timeSlot.getEndTime();
	}

	@Override
	public String toString() {
		return getDay(startTime) + " " + getTimeString(startTime) + " - "
				+ getTimeString(endTime);
	}

	private String getTimeString(int time) {
		return "" + ((time % ONE_DAY) / ONE_HOUR) + ":" + (time % ONE_HOUR < 10 ? "0" : "")
				+ (time % ONE_HOUR);
	}

	private String getDay(int time) {
		switch (time / (ONE_DAY)) {
		case 0:
			return "Monday";
		case 1:
			return "Tuesday";
		case 2:
			return "Wednesday";
		case 3:
			return "Thursday";
		case 4:
			return "Friday";
		case 5:
			return "Saturday";
		case 6:
			return "Sunday";
		default:
			return "can't find day";
		}
	}
	
	/**
	 * Monday = 0, Sunday = 6
	 * @param time
	 * @return
	 */
	public int getDayNumber() {
		return startTime / ONE_DAY;
	}
}
