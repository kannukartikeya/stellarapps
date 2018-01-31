import java.net.*;
import java.io.*;
import java.util.*;
import org.stellar.sdk.KeyPair;

public class CreateTestAccount {
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		KeyPair pair = KeyPair.random();

		System.out.println(new String(pair.getSecretSeed()));
		
		//SBBPSGGOD57U6Q3VHBHRBAPJI4VI2GH7UGWLRKWICGFA3AS7OBTHZIYB
		
		System.out.println(pair.getAccountId());
		
		//GCINXZW34TDYIOE4MMRBP6OVAGUIQ6255GH7KKZP4G26HW7ZINL5ZFDG
				
		String friendbotUrl = String.format(
				  "https://horizon-testnet.stellar.org/friendbot?addr=%s", pair.getAccountId());
				InputStream response = new URL(friendbotUrl).openStream();
				String body = new Scanner(response, "UTF-8").useDelimiter("\\A").next();
				System.out.println("SUCCESS! You have a new account :)\n" + body);
		
	}

}
