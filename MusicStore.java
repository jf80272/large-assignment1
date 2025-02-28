package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Album;
import model.Song;

public class MusicStore {
	private ArrayList<Album> albumList;
	
	/* CONSTRUCTOR */
	public MusicStore(String fileName) {
		albumList = new ArrayList<>();
		addAlbums(fileName);
		printAlbums();
	}
	
	/* METHODS */	
	// Adds albums from the albums file to the list of albums
	private void addAlbums(String fileName) {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			
			while (line != null) {
				String[] albumTitleAuthor = line.split(",");
				findAlbum(albumTitleAuthor);
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Finds an album from the albums file using album title and author
	private void findAlbum(String[] albumTitleAuthor) {
		String albumFileName = "albums/" + albumTitleAuthor[0] + "_" + albumTitleAuthor[1] + ".txt";
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(albumFileName));
			String line = reader.readLine();
			
			// Reading first line
			String[] albumInfo = line.split(",");
			line = reader.readLine();		
			
			// Reading other lines to make a song list
			ArrayList<Song> songs = new ArrayList<>();
			while (line != null) {
				songs.add(new Song(line, albumInfo[1], albumInfo[0]));
				line = reader.readLine();
			}
			
			// Add album to list of albums
			albumList.add(new Album(albumInfo[0], albumInfo[1], albumInfo[2], Integer.parseInt(albumInfo[3]), songs));
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Search for a song by title
	public ArrayList<Song> findSongByTitle(String title) {
		ArrayList<Song> songs = new ArrayList<>();
		for (Album a : albumList) {
			for (Song s : a.getSongList()) {
				if (s.getTitle().equals(title)) {
					songs.add(s);
				}
			}
		}
		
		return songs;
	}

	// Search for a song by artist
	public ArrayList<Song> findSongByArtist(String artist) {
		ArrayList<Song> songs = new ArrayList<>();
		for (Album a : albumList) {
			for (Song s : a.getSongList()) {
				if (s.getArtist().equals(artist)) {
					songs.add(s);
				}
			}
		}
		
		return songs;
	}
	
	// Search for an album by title
	public ArrayList<Album> findAlbumByTitle(String title) {
		ArrayList<Album> albums = new ArrayList<>();
		for (Album a : albumList) {
			if (a.getTitle().equals(title)) {
				albums.add(a);
			}
		}
		
		return albums;
	}
	
	// Search for an album by artist
	public ArrayList<Album> findAlbumByArtist(String artist) {
		ArrayList<Album> albums = new ArrayList<>();
		for (Album a : albumList) {
			if (a.getArtist().equals(artist)) {
				albums.add(a);
			}
		}
		
		return albums;
	}
	
	// for testing
		public void printAlbums() {
			for (Album a : albumList) {
				a.printAlbum();
				System.out.println();
			}
		}
}
