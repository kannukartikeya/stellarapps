import java.net.*;
import java.io.*;
import java.util.*;
import org.stellar.sdk.KeyPair;

public class CreateTestAccount2 {
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		KeyPair pair = KeyPair.random();

		System.out.println(new String(pair.getSecretSeed()));
		
		//SDQRS5MUXTDBU5TYKNVV3VL6Q34QUOTBRAZFNSMDJX754ML2AN5RIKOX
		
		System.out.println(pair.getAccountId());
		
		//GDDL2QXA7VXFU4MJS7C3PHXN5T5T3E6W65KZQDVDNEQHH5UYSH5HLUPO
				
		String friendbotUrl = String.format(
				  "https://horizon-testnet.stellar.org/friendbot?addr=%s", pair.getAccountId());
				InputStream response = new URL(friendbotUrl).openStream();
				String body = new Scanner(response, "UTF-8").useDelimiter("\\A").next();
				System.out.println("SUCCESS! You have a new account :)\n" + body);
		
	}

}
