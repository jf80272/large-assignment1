package model;

import java.util.ArrayList;
import java.util.Base64;
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
		String fileName = "src/database/users.txt";
		File file = new File(fileName);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
			// Salt and hash user provided plaintext password
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.reset();
			md.update(salt);
			byte[] byteHashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convert byte arrays to Base64 for safe storage
            String strHashedPassword = Base64.getEncoder().encodeToString(byteHashedPassword);
            String strSalt = Base64.getEncoder().encodeToString(salt);

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
	public void updateSongPlays() {
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
				songPlays.put(songPlayed, plays+1);
				
				updateRecentPlays(songPlayed);
				
				updateMostPlays(songPlayed);
				
				return true;	
			}
		}
		return false;
	}
	
	// Update recent plays list
	private void updateRecentPlays(Song songPlayed) {
		System.arraycopy(recentPlays, 0, recentPlays, 1, 9);
        recentPlays[0] = songPlayed;
	}
	
	// Update most plays list
	private void updateMostPlays(Song songPlayed) {
	    // Get the current play count of the song
	    int playCount = songPlays.get(songPlayed);

	    // First check if the song is already in the mostPlays array
	    boolean songUpdated = false;
	    for (int i = 0; i < mostPlays.length; i++) {
	        if (mostPlays[i] != null && mostPlays[i].equals(songPlayed)) {
	            // Update the play count of the song in the array
	            songPlays.put(songPlayed, playCount);
	            songUpdated = true;
	            break;
	        }
	    }

	    // If the song was not found in mostPlays, need to add it
	    if (!songUpdated) {
	        // Shift elements and insert the song in the correct position
	        for (int i = 0; i < mostPlays.length; i++) {
	            // If the current spot is empty or the current song has fewer plays than the new one
	            if (mostPlays[i] == null || songPlays.get(mostPlays[i]) < playCount) {
	                // Shift elements to make room
	                System.arraycopy(mostPlays, i, mostPlays, i + 1, 9 - i);
	                mostPlays[i] = songPlayed;
	                break;
	            }
	        }
	    }

	    sortMostPlaysByPlays();
	}

	// Helper method to sort most plays list in descending order of play count
	private void sortMostPlaysByPlays() {
	    for (int i = 0; i < mostPlays.length - 1; i++) {
	        for (int j = i + 1; j < mostPlays.length; j++) {
	            // Compare the play counts of songs and swap if necessary
	            if (mostPlays[i] != null && mostPlays[j] != null) {
	                int playCountI = songPlays.get(mostPlays[i]);
	                int playCountJ = songPlays.get(mostPlays[j]);
	                if (playCountI < playCountJ) {
	                    Song temp = mostPlays[i];
	                    mostPlays[i] = mostPlays[j];
	                    mostPlays[j] = temp;
	                }
	            }
	        }
	    }
	}
	
	public String getUsername() {
		return username;
	}
	
	public LibraryModel getLibrary() {
		return library;
	}
	
	public Song[] getRecentPlays() {
		Song[] returnArr = new Song[10];
		int i = 0;
		while (i < 10 && recentPlays[i] != null) {
			returnArr[i] = new Song(recentPlays[i].getTitle(), recentPlays[i].getArtist(), recentPlays[i].getAlbum());
			i++;
		}
		
		return returnArr;
	}
	
	public Song[] getMostPlays() {
		Song[] returnArr = new Song[10];
		int i = 0;
		while (i < 10 && mostPlays[i] != null) {
			returnArr[i] = new Song(mostPlays[i].getTitle(), mostPlays[i].getArtist(), mostPlays[i].getAlbum());
			i++;
		}
		
		return returnArr;
	}
}
