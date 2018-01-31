package com.rider.pooling;

import java.util.ArrayList;
import java.util.List;

public class Pool {
	
	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public List<Rider> getRiderList() {
		return riderList;
	}

	public void setRiderList(List<Rider> riderList) {
		this.riderList = riderList;
	}

	private String poolName;
	
	private List<Rider> riderList ;
	
	public Pool(Rider r)
	{
		riderList = new ArrayList<Rider>();
		riderList.add(r);
	}
	
	public void addRider( Rider r)
	{
		riderList.add(r);
	}
}
