import java.net.*;
import java.io.*;
import java.util.*;

import org.stellar.sdk.AssetTypeNative;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Memo;
import org.stellar.sdk.Network;
import org.stellar.sdk.PaymentOperation;
import org.stellar.sdk.Server;
import org.stellar.sdk.Transaction;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse;

public class SendLumensAcctToAcct2 {
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		Network.useTestNetwork();
		Server server = new Server("https://horizon-testnet.stellar.org");

		KeyPair source = KeyPair.fromSecretSeed("SBBPSGGOD57U6Q3VHBHRBAPJI4VI2GH7UGWLRKWICGFA3AS7OBTHZIYB");
		KeyPair destination = KeyPair.fromAccountId("GDDL2QXA7VXFU4MJS7C3PHXN5T5T3E6W65KZQDVDNEQHH5UYSH5HLUPO");

		// First, check to make sure that the destination account exists.
		// You could skip this, but if the account does not exist, you will be charged
		// the transaction fee when the transaction fails.
		// It will throw HttpResponseException if account does not exist or there was another error.
		server.accounts().account(destination);

		// If there was no error, load up-to-date information on your account.
		AccountResponse sourceAccount = server.accounts().account(source);

		// Start building the transaction.
		Transaction transaction = new Transaction.Builder(sourceAccount)
		        .addOperation(new PaymentOperation.Builder(destination, new AssetTypeNative(), "10").build())
		        // A memo allows you to add your own metadata to a transaction. It's
		        // optional and does not affect how Stellar treats the transaction.
		        .addMemo(Memo.text("Test Transaction"))
		        .build();
		// Sign the transaction to prove you are actually the person sending it.
		transaction.sign(source);

		// And finally, send it off to Stellar!
		try {
		  SubmitTransactionResponse response = server.submitTransaction(transaction);
		  System.out.println("Success!");
		  System.out.println(response);
		} catch (Exception e) {
		  System.out.println("Something went wrong!");
		  System.out.println(e.getMessage());
		  // If the result is unknown (no response body, timeout etc.) we simply resubmit
		  // already built transaction:
		  // SubmitTransactionResponse response = server.submitTransaction(transaction);
		}
	}

}
