package processing;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import data.FinalSession;
import data.Session;
import data.Settings;
import data.TimeSlot;
import data.TimeSlotOption;
import data.Timetable;

public class TimeSlotRater {
	private Settings settings;
	private static final int COURSE_GROUPING_BIAS = 60;
	private static final int TIME_BIAS = 20;
	private static final int TUTOR_GROUPING_BIAS = 60;
	private static final int TUTOR_OVER_MINUTES_BIAS = -2;
	private static final int COURSE_OVER_MINUTES_BIAS = -2;
	
	// TODO:
	// - Add course grouping bias to reduce gaps between sessions
	// - Add preferred finish time bias
	// - Add preferred start time bias

	public TimeSlotRater(Settings settings) {
		this.settings = settings;
	}

	/**
	 * gives all of the time slots passed as an argument a rating according to
	 * the settings. It then sorts the List so that the top rating is at the
	 * start of the list.
	 * 
	 * @param timeSlotOptions
	 */
	public void rateAndSortTimeSlots(List<TimeSlotOption> timeSlotOptions,
			Timetable timetable, Session session) {
		if (timeSlotOptions != null && !timeSlotOptions.isEmpty()) {
			addDayBias(timeSlotOptions);
			addTimeBias(timeSlotOptions);
			addCourseGroupingBias(timeSlotOptions, timetable, session);
			addTutorGroupingBias(timeSlotOptions, timetable, session);
			addCourseMaxHoursBias(timeSlotOptions, timetable, session);
			addTutorMaxHoursBias(timeSlotOptions, timetable, session);
			// sort them before exiting
			sortTimeSlotOptions(timeSlotOptions);
		}
	}

	/**
	 * rate each day according to how many hours over it is,
	 * award negative points for each hour over
	 * @param timeSlotOptions
	 * @param timetable
	 * @param session
	 */
	private void addTutorMaxHoursBias(List<TimeSlotOption> timeSlotOptions,
			Timetable timetable, Session session) {
		int[] dayScores = new int[7];
		for (int i = 0; i < dayScores.length; i++) {
			dayScores[i] = 0;
		}
		// iterate through each day for the tutor in timetable
		if (timetable.getTutorTimetables().containsKey(session.getTutor())) {
			for (FinalSession finalSession : timetable.getTutorTimetables().get(session.getTutor())) {
				// for each one add up total minutes
				dayScores[finalSession.getTimeSlot().getDayNumber()] 
						+= finalSession.getTimeSlot().getSessionLength();
			}
		}
		// assign a score * how many hours over it is to that day
		for (int i = 0; i < dayScores.length; i++) {
			// get the amount over the total
			dayScores[i] -= settings.getMaxDailyTutorMinutes();
			// make sure not to award positive for under hours
			dayScores[i] = Math.max(dayScores[i], 0);
			// score added for every minute over
			dayScores[i] *= TUTOR_OVER_MINUTES_BIAS;
		}
		// iterate over the options
		for (TimeSlotOption timeSlotOption : timeSlotOptions) {
			// assign the score based on what day it is on
			timeSlotOption.addToScore(
					dayScores[timeSlotOption.getTimeSlot().getDayNumber()]);
		}
	}

	private void addCourseMaxHoursBias(List<TimeSlotOption> timeSlotOptions,
			Timetable timetable, Session session) {
		int[] dayScores = new int[7];
		for (int i = 0; i < dayScores.length; i++) {
			dayScores[i] = 0;
		}
		// iterate through each day for the tutor in timetable
		if (timetable.getCourseTimetables().containsKey(session.getParentCourse())) {
			for (FinalSession finalSession : timetable.getCourseTimetables().get(session.getParentCourse())) {
				// for each one add up total minutes
				dayScores[finalSession.getTimeSlot().getDayNumber()] 
						+= finalSession.getTimeSlot().getSessionLength();
			}
		}
		// assign a score * how many hours over it is to that day
		for (int i = 0; i < dayScores.length; i++) {
			// get the amount over the total
			dayScores[i] -= settings.getMaxDailyCourseMinutes();
			// make sure not to award positive for under hours
			dayScores[i] = Math.max(dayScores[i], 0);
			// score added for every minute over
			dayScores[i] *= COURSE_OVER_MINUTES_BIAS;
		}
		// iterate over the options
		for (TimeSlotOption timeSlotOption : timeSlotOptions) {
			// assign the score based on what day it is on
			timeSlotOption.addToScore(
					dayScores[timeSlotOption.getTimeSlot().getDayNumber()]);
		}
	}

	/**
	 * adds a bias to the options based on the day,
	 * Monday > ... > Sunday
	 * 
	 * @param timeSlotOptions
	 */
	private void addDayBias(List<TimeSlotOption> timeSlotOptions) {
		if (settings.isDayBiasActive()) {
			for (TimeSlotOption timeSlotOption : timeSlotOptions) {
				// day of week
				int bias = timeSlotOption.getTimeSlot().getStartTime()
						/ TimeSlot.ONE_DAY;
				// difference in days
				bias = Math.abs(bias - settings.getDayBias());
				// wrap around
				if (bias > 4) {
					bias = 7 - bias;
				}
				// set to the scale
				bias = 6 - bias;
				timeSlotOption.addToScore(bias);
			}
		}
	}

	/**
	 * adds a bias to the session time start closer to the
	 * bias time in settings scores higher
	 * 
	 * @param timeSlotOptions
	 */
	private void addTimeBias(List<TimeSlotOption> timeSlotOptions) {
		if (settings.isTimeBiasActive()) {
			for (TimeSlotOption timeSlotOption : timeSlotOptions) {
				// time of day
				int bias = timeSlotOption.getTimeSlot().getStartTime()
						% TimeSlot.ONE_DAY;
				// difference in time
				bias = Math.abs(bias - settings.getTimeBias());
				// set to the scale
				if (bias == 0) {
					// avoid a divide by 0 error
					bias = TIME_BIAS;
				} else {
					bias = TIME_BIAS / (bias / 10);
				}
				timeSlotOption.addToScore(bias);
			}
		}
	}

	/**
	 * adds a strong bias to sessions that are on the same day as other sessions
	 * of the same course
	 * 
	 * @param timeSlotOptions
	 * @param timetable
	 */
	private void addCourseGroupingBias(List<TimeSlotOption> timeSlotOptions,
			Timetable timetable, Session session) {
		if (settings.isCourseGroupingBiasActive()) {
			// iterates over all of the sessions in the same course in the
			// timetable
			// create a score holder
			boolean[] scores = new boolean[7];
			for (int i = 0; i < scores.length; i++) {
				scores[i] = false;
			}
			
			// checks there are other courses made first
			if (timetable.getCourseTimetables().containsKey(
					session.getParentCourse())) {
				// iterates over all of the time slot options
				for (TimeSlotOption timeSlotOption : timeSlotOptions) {
					for (FinalSession finalSession : timetable
							.getCourseTimetables().get(session.getParentCourse())) {
						if (TimeSlot.areOnSameDay(finalSession.getTimeSlot(),
								timeSlotOption.getTimeSlot())) {
							
							// adds a score if they are on the same day
							// strong mode adds score for each session on same day
							if (settings.isCourseGroupingBiasStrong()) {
								timeSlotOption.addToScore(COURSE_GROUPING_BIAS);
							} else {
								// weak mode only adds score once per day, flags it here
								scores[timeSlotOption.getTimeSlot().getDayNumber()] = true;
							}							
						}
					}
					// add the weak score here
					if (!settings.isCourseGroupingBiasStrong()
							&& scores[timeSlotOption.getTimeSlot().getDayNumber()]) {
							timeSlotOption.addToScore(COURSE_GROUPING_BIAS);
					}
				}
			}
		}
	}
	
	/**
	 * adds a strong bias to sessions that are on the same day as other sessions
	 * of the same tutor
	 * i.e. groups tutors sessions together into specific days
	 * 
	 * @param timeSlotOptions
	 * @param timetable
	 */
	private void addTutorGroupingBias(List<TimeSlotOption> timeSlotOptions,
			Timetable timetable, Session session) {
		if (settings.isTutorGroupingBiasActive()) {
			// iterates over all of the sessions in the same course in the
			// timetable
			// create a score holder
			boolean[] scores = new boolean[7];
			for (int i = 0; i < scores.length; i++) {
				scores[i] = false;
			}
			
			// checks there are other courses made first
			if (timetable.getTutorTimetables().containsKey(
					session.getTutor())) {
				// iterates over all of the time slot options
				for (TimeSlotOption timeSlotOption : timeSlotOptions) {
					// iterate over all of the final sessions
					for (FinalSession finalSession : timetable
							.getTutorTimetables().get(session.getTutor())) {
						if (TimeSlot.areOnSameDay(finalSession.getTimeSlot(),
								timeSlotOption.getTimeSlot())) {
							
							// adds a score if they are on the same day
							// strong mode adds higher score for each session on same day
							if (settings.isTutorGroupingBiasStrong()) {
								timeSlotOption.addToScore(TUTOR_GROUPING_BIAS);
							} else {
								// weak mode only adds score once per day, flags it here
								scores[timeSlotOption.getTimeSlot().getDayNumber()] = true;
							}							
						}
					}
					// add the weak score here
					if (!settings.isTutorGroupingBiasStrong()
							&& scores[timeSlotOption.getTimeSlot().getDayNumber()]) {
							timeSlotOption.addToScore(TUTOR_GROUPING_BIAS);
					}
				}
			}
		}
	}

	/**
	 * puts the time slot options in order of highest score first
	 * @param timeSlotOptions
	 */
	private void sortTimeSlotOptions(List<TimeSlotOption> timeSlotOptions) {
		Collections.sort(timeSlotOptions, new Comparator<TimeSlotOption>() {
			public int compare(TimeSlotOption option1, TimeSlotOption option2) {
				return option2.getScore() - option1.getScore();
			}
		});
	}
}
