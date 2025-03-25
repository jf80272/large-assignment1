package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import model.User;

public class UserData {
	/* INSTANCE VARIABLES */
	private HashMap<String, User> userData;
	
	/* CONSTRUCTORS */
	public UserData() {
		userData = new HashMap<>();
	}
	
	/* METHODS */
	// Add user to user database
	public void addUserData(String username, User user) {
		userData.put(username, user);
	}
	
	// Retrieve user from userData if both the username and password are correct
	public User getUser(String username, String password) {
		BufferedReader reader;
		String salt = "";
		String strHashedPassword = "";
		
		try {
			reader = new BufferedReader(new FileReader("users.txt"));
			String line = reader.readLine();
			
			while (line != null) {
				String[] userInfo = line.split(",");
				if (userInfo[0].equals(username)) {
					salt = userInfo[1];
					strHashedPassword = userInfo[2];
					reader.close();
					break;
				}
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// If salt is null, a user account with a username matching the input was not found
		if (salt.isEmpty()) {
			return null;
		}
		
		try {
			// Salt and hash input password and check if it matches hashed password saved in user/pass database
			byte[] byteSalt = salt.getBytes(StandardCharsets.UTF_8);
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(byteSalt);
			byte[] byteHashedInputPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			String strHashedInputPassword = new String(byteHashedInputPassword, StandardCharsets.UTF_8);
			
			if (strHashedInputPassword.equals(strHashedPassword)) {
				return userData.get(username);
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
