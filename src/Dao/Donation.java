package Dao;

import java.util.Date;

public abstract class Donation {
	private String donorName;
	private String donationType;
	private double amount;
	
	
	public Donation(String donorName, String donationType, double amount) {
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
	
	public abstract void recordDonation();
}
