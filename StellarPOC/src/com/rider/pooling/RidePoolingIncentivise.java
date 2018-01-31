package com.rider.pooling;

import java.io.IOException;

public class RidePoolingIncentivise {
	
	public static void main(String[] args) throws IOException {
		
		Rider kartikeya  = new Rider ("kartikeya","11","GDDL2QXA7VXFU4MJS7C3PHXN5T5T3E6W65KZQDVDNEQHH5UYSH5HLUPO","SDQRS5MUXTDBU5TYKNVV3VL6Q34QUOTBRAZFNSMDJX754ML2AN5RIKOX");
		
		Rider sujay  = new Rider ("sujay","22","GDDL2QXA7VXFU4MJS7C3PHXN5T5T3E6W65KZQDVDNEQHH5UYSH5HLUPO","SDQRS5MUXTDBU5TYKNVV3VL6Q34QUOTBRAZFNSMDJX754ML2AN5RIKOX");
		
		Pool creamTeamPool = new Pool(kartikeya);
		
		creamTeamPool.addRider(sujay);
		
		Ride jpmcRide = new Ride(creamTeamPool);
		
		RideAuthentication kartikeyaStartDetails = new RideAuthentication();
		kartikeyaStartDetails.setStartotp(111);
		
		RideAuthentication sujayStartDetails = new RideAuthentication();
		sujayStartDetails.setStartotp(111);
		
		jpmcRide.startRide(kartikeyaStartDetails);
		jpmcRide.startRide(sujayStartDetails);
		
		//Riding to office 
		
		RideAuthentication kartikeyaEndDetails = new RideAuthentication();
		kartikeyaEndDetails.setStartotp(222);
		
		RideAuthentication sujayEndDetails = new RideAuthentication();
		sujayEndDetails.setStartotp(222);
		
		jpmcRide.endRide(kartikeyaEndDetails);
		jpmcRide.endRide(sujayEndDetails);
		
		
		
	}

}
