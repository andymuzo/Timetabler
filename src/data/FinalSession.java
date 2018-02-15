package data;

/**
 * The final details for a given session
 * @author ajrog_000
 *
 */
public class FinalSession implements Comparable<FinalSession> {
	private String name;
	private Tutor tutor;
	private TimeSlot timeSlot;
	private Room room;
	private Course course;
	
	/**
	 * empty constructor for blank details
	 */
	public FinalSession() {
		this.setName(null);
		this.setTutor(null);
		this.setTimeSlot(null);
		this.setRoom(null);
		this.setCourse(null);
	}
	
	/**
	 * constructor for completed details
	 * @param timeSlot
	 * @param room
	 * @param semester
	 */
	public FinalSession(String name, Tutor tutor, TimeSlot timeSlot, 
			Room room, Course course) {
		this.setName(name);
		this.setTutor(tutor);
		this.setTimeSlot(timeSlot);
		this.setRoom(room);
		this.setCourse(course);
	}
	
	public FinalSession(TimeSlotOption timeSlotOption) {
		this.setName(timeSlotOption.getSession().getName());
		this.setTutor(timeSlotOption.getSession().getTutor());
		this.setTimeSlot(timeSlotOption.getTimeSlot());
		this.setRoom(timeSlotOption.getRoom());
		this.setCourse(timeSlotOption.getSession().getParentCourse());	
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	@Override
	public int compareTo(FinalSession otherFinalSession) {
		// Put the one that starts earliest first
		return timeSlot.getStartTime()
				- otherFinalSession.getTimeSlot().getStartTime();
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return timeSlot + "\n" + name + ", " + course.getName() + "\n" + room.getName() + ", " + tutor.getName();
	}
	
}
