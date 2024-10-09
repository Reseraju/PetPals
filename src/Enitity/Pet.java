package Enitity;

public class Pet {
	
	private String name;
	private int age;
	private String breed;
	private String type;
	private boolean isAvailable;
	
	public Pet() {
		name = "unknown";
		age = 0;
		breed = "unknown";
	}
	
	public Pet(String name, int age, String breed, String type, boolean isAvailable) {
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.type = type;
		this.isAvailable = isAvailable;
	}
	
	public String getPetName() {
		return name;
	}
	
	public void setPetName(String name) {
		this.name = name;
	}
	
	public int getPetAge() {
		return age;
	}
	
	public void setPetAge(int age) {
		this.age = age;
	}
	
	public String getBreed() {
		return breed;
	}
	
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String getPetType() {
		return type;
	}
	
	public void setPetType(String type) {
		this.type = type;
	}
	
	public boolean getPetAvailability() {
		return isAvailable;
	}
	
	public void setAvailability(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	@Override
	public String toString() {
        return "Pet [Name: " + name + ", Age: " + age + ", Breed: " + breed + ", Type: " + type+ ", Availability: "+ isAvailable+ "]";
    }
	
}
