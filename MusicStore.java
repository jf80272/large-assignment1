package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Album;
import model.Song;

public class MusicStore {
	private ArrayList<Album> albums;
	
	public MusicStore() {
		albums = new ArrayList<>();
		addAlbums("albums/albums.txt");
		printAlbums();
	}
	
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
				songs.add(new Song(line, albumInfo[0], albumInfo[1]));
				line = reader.readLine();
			}
			
			// Add album to list of albums
			albums.add(new Album(albumInfo[0], albumInfo[1], albumInfo[2], Integer.parseInt(albumInfo[3]), songs));
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// for testing
	public void printAlbums() {
		for (Album a : albums) {
			a.printAlbum();
			System.out.println();
		}
	}
}
