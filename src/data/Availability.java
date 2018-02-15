package data;

import java.util.ArrayList;

/**
 * contains a collection of the available days and time slots
 * @author ajrog_000
 *
 */
public class Availability {
	
	public static final int MONDAY_9_AM = 9 * 60;
	public static final int MONDAY_5_PM = 17 * 60;
	public static final int ONE_DAY = 24 * 60;
	public static final int ONE_HOUR = 60;
	
	public static Availability getDefaultAvailability() {
		Availability availability = new Availability(new ArrayList<TimeSlot>());
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM, MONDAY_5_PM));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + ONE_DAY, MONDAY_5_PM + ONE_DAY));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + (ONE_DAY * 2), MONDAY_5_PM + (ONE_DAY * 2)));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + (ONE_DAY * 3), MONDAY_5_PM + (ONE_DAY * 3)));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + (ONE_DAY * 4), MONDAY_5_PM + (ONE_DAY * 4) - ONE_HOUR));
		return availability;
	}
	
	private ArrayList<TimeSlot> timeSlots;
	
	public Availability(ArrayList<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}
	
	public void addTimeSlot(TimeSlot timeSlot) {
		this.timeSlots.add(timeSlot);
	}
	
	public ArrayList<TimeSlot> getTimeSlots() {
		return timeSlots;
	}
	
	/**
	 * checks through the list of time slots to see if the 
	 * start time is contained within any of them
	 * @param time
	 * @return
	 */
	public boolean hasTimeSlot(int startTime, int sessionLength) {
		for (TimeSlot timeSlot : timeSlots) {
			if (timeSlot.containsTime(startTime, startTime + sessionLength)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<TimeSlot> getTimeSlotsForDay(int dayIndex) {
		ArrayList<TimeSlot> timeSlotsForDay = new ArrayList<>();
		for (TimeSlot timeSlot : timeSlots) {
			if (timeSlot.getDayNumber() == dayIndex) {
				timeSlotsForDay.add(timeSlot);
			}
		}
		return timeSlotsForDay;
	}
	
	public void removeAllOnDay(int dayIndex) {
		ArrayList<TimeSlot> timeSlotsForDay = new ArrayList<>();
		for (TimeSlot timeSlot : timeSlots) {
			if (timeSlot.getDayNumber() == dayIndex) {
				timeSlotsForDay.add(timeSlot);
			}
		}
		timeSlots.removeAll(timeSlotsForDay);
	}
	
	/**
	 * use if the time slot is just a time regardless of day, day is
	 * then specified as an int where 0 = Monday, 7 = Sunday
	 * @param removedTimeSlot
	 * @param day
	 */
	public void removeTimeSlot(TimeSlot removedTimeSlot, int day) {
		removeTimeSlot(new TimeSlot(
				removedTimeSlot.getStartTime() + (day * TimeSlot.ONE_DAY),
				removedTimeSlot.getEndTime() + (day * TimeSlot.ONE_DAY)));
	}
	
	public void removeTimeSlot(TimeSlot removedTimeSlot) {
		// get the time slot that intersects with the argument
		TimeSlot intersectingTimeSlot = null;
		for (TimeSlot timeSlot : timeSlots) {
			if (timeSlot.containsTime(removedTimeSlot)) {
				intersectingTimeSlot = timeSlot;
			}
		}
		
		if (intersectingTimeSlot != null) {
			// remove the original from the list
			timeSlots.remove(intersectingTimeSlot);
			// get the new slot(s)
			if (removedTimeSlot.getStartTime() != intersectingTimeSlot.getStartTime()) {
				// gap at the beginning
				timeSlots.add(new TimeSlot(intersectingTimeSlot.getStartTime(), 
						removedTimeSlot.getStartTime()));
			}
			if (removedTimeSlot.getEndTime() != intersectingTimeSlot.getEndTime()) {
				// gap at the end
				timeSlots.add(new TimeSlot(removedTimeSlot.getEndTime(),
						intersectingTimeSlot.getEndTime()));
			}
		}
	}
}
