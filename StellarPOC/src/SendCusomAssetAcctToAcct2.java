import java.net.*;
import java.io.*;
import java.util.*;

import org.stellar.sdk.Asset;
import org.stellar.sdk.AssetTypeNative;
import org.stellar.sdk.ChangeTrustOperation;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Memo;
import org.stellar.sdk.Network;
import org.stellar.sdk.PaymentOperation;
import org.stellar.sdk.Server;
import org.stellar.sdk.Transaction;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;

public class SendCusomAssetAcctToAcct2 {
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		Network.useTestNetwork();
		Server server = new Server("https://horizon-testnet.stellar.org");

		KeyPair source = KeyPair.fromSecretSeed("SBBPSGGOD57U6Q3VHBHRBAPJI4VI2GH7UGWLRKWICGFA3AS7OBTHZIYB");
		
		KeyPair destination = KeyPair.fromSecretSeed("SDQRS5MUXTDBU5TYKNVV3VL6Q34QUOTBRAZFNSMDJX754ML2AN5RIKOX");
		
		//KeyPair destination = KeyPair.fromAccountId("GDDL2QXA7VXFU4MJS7C3PHXN5T5T3E6W65KZQDVDNEQHH5UYSH5HLUPO");
		
		// Create an object to represent the new asset
		Asset artroDollar = Asset.createNonNativeAsset("ArtroDollar", source);
		System.out.println("Success!");
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
		    new PaymentOperation.Builder(destination, artroDollar, "10").build())
		  .build();
		sendAstroDollars.sign(source);
		server.submitTransaction(sendAstroDollars);

	}
}
