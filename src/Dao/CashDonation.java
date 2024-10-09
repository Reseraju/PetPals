package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import Util.DBPropertyUtil;

public class CashDonation extends Donation {
	
	
//	private Date donationDate;
	
	DBPropertyUtil dbprop = new DBPropertyUtil();

	public CashDonation(String donorName, String donationType,  double amount) {
		super(donorName, donationType, amount);
//		this.donationDate = donationDate;
	}

	@Override
	public void recordDonation() {
		
			String sql = "INSERT INTO donations (DonorName, DonationType, DonationAmount ) VALUES (?, ?, ?)";
        
	        // Use dbprop to get the connection
	        try (Connection conn = dbprop.getConnection();
	             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
	
	            // Set parameters for the query
	            ps.setString(1, this.getDonorName());
	            ps.setString(2, this.getDonationType());
	            ps.setDouble(3, this.getAmount());
	           
	           
	
	            // Execute the insert query
	            ps.executeUpdate();
	            System.out.println("Cash donation recorded successfully.");
	
	        } catch (SQLException e) {
	            System.err.println("Error while recording cash donation: " + e.getMessage());
	        }
		
		
		
	}
	
}
