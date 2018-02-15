package data;

public class Settings {

	public static enum PRIMARY_PREFERENCE {
		GROUP_SESSIONS, SPREAD_SESSIONS, BALANCED_SPREAD_SESSIONS, 
		MIN_DAYS_FOR_COURSE, MIN_DAYS_FOR_TUTOR, MIN_GAPS_IN_ROOMS,
		NO_PREFERENCE
	}
	
	private PRIMARY_PREFERENCE primaryPreference;

	private int numberOfSemesters;
	
	private boolean dayBiasActive;
	private int dayBias; // 0 = Monday, 6 = Sunday
	private boolean timeBiasActive;
	private int timeBias;
	private boolean courseGroupingBiasActive;
	private boolean tutorGroupingBiasActive;
	private boolean courseGroupingBiasStrong;
	private boolean tutorGroupingBiasStrong;
	private boolean hasLunchTime;
	// the two ints below are preferred max hours, they will be used 
	// when making choices but not strictly adhered to
	private int maxDailyTutorMinutes;
	private int maxDailyCourseMinutes;
	private TimeSlot lunchTimeSlot;
	
	public Settings() {
		this(1);
	}

	public Settings(int numberOfSemesters) {
		this.setNumberOfSemesters(numberOfSemesters);
		primaryPreference = PRIMARY_PREFERENCE.NO_PREFERENCE;
		setDayBiasActive(true);
		setDayBias(0);
		setTimeBiasActive(true);
		setTimeBias(TimeSlot.MONDAY_9_AM);
		
		setCourseGroupingBiasActive(true);
		setCourseGroupingBiasStrong(true);
		setTutorGroupingBiasActive(true);
		setTutorGroupingBiasStrong(true);
		
		setMaxDailyTutorMinutes(6 * TimeSlot.ONE_HOUR);
		setMaxDailyCourseMinutes(6 * TimeSlot.ONE_HOUR);
		
		setHasLunchTime(true);
		setLunchTimeSlot(new TimeSlot(TimeSlot.ONE_HOUR * 12, TimeSlot.ONE_HOUR * 13));
	}

	public int getNumberOfSemesters() {
		return numberOfSemesters;
	}

	public void setNumberOfSemesters(int numberOfSemesters) {
		this.numberOfSemesters = numberOfSemesters;
	}

	public boolean isDayBiasActive() {
		return dayBiasActive;
	}

	public void setDayBiasActive(boolean dayBiasActive) {
		this.dayBiasActive = dayBiasActive;
	}

	public boolean isTimeBiasActive() {
		return timeBiasActive;
	}

	public void setTimeBiasActive(boolean timeBiasActive) {
		this.timeBiasActive = timeBiasActive;
	}

	public boolean isCourseGroupingBiasActive() {
		return courseGroupingBiasActive;
	}

	public void setCourseGroupingBiasActive(boolean courseGroupingBiasActive) {
		this.courseGroupingBiasActive = courseGroupingBiasActive;
	}

	public int getTimeBias() {
		return timeBias;
	}

	public void setTimeBias(int timeBias) {
		this.timeBias = timeBias;
	}

	public int getDayBias() {
		return dayBias;
	}

	public void setDayBias(int dayBias) {
		this.dayBias = dayBias;
	}

	public boolean isHasLunchTime() {
		return hasLunchTime;
	}

	public void setHasLunchTime(boolean hasLunchTime) {
		this.hasLunchTime = hasLunchTime;
	}

	public TimeSlot getLunchTimeSlot() {
		return lunchTimeSlot;
	}

	public void setLunchTimeSlot(TimeSlot lunchTimeSlot) {
		this.lunchTimeSlot = lunchTimeSlot;
	}
	
	public void setLunchTimeStart(int startTime) {
		this.lunchTimeSlot.setStartTime(startTime);
	}
	
	public void setLunchTimeEnd(int endTime) {
		this.lunchTimeSlot.setEndTime(endTime);
	}

	public boolean isTutorGroupingBiasActive() {
		return tutorGroupingBiasActive;
	}

	public void setTutorGroupingBiasActive(boolean tutorGroupingBiasActive) {
		this.tutorGroupingBiasActive = tutorGroupingBiasActive;
	}

	public boolean isCourseGroupingBiasStrong() {
		return courseGroupingBiasStrong;
	}

	public void setCourseGroupingBiasStrong(boolean courseGroupingBiasStrong) {
		this.courseGroupingBiasStrong = courseGroupingBiasStrong;
	}

	public boolean isTutorGroupingBiasStrong() {
		return tutorGroupingBiasStrong;
	}

	public void setTutorGroupingBiasStrong(boolean tutorGroupingBiasStrong) {
		this.tutorGroupingBiasStrong = tutorGroupingBiasStrong;
	}

	public int getMaxDailyTutorMinutes() {
		return maxDailyTutorMinutes;
	}

	public void setMaxDailyTutorMinutes(int maxDailyTutorMinutes) {
		this.maxDailyTutorMinutes = maxDailyTutorMinutes;
	}

	public int getMaxDailyCourseMinutes() {
		return maxDailyCourseMinutes;
	}

	public void setMaxDailyCourseMinutes(int maxDailyCourseMinutes) {
		this.maxDailyCourseMinutes = maxDailyCourseMinutes;
	}

}
