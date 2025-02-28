package model;

import java.util.ArrayList;

import model.Song.Rate;

public class LibraryModel {
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
	// Add Song to songList
	public void addToSongList(Song song) {
		if (!this.containsSong(song)) {
			Song s = new Song(song.getTitle(), song.getArtist(), song.getArtist());
			s.setRating(song.getRating());
			s.setFav(song.isFav());
			this.songList.add(s);
		}
	}

	// Add an album to albumList
	public void addToAlbumList(Album album) {
		Album al = new Album(album.getTitle(), album.getArtist(), album.getGenre(), album.getYear(),
				album.getSongList());
		for (Song song : al.getSongList()) {
			if (!this.containsSong(song)) {
				this.songList.add(song);
			}
		}
		this.albumList.add(al);
	}

	// Checks if song is in songList
	public boolean containsSong(Song song) {
		for (Song s : this.songList) {
			if (s.getTitle().equals(song.getTitle()) &&
					s.getArtist().equals(song.getArtist())) {
				return true;
			}
		}
		return false;
	}

	// Create a playlist
	public void createPlaylist(String name) {
		if (!this.containsPlaylist(name)) {
			this.playlistList.add(new Playlist(name));
		}
	}

	// Checks if a playlist name is in playlistList
	public boolean containsPlaylist(String name) {
		for (Playlist playlist : this.playlistList) {
			if (playlist.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	// Add a song to a playlist
	public void addToPlaylist(Song song, String name) {
		if (!this.containsSong(song)) {
			this.addToSongList(song);
		}
		for (Playlist playlist : this.playlistList) {
			if (playlist.getName() == name) {
				playlist.addSong(song);
			}
		}
	}

	// Remove a song from a playlist
	public void removeFromPlaylist(Song song, String name) {
		for (Playlist playlist : this.playlistList) {
			if (playlist.getName() == name) {
				playlist.rmSong(song);
			}
		}
	}
	
	// Search for a song by title
	public ArrayList<Song> findSongByTitle(String title) {
		ArrayList<Song> songs = new ArrayList<>();
		for (Song s : songList) {
			if (s.getTitle().equals(title)) {
				songs.add(s);
			}
		}
		
		return songs;
	}
	
	// Search for a song by artist
	public ArrayList<Song> findSongByArtist(String artist) {
		ArrayList<Song> songs = new ArrayList<>();
		for (Song s : songList) {
			if (s.getArtist().equals(artist)) {
				songs.add(s);
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
	
	// Search for a playlist by name
	public Playlist findPlaylist(String name) {
		for (Playlist p : playlistList) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public void rateSong(Song song , Rate rate) {
		for (Song s : this.songList) {
			if (s.getTitle() == song.getTitle() &&
					s.getArtist() == song.getArtist()) {
				s.setRating(rate);
				
				// If the rating is 5, marks the song as favorite
				if (rate.getValue() == 5) {
					s.setFav(true);
				}
			}
		}
	}
	
	public void setFav(Song song) {
		for (Song s : songList) {
			if (s.getTitle() == song.getTitle() &&
					s.getArtist() == song.getArtist()) {
				s.setFav(true);
			}
		}
	}
	
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
