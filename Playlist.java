package model;

import java.util.ArrayList;

public class Playlist {
	/* INSTANCE VARIABLES */
	private final String name;
	private ArrayList<Song> songs;
	
	/* CONSTRUCTORS */
	public Playlist(String name) {
		this.name = name;
		this.songs = new ArrayList<Song>();
	}
	
	/* METHODS */
	public void addSong(Song song) {
		Song s = new Song(song.getTitle(), song.getArtist(), song.getAlbum());
		s.setFav(song.isFav());
		s.setRating(song.getRating());
		songs.add(s);
	}
	
	public void rmSong(Song song) {
		for (Song s : this.songs) {
			if (s.toString().equals(song.toString())) {
				this.songs.remove(s);
			}
		}
	}
	
	/* GETTERS */
	public String getName() {
		return this.name;
	}
	
	public ArrayList<String> getList() {
		ArrayList<String> s = new ArrayList<String>();
		for (Song song : this.songs) {
			s.add(song.getTitle());
		}
		return s;
	}
}
