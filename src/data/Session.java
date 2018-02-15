package data;

import java.util.Set;

/**
 * all the details needed for a given session
 * 
 * @author ajrog_000
 * 
 */
public class Session {
	private String name;
	private int[] preferredSemesters;
	private Tutor tutor;
	private Set<Room> roomOptions;
	private int lengthInMinutes;
	private Course parentCourse;

	public Session(String name, int preferredSemester, Tutor tutor,
			Set<Room> roomOptions, int lengthInMinutes, Course parentCourse) {
		this.setName(name);
		this.preferredSemesters = new int[1];
		this.preferredSemesters[0] = preferredSemester;
		this.setTutor(tutor);
		this.setRoomOptions(roomOptions);
		this.setLengthInMinutes(lengthInMinutes);
		this.setParentCourse(parentCourse);
	}
	
	public Session(String name, int[] preferredSemesters, Tutor tutor,
			Set<Room> roomOptions, int lengthInMinutes, Course parentCourse) {
		this.setName(name);
		this.setPreferredSemesters(preferredSemesters);
		this.setTutor(tutor);
		this.setRoomOptions(roomOptions);
		this.setLengthInMinutes(lengthInMinutes);
		this.setParentCourse(parentCourse);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getPreferredSemesters() {
		return preferredSemesters;
	}

	public void setPreferredSemesters(int[] preferredSemesters) {
		this.preferredSemesters = preferredSemesters;
	}
	
	public boolean isOnlyForSemester(int semester) {
		boolean returnValue = false;
		if (this.preferredSemesters.length == 1) {
			if (this.preferredSemesters[0] == semester) {
				returnValue = true;
			}
		}
		return returnValue;
	}
	
	/**
	 * returns true if the session is for the given semester AND another one
	 * @param semester
	 * @return
	 */
	public boolean isForGivenSemesterAndMore(int semester) {
		boolean returnValue = false;
		if (this.preferredSemesters.length > 1) {
			for (int i = 0; i < preferredSemesters.length; i++) {
				if (this.preferredSemesters[i] == semester) {
					returnValue = true;
				}
			}
		}
		return returnValue;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Set<Room> getRoomOptions() {
		return roomOptions;
	}
	
	public int getAmountOfRoomOptions() {
		return roomOptions.size();
	}

	public void setRoomOptions(Set<Room> roomOptions) {
		this.roomOptions = roomOptions;
	}

	public int getLengthInMinutes() {
		return lengthInMinutes;
	}

	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}

	public Course getParentCourse() {
		return parentCourse;
	}

	public void setParentCourse(Course parentCourse) {
		this.parentCourse = parentCourse;
	}
	
	@Override
	public String toString() {
		return name + ", rooms: " + getRoomOptions().size() + ", length: " + lengthInMinutes;
	}
}
