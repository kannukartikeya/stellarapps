package com.rider.pooling;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Ride {
	
	private  Pool teamPool;
	
	private List<RideAuthentication> startRideList;
	
	private List<RideAuthentication> endRideList;
	
	
	
	public Ride(Pool team)
	{
		teamPool = team;
		startRideList = new ArrayList<RideAuthentication>();
		endRideList = new ArrayList<RideAuthentication>();
		
	}
	
	public void startRide(RideAuthentication riderStartRideDetails)
	{
		startRideList.add(riderStartRideDetails);
	}
	
	public void endRide(RideAuthentication riderEndRideDetails) throws IOException
	{
			endRideList.add(riderEndRideDetails);
			if(endRideList.size()==teamPool.getRiderList().size())
			encentiviseRiders(teamPool);
	}

	private void encentiviseRiders(Pool pool) throws IOException {
		// TODO Auto-generated method stub
		if((pool.getRiderList().size()!=startRideList.size()) || (pool.getRiderList().size()!=endRideList.size())) {
			System.out.println("Ride does not have pool riders for the journey of ride");
			throw new RuntimeException("Ride does not have pool riders for the journey of ride");
		}
		else {
			int startotp = startRideList.get(0).getStartotp();
			for(RideAuthentication riderStartRideDetails:startRideList)
			{
				if(riderStartRideDetails.getStartotp()!=startotp)
					throw new RuntimeException("Start OTP mismatch");
			}
			int endotp = endRideList.get(0).getEndotp();
			for(RideAuthentication riderEndRideDetails:endRideList)
			{
				if(riderEndRideDetails.getEndotp()!=endotp)
					throw new RuntimeException("End OTP mismatch");
			}
			
			PaymentEngine.incentiviseRiders(pool);
			
		}
		
		
		
		
			
		
	}


}
