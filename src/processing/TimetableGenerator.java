package processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import data.Course;
import data.FinalSession;
import data.Room;
import data.Session;
import data.Settings;
import data.TimeSlot;
import data.TimeSlotOption;
import data.Timetable;
import data.Tutor;

/**
 * class does all of the heavy lifting and contains the algorithm for generating
 * the timetable
 * 
 * @author ajrog_000
 * 
 */
public class TimetableGenerator {

	private Set<Course> courses;
	private TimeSlotRater timeSlotRater;
	private Settings settings;

	public TimetableGenerator(Set<Course> courses, Settings settings) {
		this.courses = courses;
		this.timeSlotRater = new TimeSlotRater(settings);
		this.settings = settings;
	}

	/**
	 * generates a new timetable based on the input values
	 * 
	 * @return
	 */
	public Timetable generateTimetable() {
		/*
		 * Timetabling Algorithm 1.0:
		 * 
		 * 1- Sort sessions by the number of room options, then by length 
		 * 2- Create sublists of all sessions that have to be 1st semester 
		 *    then all sessions that can be 1st or another 
		 * 3- Check all rooms have capacity for sessions 
		 * 4- Starting with the 1st semester list 
		 *    a- Take next session in list 
		 *    b- Find next available slot on the course timetable
		 *       and for session tutor 
		 *    c- If there is no available slot add to failure list 
		 *    d- Find the first available room on the session's room 
		 *       list for the given time slot 
		 *    e- If there is no room available go to step b 
		 * 5- Repeat step 4 for second list, adding failures to a new list 
		 * 6- Start again on 2nd semester, adding the sessions from the new 
		 *    list at step 5 that can now only be in 2nd semester 
		 * 7- Repeat again for 3rd/4th term if there is one 
		 * 8- Try to add sessions from the failure list to the other 
		 *    semesters, these should be flagged as being in the
		 *    inappropriate semester
		 */
		
		// 1- Create sublists of all sessions that have to be 1st semester 
		//    then all sessions that can be 1st or another 
		List<Session> allSessions = getListOfAllSessions();
		
		
		
		List<Session> semesterSessions = getSessionsForSemester(1, allSessions);
		List<Session> optionalSessions = getSessionsOptionalForSemester(1, allSessions);
		// 2- Sort sessions by the number of room options, then by length 
		sortSessions(semesterSessions);
		sortSessions(optionalSessions);
		// 3- Check all rooms have capacity for sessions
		checkRoomCapacity(semesterSessions);
		// 4- room finding algorithm
		Timetable semesterTimetable = new Timetable();
		List<Session> rejectList = new ArrayList<>();
		
		generateTimetableForSemester(semesterTimetable, semesterSessions, optionalSessions, rejectList);
		
		return semesterTimetable;
	}

	/**
	 * returns a list of all of the sessions of all of the courses
	 * @return
	 */
	private List<Session> getListOfAllSessions() {
		// put all sessions into one List
		List<Session> allSessions = new ArrayList<>();
		for (Course course : courses) {
			allSessions.addAll(course.getSessions());
		}

		return allSessions;
	}

	/**
	 * returns the sessions that are for a given semester only, 
	 * i.e they should only be in the given semester
	 * @param semester
	 * @param sessions
	 * @return
	 */
	private List<Session> getSessionsForSemester(int semester, List<Session> sessions) {
		List<Session> semesterSessions = new ArrayList<>();
		
		for (Session session : sessions) {
			if (session.isOnlyForSemester(semester)) {
				semesterSessions.add(session);
			}
		}

		return semesterSessions;
	}
	
	/**
	 * returns sessions that can be for the given semester OR for another.
	 * Won't return any that are exclusively for the given semester, they 
	 * have to have other options too
	 * @param semester
	 * @param sessions
	 * @return
	 */
	private List<Session> getSessionsOptionalForSemester(int semester, List<Session> sessions) {
		
		List<Session> semesterSessions = new ArrayList<>();
		
		for (Session session : sessions) {
			if (session.isForGivenSemesterAndMore(semester)) {
				semesterSessions.add(session);
			}
		}

		return semesterSessions;
	}

	/**
	 * sorts the list given as an argument according to how they should 
	 * be placed in a timetable
	 * @param sessions
	 */
	private void sortSessions(List<Session> sessions) {
		// sort the List
		Collections.sort(sessions, new Comparator<Session>() {
			public int compare(Session session1, Session session2) {
				int returnValue = session1.getAmountOfRoomOptions()
						- session2.getAmountOfRoomOptions();
				if (returnValue == 0) {
					// same amount of room options so order by length, longest
					// first
					returnValue = session2.getLengthInMinutes()
							- session1.getLengthInMinutes();
				}
				return returnValue;
			}
		});
	}
	
	/**
	 * makes sure that all of the sessions that have to be in a particular room
	 * or with a particular tutor can do so. Also checks that there is not more total session time
	 * than there is total room or tutor availability.
	 * @param sessions
	 */
	private void checkRoomCapacity(List<Session> sessions) {
		//TODO: implement this, needs to throw exceptions if timetable 
		//      is impossible
	}
	
	/**
	 * Takes the empty semester timetable as an argument and fills it.
	 * It first fits all of the semester Sessions, then tries to fit 
	 * all of the optional ones. The semester Sessions remains unchanged but
	 * any sessions placed from the optionalSessions are removed from the list.
	 * The reject list is populated with any sessions from the semester sessions
	 * that couldn't fit into the semester.
	 * @param semesterTimetable
	 * @param semesterSessions
	 * @param optionalSessions
	 * @param rejectList
	 */
	private void generateTimetableForSemester(Timetable semesterTimetable, 
			List<Session> semesterSessions, List<Session> optionalSessions,
			List<Session> rejectList) {
		// if there are fixed break times removes them first
		removeBreakTimes(semesterTimetable);
		// first get the search interval for these time slots
		int minimumSearchInterval = getMinimumSearchInterval(semesterSessions);
		// then try to place all that have to be in this semester
		for (Session session : semesterSessions) {
			FinalSession finalSession = createFinalSession(session, minimumSearchInterval, semesterTimetable);
			// if one couldn't be created add to the reject pile
			if (finalSession == null) {
				rejectList.add(session);
			}
			else {
				semesterTimetable.addFinalSession(finalSession);
			}
		}
		
		// then try to place all optional sessions
		minimumSearchInterval = Math.min(minimumSearchInterval, getMinimumSearchInterval(optionalSessions));
		// then try to place all that have to be in this semester
		List<Session> placedSessions = new ArrayList<Session>();
		for (Session session : optionalSessions) {
			FinalSession finalSession = createFinalSession(session, minimumSearchInterval, semesterTimetable);
			// if one couldn't be created add to the reject pile
			if (finalSession != null) {
				// leave on the list if the session can't be placed, remove if it can
				semesterTimetable.addFinalSession(finalSession);
				placedSessions.add(session);
			}
		}
		// remove the placed sessions
		optionalSessions.removeAll(placedSessions);
	}
	
	private void removeBreakTimes(Timetable semesterTimetable) {
		for (int i = 0; i < 7; i++) {
			for (Course course : courses) {
				course.getAvailability().removeTimeSlot(settings.getLunchTimeSlot(), i);
			}
		}
	}

	/**
	 * gets the best time slot available for the given session
	 * @param session
	 * @param minimumSearchInterval
	 */
	private FinalSession createFinalSession(Session session, int minimumSearchInterval, Timetable semesterTimetable) {
		// find all possible time slots
		List<TimeSlotOption> timeSlotOptions = 
				getAvailableTimeSlots(session, minimumSearchInterval);
		// rate them according to the preferences in the settings
		rateTimeSlotOptions(timeSlotOptions, semesterTimetable, session);
		// choose the one with the best rating & create the FinalSession based on it
		return getBestFinalSession(timeSlotOptions);
	}
	
	/**
	 * finds all the possible time slots for the given session, searching through the
	 * tutor's and room's time slots at the supplied interval.
	 * @param session
	 * @param minimumSearchInterval
	 */
	private List<TimeSlotOption> getAvailableTimeSlots(Session session, 
			int minimumSearchInterval) {
		List<TimeSlotOption> timeSlotOptions = new ArrayList<>();
		// take the length of the session
		int sessionLength = session.getLengthInMinutes();
		
		// search every possible time interval for the week
		for (int time = 0; time < TimeSlot.TIME_AT_END_OF_WEEK; time += minimumSearchInterval) {
			// check availability for the tutor and the course
			if (session.getTutor().getAvailability().hasTimeSlot(time, sessionLength)
					&& session.getParentCourse().getAvailability().hasTimeSlot(time, sessionLength)) {
				// check through the rooms, for each match create a time slot option
				for (Room room : session.getRoomOptions()) {
					if (room.getAvailability().hasTimeSlot(time, sessionLength)) {
						// room is available at the same time as tutor and course
						timeSlotOptions.add(new TimeSlotOption(
								new TimeSlot(time, time + sessionLength), 
								session, room));
					}
				}
			}
		}
		return timeSlotOptions;
	}
	
	/**
	 * takes the list and gives each option a rating based on the preferences
	 * sorts them so the highest rated is first in the list
	 * @param timeSlotOptions
	 */
	private void rateTimeSlotOptions(List<TimeSlotOption> timeSlotOptions, 
			Timetable semesterTimetable, Session session) {
		// TODO: rating
		timeSlotRater.rateAndSortTimeSlots(timeSlotOptions, semesterTimetable, session);
	}
	
	/**
	 * takes a list of rated time slot options and returns a final session object 
	 * created by taking the best TimeSlotOption
	 * @param timeSlotOptions
	 * @return
	 */
	private FinalSession getBestFinalSession(List<TimeSlotOption> timeSlotOptions) {
		FinalSession finalSession = null;
		// checks for an empty list first
		if (!timeSlotOptions.isEmpty()) {
			// get the first one in the list
			TimeSlotOption timeSlotOption = timeSlotOptions.get(0);
			finalSession = new FinalSession(timeSlotOption);
			//remove time slot from room, course and tutor
			finalSession.getRoom().getAvailability()
				.removeTimeSlot(timeSlotOption.getTimeSlot());
			finalSession.getCourse().getAvailability()
				.removeTimeSlot(timeSlotOption.getTimeSlot());
			finalSession.getTutor().getAvailability()
				.removeTimeSlot(timeSlotOption.getTimeSlot());
		}
		return finalSession;
	}
	
	/**
	 * given a List of Sessions, works out the minimum interval in minutes 
	 * that needs to be searched to ensure no possible time slots are missed
	 * @return
	 */
	private int getMinimumSearchInterval(List<Session> sessions) {
		int minSearchInterval = 60;		
		for (Session session : sessions) {
			if (session.getLengthInMinutes() % 60 == 0) {
				// on the hour exactly
				minSearchInterval = Math.min(minSearchInterval, 60);
			}
			else if (session.getLengthInMinutes() % 30 == 0){
				// half hour exactly
				minSearchInterval = Math.min(minSearchInterval, 30);
			}
			else if (session.getLengthInMinutes() % 20 == 0) {
				// 20 mins
				minSearchInterval = Math.min(minSearchInterval, 20);
			}
			else if (session.getLengthInMinutes() % 15 == 0) {
				// 15 mins
				minSearchInterval = Math.min(minSearchInterval, 15);
			}
			else if (session.getLengthInMinutes() % 10 == 0) {
				// 10 mins
				minSearchInterval = Math.min(minSearchInterval, 10);
			}
			else if (session.getLengthInMinutes() % 5 == 0) {
				// 5 mins
				minSearchInterval = Math.min(minSearchInterval, 5);
			}
			else {
				// 1 min
				minSearchInterval = Math.min(minSearchInterval, 1);
			}
		}
		return minSearchInterval;
	}
	
	/**
	 * TEST method
	 * @param sessions
	 */
	private void printSessions(List<Session> sessions) {

		for (Session session : sessions) {
			System.out.println(session);
		}
	}
}
