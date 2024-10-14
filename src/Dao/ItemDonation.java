package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import Util.DBPropertyUtil;

public class ItemDonation extends Donation {
	
	
	DBPropertyUtil dbprop = new DBPropertyUtil();
	
	public ItemDonation(String donorName, String donationType, double amount, String itemType) {
		super(donorName, donationType, amount, itemType);
	}

	@Override
	public void recordDonation() {


		String sql = "INSERT INTO donations (DonorName, DonationType, DonationAmount, DonationItem) VALUES (?, ?, ?, ?)";
        
        
        try (Connection conn = dbprop.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            
            ps.setString(1, this.getDonorName());
            ps.setString(2, this.getDonationType());
            ps.setDouble(3, this.getAmount());
            ps.setString(4,  this.getDonationItem());

            
            ps.executeUpdate();
            System.out.println("Item donation recorded successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
	
		
	}

}
