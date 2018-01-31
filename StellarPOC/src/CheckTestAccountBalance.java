import java.io.IOException;

import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Server;
import org.stellar.sdk.responses.AccountResponse;

public class CheckTestAccountBalance {
	
	public static void main(String[] args) throws IOException {
		
		//KeyPair pair = KeyPair.random();

		Server server = new Server("https://horizon-testnet.stellar.org");
		
		KeyPair source = KeyPair.fromSecretSeed("SBBPSGGOD57U6Q3VHBHRBAPJI4VI2GH7UGWLRKWICGFA3AS7OBTHZIYB");
		//KeyPair destination = KeyPair.fromAccountId("GDDL2QXA7VXFU4MJS7C3PHXN5T5T3E6W65KZQDVDNEQHH5UYSH5HLUPOGDDL2QXA7VXFU4MJS7C3PHXN5T5T3E6W65KZQDVDNEQHH5UYSH5HLUPO");
		
		KeyPair destination = KeyPair.fromSecretSeed("SDQRS5MUXTDBU5TYKNVV3VL6Q34QUOTBRAZFNSMDJX754ML2AN5RIKOX");

		
		AccountResponse account = server.accounts().account(source);
		System.out.println("Balances for account " + source.getAccountId());
		for (AccountResponse.Balance balance : account.getBalances()) {
		  System.out.println(String.format(
		    "Type: %s, Code: %s, Balance: %s",
		    balance.getAssetType(),
		    balance.getAssetCode(),
		    balance.getBalance()));
		}
		
		AccountResponse destaccount = server.accounts().account(destination);
		System.out.println("Balances for account " + destination.getAccountId());
		for (AccountResponse.Balance balance : destaccount.getBalances()) {
		  System.out.println(String.format(
		    "Type: %s, Code: %s, Balance: %s",
		    balance.getAssetType(),
		    balance.getAssetCode(),
		    balance.getBalance()));
		}
	}

}
