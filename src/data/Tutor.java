package data;

/**
 * contains the data for a tutor
 * 
 * @author ajrog_000
 * 
 */
public class Tutor {

	private String name;
	private Availability availability;

	public Tutor(String name, Availability availability) {
		this.name = name;
		this.availability = availability;
	}

	public String getName() {
		return name;
	}

	public Availability getAvailability() {
		return availability;
	}
}
