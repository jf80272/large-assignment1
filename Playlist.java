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
		if (!this.contains(s)) {
			songs.add(s);
		}
	}

	private boolean contains(Song song) {
		for (Song s : this.songs) {
			if (s.getTitle() == song.getTitle() && s.getArtist() == song.getArtist()) {
				return true;
			}
		}
		return false;
	}

	public void rmSong(Song song) {
		for (int i = 0; i < this.songs.size(); i++) {
			Song s = this.songs.get(i);
			if (s.getTitle() == song.getTitle() && s.getArtist() == song.getArtist()) {
				this.songs.remove(i);
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
			s.add(song.toString());
		}
		return s;
	}
}
