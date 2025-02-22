package model;

import java.util.ArrayList;

public class LibraryModel {
	/* INSTANCE VAIRBALE */
	private ArrayList<Album> albumList;
	private ArrayList<Song> songList;
	private ArrayList<Playlist> playlistList;

	/* INSTANCE VAIRBALE */
	private ArrayList<Album> albumList;
	private ArrayList<Song> songList;
	private ArrayList<Playlist> playlistList;
	
	/* CONSTRUCTORS */
	public LibraryModel() {
		this.albumList = new ArrayList<Album>();
		this.songList = new ArrayList<Song>();
		this.playlistList = new ArrayList<Playlist>();
	}
	
	/* METHODS */
	/* GETTERS */
	// List of albums
	public ArrayList<String> getAlbumList() {
		ArrayList<String> list = new ArrayList<String>();
		for (Album album : this.albumList) {
			list.add(album.getTitle());
		}
		return list;
	}
	
	// List of songs
	public ArrayList<String> getSongList() {
		ArrayList<String> list = new ArrayList<String>();
		for (Song song : this.songList) {
			list.add(song.getTitle());
		}
		return list;
	}
	
	// List of playlists
	public ArrayList<String> getAllPlayList() {
		ArrayList<String> list = new ArrayList<String>();
		for (Playlist playlist : this.playlistList) {
			list.add(playlist.getName());
		}
		return list;
	}
	
	// List of artists
	public ArrayList<String> getArtists() {
		ArrayList<String> list = new ArrayList<String>();
		for (Album album : this.albumList) {
			list.add(album.getArtist());
		}
		for (Song song : this.songList) {
			if (!list.contains(song.getArtist())) {
				list.add(song.getArtist());
			}
		}
		return list;
	}
	
	// List of favorite songs
	public ArrayList<String> getFavSongs() {
		ArrayList<String> list = new ArrayList<String>();
		for (Song song : this.songList) {
			if (song.isFav()) {
				list.add(song.getTitle());
			}
		}
		return list;
	}
}
