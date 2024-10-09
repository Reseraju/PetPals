package Enitity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Util.DBPropertyUtil;

public class PetShelter {
	
	private List<Pet> availablePets = null;
	private DBPropertyUtil dbprop;
	
	public PetShelter() {
		this.availablePets = new ArrayList<>();
		this.dbprop = new DBPropertyUtil();
	}
	
//	public void addPet(Pet pet) {
//		availablePets.add(pet);
//		System.out.println(pet.getPetName() + " has been added to the pet shelter");
//	}
	public void addPet(Pet pet) {
        // Add pet to the local list
        availablePets.add(pet);
        System.out.println(pet.getPetName() + " has been added to the pet shelter");

        // Insert the pet into the database
        String sql = "INSERT INTO Pets (Name, Age, Breed, Type, AvailableForAdoption) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbprop.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters for the query
            pstmt.setString(1, pet.getPetName());
            pstmt.setInt(2, pet.getPetAge());
            pstmt.setString(3, pet.getBreed());
            pstmt.setString(4, pet.getPetType());
            pstmt.setBoolean(5, pet.getPetAvailability());

            // Execute the insert query
            pstmt.executeUpdate();
            System.out.println("Pet " + pet.getPetName() + " has been added to the database.");

        } catch (SQLException e) {
            System.err.println("Error while adding pet to the database: " + e.getMessage());
        }
    }
	
	public void removePet(Pet pet) {
		if(availablePets.remove(pet)) {
			System.out.println(pet.getPetName() + " has been removed from pet shelter");
			
			String sql = "DELETE FROM Pets WHERE Name = ? AND Age = ? AND Breed = ?";

	        try (Connection conn = dbprop.getConnection(); // Assume dbprop is your DBPropertyUtil instance
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	             
	            // Set parameters for the query
	            pstmt.setString(1, pet.getPetName());
	            pstmt.setInt(2, pet.getPetAge());
	            pstmt.setString(3, pet.getBreed());

	            // Execute the delete query
	            int rowsAffected = pstmt.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Pet " + pet.getPetName() + " has been removed from the database.");
	            } else {
	                System.out.println("No pet found in the database with the given details.");
	            }
	        } catch (SQLException e) {
	            System.err.println("Error while removing pet from the database: " + e.getMessage());
	        }

		}
		else {
			System.out.println(pet.getPetName() + " is not on the pet shelter");
		}
	}
	
	public Pet findPetByName(String name) {
        for (Pet pet : availablePets) {
            if (pet.getPetName().equalsIgnoreCase(name)) {
                return pet;
            }
        }
        return null; // Pet not found
    }
	
	public void listAvailablePets() {
		if(availablePets.isEmpty()) {
			System.out.println("No pets available for adoption");
		}
		else {
			System.out.println("Available pets for adoption");
			System.out.println("-----------------------------------");
			for(Pet pet: availablePets) {
				System.out.println(pet.toString());
			}
		}
	}
	
	public void fetchAvailablePets() {
        String sql = "SELECT Name, Age, Breed, Type, AvailableForAdoption FROM Pets";
        try (Connection conn = dbprop.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            availablePets.clear(); // Clear the existing list before adding new pets

            while (rs.next()) {
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String breed = rs.getString("Breed");
                String type = rs.getString("Type");
                boolean isAvailable = rs.getBoolean("AvailableForAdoption");
                Pet pet = new Pet(name, age, breed, type, isAvailable);
                availablePets.add(pet);
            }

            if (availablePets.isEmpty()) {
                System.out.println("No pets available for adoption.");
            } else {
                System.out.println("Available Pets:");
                for (Pet pet : availablePets) {
                    System.out.println(pet);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error while fetching pets: " + e.getMessage());
        }
    }
}
