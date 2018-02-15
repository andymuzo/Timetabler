package data;

import java.util.ArrayList;
import java.util.List;

/**
 * contains the course title and other related info plus a collection of all of
 * the associated sessions
 * 
 * @author ajrog_000
 * 
 */
public class Course {
	private String name;
	private List<Session> sessions;
	private Availability availability;
	
	public Course(String name, Availability availability) {
		this.setName(name);
		this.setAvailability(availability);
		this.sessions = new ArrayList<>();
	}
	
	public Course(String name, List<Session> sessions, Availability availability) {
		this.setName(name);
		this.setAvailability(availability);
		this.sessions = sessions;
	}
	
	public void addSession(Session session) {
		sessions.add(session);
	}
	
	public List<Session> getSessions() {
		return sessions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	
}
