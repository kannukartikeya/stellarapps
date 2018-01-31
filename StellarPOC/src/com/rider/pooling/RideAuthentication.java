package com.rider.pooling;

import java.sql.Timestamp;

public class RideAuthentication {
	
	public String getRiderId() {
		return riderId;
	}
	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}
	public int getStartotp() {
		return startotp;
	}
	public void setStartotp(int startotp) {
		this.startotp = startotp;
	}
	public int getEndotp() {
		return endotp;
	}
	public void setEndotp(int endotp) {
		this.endotp = endotp;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	private String riderId;
	private int startotp;
	private int endotp;
	private Timestamp startTime;
	private Timestamp endTime;
	

}
