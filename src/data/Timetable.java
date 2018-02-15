package data;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * container class for a timetable in three formats; by room, by tutor and by
 * course
 * 
 * @author Andrew Rogers 2014
 */
public class Timetable {
	// collections of the timetables
	private Map<Room, SortedSet<FinalSession>> roomTimetables;
	private Map<Tutor, SortedSet<FinalSession>> tutorTimetables;
	private Map<Course, SortedSet<FinalSession>> courseTimetables;

	public Timetable() {
		// creates empty collections
		roomTimetables = new HashMap<>();
		tutorTimetables = new HashMap<>();
		courseTimetables = new HashMap<>();
	}
	
	public Timetable(Map<Room, SortedSet<FinalSession>> roomTimetables,
			Map<Tutor, SortedSet<FinalSession>> tutorTimetables,
			Map<Course, SortedSet<FinalSession>> courseTimetables) {
		
		this.setRoomTimetables(roomTimetables);
		this.setTutorTimetables(tutorTimetables);
		this.setCourseTimetables(courseTimetables);
	}
	
	public void addFinalSession(FinalSession finalSession) {
		// if the tutor, room or course hash maps are not already set up
		// then set them up
		if (!roomTimetables.containsKey(finalSession.getRoom())) {
			roomTimetables.put(finalSession.getRoom(), new TreeSet<FinalSession>());
		}
		if (!tutorTimetables.containsKey(finalSession.getTutor())) {
			tutorTimetables.put(finalSession.getTutor(), new TreeSet<FinalSession>());
		}
		if (!courseTimetables.containsKey(finalSession.getCourse())) {
			courseTimetables.put(finalSession.getCourse(), new TreeSet<FinalSession>());
		}
		// then add the final session to them
		roomTimetables.get(finalSession.getRoom()).add(finalSession);
		tutorTimetables.get(finalSession.getTutor()).add(finalSession);
		courseTimetables.get(finalSession.getCourse()).add(finalSession);
	}
	

	//*********** Getters and Setters ****************************************
	public Map<Room, SortedSet<FinalSession>> getRoomTimetables() {
		return roomTimetables;
	}

	public void setRoomTimetables(Map<Room, SortedSet<FinalSession>> roomTimetables) {
		this.roomTimetables = roomTimetables;
	}

	public Map<Tutor, SortedSet<FinalSession>> getTutorTimetables() {
		return tutorTimetables;
	}

	public void setTutorTimetables(Map<Tutor, SortedSet<FinalSession>> tutorTimetables) {
		this.tutorTimetables = tutorTimetables;
	}

	public Map<Course, SortedSet<FinalSession>> getCourseTimetables() {
		return courseTimetables;
	}

	public void setCourseTimetables(Map<Course, SortedSet<FinalSession>> courseTimetables) {
		this.courseTimetables = courseTimetables;
	}
	
	/**
	 * returns all of the timetables as simple lists
	 */
	@Override
	public String toString() {
		String string = "Timetables:\n\n**** Courses: ********\n";
		// courses
		for (Course course : courseTimetables.keySet()) {
			string = string + "##" + course.getName() + "##\n";
			for (FinalSession finalSession : courseTimetables.get(course)) {
				string = string + finalSession.toString() + "\n\n";
			}
		}
		
		// tutors
		string = string + "\n**** Tutors: ********\n";
		for (Tutor tutor : tutorTimetables.keySet()) {
			string = string + "##" + tutor.getName() + "##\n";
			for (FinalSession finalSession : tutorTimetables.get(tutor)) {
				string = string + finalSession.toString() + "\n\n";
			}
		}
		// rooms
		string = string + "\n**** Rooms: ********\n";
		for (Room room : roomTimetables.keySet()) {
			string = string + "##" + room.getName() + "##\n";
			for (FinalSession finalSession : roomTimetables.get(room)) {
				string = string + finalSession.toString() + "\n\n";
			}
		}
		
		return string;
	}
}
