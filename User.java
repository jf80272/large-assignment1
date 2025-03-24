package model;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class User {
	/* INSTANCE VARIABLES */
	private final String username;
	private LibraryModel library;
	private HashMap<Song, Integer> songPlays;
	private Song[] recentPlays;
	private Song[] mostPlays;
	
	/* CONSTRUCTORS */
	public User (String username, String password) {
		this.username = username;
		library = new LibraryModel();
		songPlays = new HashMap<>();
		recentPlays = new Song[10];
		mostPlays = new Song[10];
		
		// set password in file
		String fileName = "users.txt";
		File file = new File(fileName);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
			// Salt and hash user provided plaintext password
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			byte[] byteHashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			String strHashedPassword = new String(byteHashedPassword, StandardCharsets.UTF_8);
			String strSalt = new String(salt, StandardCharsets.UTF_8);
			
			// Store hashed password in user database text file
			if (file.length() == 0) {
				writer.write(username + "," + strSalt + "," + strHashedPassword);
			} else {
				writer.append("\n" + username + "," + strSalt + "," + strHashedPassword);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/* METHODS */
	// Puts songs newly added to library to songPlays as values and their number of plays as keys
	// New songs will always have 0 plays
	// NOT CURRENTLY WORKING
	private void updateSongPlays() {
		ArrayList<Song> songList = library.getSongObjectList();
		for (Song s : songList) {
			if (!songPlays.containsKey(s)) {
				songPlays.put(s, 0);
			}
		}
	}
	
	// Finds and "plays" requested song, returns true if song was found, returns false if not
	public boolean playSong(String title, String artist) {
		// Check if song exists in library
		ArrayList<Song> songList = library.findSongByTitle(title);
		
		if (songList.size() != 0) {
			Song songPlayed = null;
			
			for (Song s : songList) {
				if (s.getArtist().equals(artist)) {
					songPlayed = s;
				}
			}
			
			// If song is found, update its number of plays in songPlays
			// Update recentPlays and mostPlays if needed as well
			if (songPlayed != null) {
				Integer plays = songPlays.get(songPlayed);
				songPlays.put(songPlayed, plays++);
				
				// Update recentPlays
				// Shift elements of recentPlays to make room for songPlayed
				for (int i = 8; i >= 0; i--) {
					recentPlays[i] = recentPlays[i++];
				}
				
				recentPlays[0] = songPlayed;
				
				// Check mostPlays and update if needed
				int lastMostPlaysIndex = -1;
				
				// mostPlays may not have all values initialized yet, so check for last value that is
				for (int i = 9; i >= 0; i--) {
					if (mostPlays[i] != null) {
						lastMostPlaysIndex = i;
					}
				}
				
				if (lastMostPlaysIndex != -1) {
					// Compare num of plays of most recently played song with that of last song in mostPlays
					int lastMostPlays = songPlays.get(mostPlays[lastMostPlaysIndex]);
					int songPlayedPlays = songPlays.get(songPlayed);
					
					if (lastMostPlays <= songPlayedPlays) {
						mostPlays[lastMostPlaysIndex] = songPlayed;
					}
				} else {
					// If lastMostPlaysIndex is still -1, no values have been initialized yet
					mostPlays[0] = songPlayed;
				}
				
				return true;	
			}
		}
		return false;
	}
	
	public LibraryModel getLibrary() {
		return library;
	}
	
	public Song[] getRecentPlays() {
		Song[] returnArr = new Song[10];
		int i = 0;
		while (recentPlays[i] != null && i < 10) {
			returnArr[i] = new Song(recentPlays[i].getTitle(), recentPlays[i].getArtist(), recentPlays[i].getAlbum());
		}
		
		return returnArr;
	}
	
	public Song[] getMostPlays() {
		Song[] returnArr = new Song[10];
		int i = 0;
		while (mostPlays[i] != null && i < 10) {
			returnArr[i] = new Song(mostPlays[i].getTitle(), mostPlays[i].getArtist(), mostPlays[i].getAlbum());
		}
		
		return returnArr;
	}
}
