package data;

/**
 * contains the data for a classroom
 * @author ajrog_000
 *
 */
public class Room {
	private String name;
	private Availability[] availability;
	
	public Room(String name, Availability[] availability) {
		this.name = name;
		this.availability = availability;
	}
	
	public Room(String name, Availability availability) {
		this.name = name;
		this.availability = new Availability[1];
		this.availability[0] = availability;
	}
	
	public String getName() {
		return name;
	}
	
	public Availability getAvailability() {
		return availability[0];
	}
	
	public Availability[] getAvailabilityArray() {
		return availability;
	}
	
	public void setAvailability(Availability[] availability) {
		this.availability = availability;
	}
	
}
