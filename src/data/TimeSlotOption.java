package data;

/**
 * simple storage class for the data returned when finding useful time slots
 * @author ajrog_000
 *
 */
public class TimeSlotOption {

	private TimeSlot timeSlot;
	private Session session;
	private Room room;
	private int score;
	
	public TimeSlotOption(TimeSlot timeSlot, Session session, Room room, int score) {
		this.setTimeSlot(timeSlot);
		this.setSession(session);
		this.setRoom(room);
		this.setScore(score);
	}
	
	public TimeSlotOption(TimeSlot timeSlot, Session session, Room room) {
		this.setTimeSlot(timeSlot);
		this.setSession(session);
		this.setRoom(room);
		this.setScore(0);
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addToScore(int score) {
		this.score += score;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
}
