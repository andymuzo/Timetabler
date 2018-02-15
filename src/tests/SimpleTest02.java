package tests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import data.Availability;
import data.Course;
import data.Room;
import data.Session;
import data.Settings;
import data.TimeSlot;
import data.Tutor;

public class SimpleTest02 implements TestData {

	public static final int MONDAY_9_AM = 9 * 60;
	public static final int MONDAY_5_PM = 17 * 60;
	public static final int ONE_DAY = 24 * 60;
	public static final int ONE_HOUR = 60;

	Settings settings;
	Set<Room> rooms;
	Set<Course> courses;
	
	Tutor tutor01;
	Tutor tutor02;

	public SimpleTest02() {
		makeSettings();
		makeRooms();
		makeTutors();
		makeCourses();
	}

	private void makeSettings() {
		settings = new Settings(1);
	}

	private void makeRooms() {
		rooms = new HashSet<>();
		Availability availability = new Availability(new ArrayList<TimeSlot>());
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM, MONDAY_5_PM));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + ONE_DAY, MONDAY_5_PM + ONE_DAY));
		rooms.add(new Room("R35", availability));
		
		availability = new Availability(new ArrayList<TimeSlot>());
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM, MONDAY_5_PM));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + ONE_DAY, MONDAY_5_PM + ONE_DAY));
		rooms.add(new Room("R30", availability));
	}

	private void makeTutors() {
		Availability availability = new Availability(new ArrayList<TimeSlot>());
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM, MONDAY_5_PM));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + ONE_DAY, MONDAY_5_PM + ONE_DAY));
		tutor01 = new Tutor("Andy", availability);
		
		availability = new Availability(new ArrayList<TimeSlot>());
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM, MONDAY_5_PM));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + ONE_DAY, MONDAY_5_PM + ONE_DAY));
		tutor02 = new Tutor("Ian", availability);
	}

	private void makeCourses() {
		// this is a bit convoluted but I want both the sessions and courses to
		// have references to each other whilst being included in the set of
		// courses to be returned
		
		courses = new HashSet<>();
		
		courses.add(getCourse01());
		courses.add(getCourse02());
	}

	private Course getCourse01() {
		Availability availability = new Availability(new ArrayList<TimeSlot>());
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM, MONDAY_5_PM));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + ONE_DAY, MONDAY_5_PM + ONE_DAY));
		Course course01 = new Course("Music", availability);
		
		Set<Room> R35 = new HashSet<>();
		R35.add(rooms.iterator().next());
		
		Set<Room> R30 = new HashSet<>();
		R30.add(rooms.iterator().next());
		
		Session session01 = new Session("Theory", 1, tutor01, rooms, ONE_HOUR,
				course01);
		Session session02 = new Session("Studio", 1, tutor02, R30, (ONE_HOUR * 3) + (ONE_HOUR / 2),
				course01);
		Session session03 = new Session("Live Sound", 1, tutor01, R35,
				ONE_HOUR * 3, course01);
		Session session04 = new Session("Techno", 1, tutor02, rooms, ONE_HOUR + (ONE_HOUR / 2),
				course01);
		Session session05 = new Session("Composition", 1, tutor01, rooms, ONE_HOUR,
				course01);
		Session session06 = new Session("Film Music", 1, tutor01, R35, ONE_HOUR / 2,
				course01);
		Session session07 = new Session("Acoustics", 1, tutor01, rooms,
				(ONE_HOUR * 3) + (ONE_HOUR / 2), course01);
		Session session08 = new Session("Business", 1, tutor02, R30, ONE_HOUR,
				course01);

		course01.addSession(session01);
		course01.addSession(session02);
		course01.addSession(session03);
		course01.addSession(session04);
		course01.addSession(session05);
		course01.addSession(session06);
		course01.addSession(session07);
		course01.addSession(session08);
		
		return course01;
	}
	
	private Course getCourse02() {
		Availability availability = new Availability(new ArrayList<TimeSlot>());
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM, MONDAY_5_PM));
		availability.addTimeSlot(new TimeSlot(MONDAY_9_AM + ONE_DAY, MONDAY_5_PM + ONE_DAY));
		Course course01 = new Course("Technology", availability);
		
		Set<Room> R35 = new HashSet<>();
		R35.add(rooms.iterator().next());
		
		Set<Room> R30 = new HashSet<>();
		R30.add(rooms.iterator().next());
		
		Session session01 = new Session("Pure Maths", 1, tutor01, rooms, ONE_HOUR + (ONE_HOUR / 2),
				course01);
		Session session02 = new Session("Studio Engineering", 1, tutor02, rooms, ONE_HOUR * 3,
				course01);
		Session session03 = new Session("CD Production", 1, tutor01, R30,
				ONE_HOUR, course01);
		Session session04 = new Session("Statistics", 1, tutor02, rooms, ONE_HOUR + (ONE_HOUR / 2),
				course01);
		Session session05 = new Session("Electronics", 1, tutor01, rooms, ONE_HOUR,
				course01);
		Session session06 = new Session("Mechanics (Maths)", 1, tutor01, R35, ONE_HOUR + (ONE_HOUR / 2),
				course01);
		Session session07 = new Session("Polar Patterns", 1, tutor01, rooms,
				ONE_HOUR * 3, course01);
		Session session08 = new Session("Impedences", 1, tutor02, R30, ONE_HOUR + (ONE_HOUR / 2),
				course01);

		course01.addSession(session01);
		course01.addSession(session02);
		course01.addSession(session03);
		course01.addSession(session04);
		course01.addSession(session05);
		course01.addSession(session06);
		course01.addSession(session07);
		course01.addSession(session08);
		
		return course01;
	}

	@Override
	public Set<Course> getCourses() {
		return courses;
	}

	@Override
	public Settings getSettings() {
		// TODO Auto-generated method stub
		return settings;
	}
}
