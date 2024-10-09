package Enitity;

public class Cat extends Pet {
	private String catColor;
	
	public Cat() {
		super();
		catColor = "unknown";
	}
	
	public Cat(String name, int age, String breed, String catColor, String type, boolean isAvailable) {
		super(name, age, breed, type, isAvailable);
		this.catColor = catColor;
	}
	
	public String getCatColor() {
		return catColor;
	}
	
	public void setCatColor(String catColor) {
		this.catColor = catColor;
	}
}
