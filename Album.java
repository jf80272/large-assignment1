package model;

import java.util.ArrayList;

public class Album {
	/* INSTANCE VARIABLES */
	private String title;
	private String artist;
	private String genre;
	private int year;
	private ArrayList<Song> songList;
	
	/* CONSTRUCTORS */
	public Album(String title, String artist, String genre, int year, ArrayList<Song> songs) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.songList = songs;
    }
	
	/* METHODS */
	
	/* GETTERS */
	public String getTitle() {
		return this.title;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public ArrayList<Song> getSongList() {
		ArrayList<Song> sList = new ArrayList<Song>();
		for (Song song : this.songList) {
			sList.add(song);
		}
		return sList;
	}
	
	// To String method
	public String toString() {
		String str = this.title + ", " + this.artist + ", " 
				 + this.genre + ", " + this.year + "\n";
		for (Song song : this.songList) {
			str += song.getTitle() + "\n";
		}
		return str.strip();
	}
}
