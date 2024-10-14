import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Dao.*;
import Enitity.*;
import Exception.*;

public class Main {
	public static void main(String[] args) throws InvalidPetAgeException, InsufficientFundsException {
		Scanner scanner = new Scanner(System.in);
        PetShelter petShelter = new PetShelter();
        AdoptionEvent adoptionEvent = new AdoptionEvent();
        
        while(true) {
        	System.out.println("\n--- PetPals ---");
            System.out.println("1. Add Pet");
            System.out.println("2. Remove Pet");
            System.out.println("3. List Available Pets");
            System.out.println("4. Make Donations");
            System.out.println("5. Upcoming Adoption Events");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice) {
            
            case 1:
            	System.out.print("Enter pet type (dog/cat): ");
                String petType = scanner.nextLine().toLowerCase();

                System.out.print("Enter pet name: ");
                String name = scanner.nextLine();

                
                // handling InvalidPetAgeException  
                System.out.print("Enter pet age: ");
                int age = scanner.nextInt();
                if(age<=0) {
                	throw new InvalidPetAgeException("Age must be a positive integer.");
                }
                scanner.nextLine(); 

                System.out.print("Enter pet breed: ");
                String breed = scanner.nextLine();
                
                System.out.print("Is pet available for adoption (true or false): ");
                boolean isAvailable = scanner.nextBoolean();

                if (petType.equals("dog")) {
                    Dog dog = new Dog(name, age, breed, petType, isAvailable);
                    petShelter.addPet(dog);
                } else if (petType.equals("cat")) {
                    System.out.print("Enter cat color: ");
                    String catColor = scanner.nextLine();
                    Cat cat = new Cat(name, age,breed,catColor, petType, isAvailable);
                    petShelter.addPet(cat);
                } else {
                    System.out.println("Invalid pet type!");
                }
                break;
             
                
            case 2: // Remove Pet
            	System.out.print("Enter pet name to remove: ");
                String petNameToRemove = scanner.nextLine();
                Pet petToRemove = petShelter.findPetByName(petNameToRemove);
                if (petToRemove != null) {
                    petShelter.removePet(petToRemove);
                } else {
                    System.out.println("Pet not found: " + petNameToRemove);
                }
                break;
            
            case 3:
            	petShelter.fetchAvailablePets();
                break;
                
            case 4:
            	System.out.println("Enter donation type: \ncash\nitem ");
            	String donationType = scanner.nextLine();
            	switch(donationType) {
            	case "cash":{
            		System.out.print("Enter donor name: ");
                    String donorNameCash = scanner.nextLine();
                    
                    System.out.print("Enter donation amount: ");
                    double amountCash = Double.parseDouble(scanner.nextLine());
                    if (amountCash < 10) {
                        throw new InsufficientFundsException("Minimum donation amount is $10.");
                    }
                    
                    System.out.print("Enter donation date (yyyy-mm-dd): ");
                    String donationDateStr = scanner.nextLine();
                    Date donationDateCash = java.sql.Date.valueOf(donationDateStr);
                    
                    
                    CashDonation cashDonation = new CashDonation(donorNameCash, donationType, amountCash);
                    cashDonation.recordDonation();
            		break;
            	}
            		
            		
            	case "item":{
            		System.out.print("Enter donor name: ");
                    String donorNameItem = scanner.nextLine();
                    
                    System.out.print("Enter donation amount: ");
                    double amountItem = Double.parseDouble(scanner.nextLine());
                    
                    System.out.print("Enter item type (e.g., food, toys): ");
                    String itemType = scanner.nextLine();
                    
                    ItemDonation itemDonation = new ItemDonation(donorNameItem, donationType, amountItem, itemType);
                    itemDonation.recordDonation();
                    
            		break;
            	}
            		
            		
            	}
            	
                break;
                
            case 5:
            	System.out.println("List of upcoming Events");
            	List<String> events = adoptionEvent.fetchUpcomingEvents();
                if (events.isEmpty()) {
                    System.out.println("No upcoming events found.");
                } else {
                    System.out.println("Upcoming Events:");
                    for (String event : events) {
                        System.out.println(event);
                    }
                }
                break;
            case 6:
                System.out.println("Exiting the program.");
                scanner.close();
                return;
                
            default:
                System.out.println("Invalid choice! Please select a valid option.");
            }
        }
	}
}
