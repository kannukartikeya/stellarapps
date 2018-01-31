package com.rider.pooling;

import java.io.IOException;

import org.stellar.sdk.Asset;
import org.stellar.sdk.ChangeTrustOperation;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Network;
import org.stellar.sdk.PaymentOperation;
import org.stellar.sdk.Server;
import org.stellar.sdk.Transaction;
import org.stellar.sdk.responses.AccountResponse;

public class PaymentEngine {

	static String sourceSeed = "SBBPSGGOD57U6Q3VHBHRBAPJI4VI2GH7UGWLRKWICGFA3AS7OBTHZIYB";
	static Server server = new Server("https://horizon-testnet.stellar.org");
	
	public static void incentiviseRiders(Pool pool) throws IOException {
		Network.useTestNetwork();
		
		
		for(Rider rider:pool.getRiderList())
		{
			payArtoDollars(sourceSeed,rider.getPrivateKey(),new String("10"));
		}
	}
	private static void payArtoDollars(String sourceSeed2, String privateKey, String incentive) throws IOException {
		
		KeyPair source = KeyPair.fromSecretSeed(sourceSeed);
		
		KeyPair destination = KeyPair.fromSecretSeed(privateKey);
		
		Asset artroDollar = Asset.createNonNativeAsset("ArtroDollar", source);
		
		// First, the receiving account must trust the asset
		AccountResponse receiving = server.accounts().account(destination);
		Transaction allowAstroDollars = new Transaction.Builder(receiving)
		  .addOperation(
		    // The `ChangeTrust` operation creates (or alters) a trustline
		    // The second parameter limits the amount the account can hold
		    new ChangeTrustOperation.Builder(artroDollar, "1000").build())
		  .build();
		allowAstroDollars.sign(destination);
		server.submitTransaction(allowAstroDollars);
		
		// Second, the issuing account actually sends a payment using the asset
		AccountResponse issuing = server.accounts().account(source);
		Transaction sendAstroDollars = new Transaction.Builder(issuing)
		  .addOperation(
		    new PaymentOperation.Builder(destination, artroDollar, incentive).build())
		  .build();
		sendAstroDollars.sign(source);
		server.submitTransaction(sendAstroDollars);
		System.out.println("Paid AstroDollar successfully to pool!");
	}

}
