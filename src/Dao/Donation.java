package Dao;

import java.util.Date;

public abstract class Donation {
	private String donorName;
	private String donationType;
	private double amount;
	private String donationItem;
	
	
	public Donation(String donorName, String donationType, double amount, String donationItem) {
		this.donorName = donorName;
		this.amount = amount;
		this.donationType = donationType;
		this.donationItem = donationItem;
	}
	
	public Donation(String donorName2, String donationType2, double amount2) {
		this.donorName = donorName;
		this.amount = amount;
		this.donationType = donationType;
	}

	public String getDonorName() {
		return donorName;
	}
	
	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}
	
	public String getDonationType() {
		return donationType;
	}
	
	public void setDonationType(String donationType) {
		this.donationType = donationType;
	}
	
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getDonationItem() {
		return donationItem;
	}
	
	public void setDonationItem(String donationItem) {
		this.donationItem = donationItem;
	}
	
	public abstract void recordDonation();
}
