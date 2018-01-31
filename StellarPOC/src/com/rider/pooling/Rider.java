package com.rider.pooling;

public class Rider {

	
	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}

	public String getRiderId() {
		return riderId;
	}

	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	private String riderName;
	private String riderId;
	private String publicKey;
	private String privateKey;
	
	public Rider ( String name,String id,String pubKey, String privKey)
	{
		riderName = name;
		riderId = id;
		publicKey = pubKey;
		privateKey = privKey;
	}

}
