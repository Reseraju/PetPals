package Enitity;

public class Dog extends Pet {
	private String dogBreed;
	
	public Dog() {
		super();
		dogBreed = "unknown";
	}
	
	public Dog(String name, int age, String dogBreed, String type, boolean isAvailable) {
		super(name, age, dogBreed, type, isAvailable);
	}
	
	public String getDogBreed() {
		return dogBreed;
	}
	
	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}
}
