package Hospital;

public class MedicalStaff {
    private int staff_id;
    private String role;
    private String specialization;
    private int rating;
    private int identity_number;

    public MedicalStaff(){}
    public MedicalStaff(int staff_id,String role ,String specialization, int rating, int identity_number) {
        this.staff_id = staff_id;
        this.specialization = specialization;
        this.rating = rating;
        this.identity_number = identity_number;
        this.role=role;
    }
    
    public MedicalStaff(String role ,String specialization, int rating, int identity_number) {
        this.specialization = specialization;
        this.rating = rating;
        this.identity_number = identity_number;
        this.role=role;
    }
    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(int identity_number) {
        this.identity_number = identity_number;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
