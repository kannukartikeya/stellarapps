package com.stellar.spring.poolriders.service;
import java.net.*;
import java.io.*;
import java.util.*;

import org.springframework.stereotype.Component;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Server;
import org.stellar.sdk.responses.AccountResponse;

import com.stellar.spring.poolriders.model.User;

@Component
public class StellarAccountService {
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
	}
	
	public void createStellarAccount(User user) {
		
		KeyPair pair = KeyPair.random(); 
				
		String friendbotUrl = String.format(
				  "https://horizon-testnet.stellar.org/friendbot?addr=%s", pair.getAccountId());
				InputStream response = null;
				try {
					response = new URL(friendbotUrl).openStream();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String body = new Scanner(response, "UTF-8").useDelimiter("\\A").next();
				System.out.println("SUCCESS! You have a new account :)\n" + body);
								
				user.setWalletPrivateKey(new String(pair.getSecretSeed()));
				user.setWalletPublicKey(pair.getAccountId());
				
				System.out.println("User private key is " + user.getWalletPrivateKey());
		
				System.out.println("User public key is " + user.getWalletPublicKey());
	}
	
	public String getAccountBalance(User user) {

		Server server = new Server("https://horizon-testnet.stellar.org");
		
		KeyPair source = KeyPair.fromSecretSeed(user.getWalletPrivateKey());
		
		String accountBalance = null;
		
				
		AccountResponse account = null;
		try {
			account = server.accounts().account(source);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Balances for account " + source.getAccountId());
		for (AccountResponse.Balance balance : account.getBalances()) {
		  System.out.println(String.format(
		    "Type: %s, Code: %s, Balance: %s",
		    balance.getAssetType(),
		    balance.getAssetCode(),
		    balance.getBalance()));
		    accountBalance =  balance.getBalance();
		}
		
		return accountBalance;
		
	}

}
