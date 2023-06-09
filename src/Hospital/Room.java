package Hospital;

// this class represent the room class, where we save all room attributes as an object
public class Room {
	
	private String roomID;
	private String roomDescription;
	private int AvailableBeds;
	private int totalNumberOfBeds;
	private float AccommodationCost;
	private int departmentID;

	public Room(String roomID, String roomDescription, int availableBeds, int totalNumberOfBeds, float accommodationCost, int departmentID) {
		this.roomID = roomID;
		this.roomDescription = roomDescription;
		AvailableBeds = availableBeds;
		this.totalNumberOfBeds = totalNumberOfBeds;
		AccommodationCost = accommodationCost;
		this.departmentID = departmentID;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public int getAvailableBeds() {
		return AvailableBeds;
	}

	public void setAvailableBeds(int availableBeds) {
		AvailableBeds = availableBeds;
	}

	public int getTotalNumberOfBeds() {
		return totalNumberOfBeds;
	}

	public void setTotalNumberOfBeds(int totalNumberOfBeds) {
		this.totalNumberOfBeds = totalNumberOfBeds;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public float getAccommodationCost() {
		return AccommodationCost;
	}

	public void setAccommodationCost(float accommodationCost) {
		AccommodationCost = accommodationCost;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	
}
