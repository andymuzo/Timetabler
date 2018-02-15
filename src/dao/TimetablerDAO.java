package dao;

import java.util.HashSet;
import java.util.Set;

import processing.TimetableGenerator;
import data.Course;
import data.Room;
import data.Session;
import data.Settings;
import data.Timetable;
import data.Tutor;

public class TimetablerDAO {

	// setup all of the collections needed
	// settings
	private Settings settings;
	// course/sessions
	private Set<Course> courses;

	public TimetablerDAO() {
		this.settings = new Settings();
		this.courses = new HashSet<>();
	}

	// methods to be called at each stage of the UI
	/**
	 * set all of the settings, this should be done first. Contains: - Number of
	 * Semesters - Lunch Times - Break Times - Display preferences
	 */
	public void defineSettings(Settings settings) {
		this.settings = settings;
	}
	
	public void updateSettings(Settings updatedSettings) {
		
	}
	
	public Settings getSettings() {
		return settings;
	}

	/**
	 * adds a collection of courses to the definitions
	 */
	public void addCourses(Set<Course> courses) {
		this.courses.addAll(courses);
	}

	/**
	 * adds an individual course to the collection, replaces the old one and all
	 * its sessions if it already exists
	 */
	public void addCourse(Course course) {

		this.courses.add(course);
	}

	/**
	 * adds a module definition to an individual course
	 */
	public void addSessionToCourse(Course course, Session session) {
		course.addSession(session);
	}

	/**
	 * uses all of the data entered to work out the timetable, returns the
	 * timetable object to the UI Pre-conditions: The following must have been
	 * set before calling this method: - Settings - Rooms - Tutors - Courses -
	 * Modules
	 * 
	 * @return returns the timetable if it has been possible to calculate it.
	 *         Null otherwise.
	 */
	public Timetable calculateTimetable() {
		// create a new TimetableGenerator object
		TimetableGenerator timetableGenerator = new TimetableGenerator(courses, settings);
		// ask it to generate the table(s)
		return timetableGenerator.generateTimetable();
	}

}
