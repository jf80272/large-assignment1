package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import model.Song.Rate;

public class LibraryModel {
	/* INSTANCE VAIRBALE */
	private HashMap<String, Album> albumList;
	private HashMap<String, Playlist> playlistList;
	private HashMap<String, Song> songList;
	private ArrayList<String> songs;

	/* CONSTRUCTORS */
	public LibraryModel() {
		this.albumList = new HashMap<>();
		this.songList = new HashMap<>();
		this.playlistList = new HashMap<>();
		this.songs = new ArrayList<String>();
		Playlist fav = new Playlist("Favorites");
		Playlist top = new Playlist("Top Rated");
		this.playlistList.put("Favorites", fav);
		this.playlistList.put("Top Rated", top);
	}

	/* METHODS */

	// Add Song to songList
	public void addToSongList(Song song) {
		if (!this.songList.containsKey(song.toString())) {
			// Deep copy of Song
			Song s = new Song(song.getTitle(), song.getArtist(), song.getAlbum());
			s.setRating(song.getRating());
			s.setFav(song.isFav());
			if (!songList.containsKey(s.toString())) {
				songList.put(s.toString(), s);
			}

			// Check if album is in albumlist
			String[] album = s.getAlbum();
			ArrayList<Song> newSongList = new ArrayList<Song>();
			newSongList.add(song);
			Album check = new Album(album[0], album[1], album[2], Integer.parseInt(album[3]), newSongList);
			this.addToAlbumList(check);

			// Update Favorites, TopRated, and auto genres
			this.favSongsPlaylist();
			this.topSongsPlaylist();
			this.genrePlaylist();

			// Updates songs
			this.songs = new ArrayList<String>(this.songList.keySet());
		}
	}

	// Remove song from songList
	public void rmFromSongList(Song song) {
		// Removes song from songList
		if (this.songList.containsKey(song.toString())) {
			this.songList.remove(song.toString());
		}
		// Removes song from albumList
		if (this.albumList.containsKey(song.getAlbum()[0] + " " + song.getAlbum()[1])) {
			// Gets album from albumlist
			Album getAl = this.albumList.get(song.getAlbum()[0] + " " + song.getAlbum()[1]);
			// Gets Song from getAl and removes song
			ArrayList<Song> getSongs = getAl.getSongList();
			getSongs.remove(song);
			// Builds new album and replaces the original one
			Album newAl = new Album(getAl.getTitle(), getAl.getArtist(), getAl.getGenre(), getAl.getYear(), getSongs);
			this.albumList.put(song.getAlbum()[0] + " " + song.getAlbum()[1], newAl);
		}

		// Update Favorites, TopRated, auto genres, and any additional playlists
		this.favSongsPlaylist();
		this.topSongsPlaylist();
		this.genrePlaylist();
		for (Playlist playlist : this.playlistList.values()) {
			playlist.rmSong(song);
		}

		// Updates songs
		this.songs = new ArrayList<String>(this.songList.keySet());
	}

	// Add an album to albumList
	public void addToAlbumList(Album album) {
		// Deep copy album
		Album al = new Album(album.getTitle(), album.getArtist(), album.getGenre(), album.getYear(),
				album.getSongList());
		// Adds album if album is not in albumlist
		if (!this.albumList.containsKey(al.getTitle() + " " + al.getArtist())) {
			this.albumList.put(al.getTitle() + " " + al.getArtist(), al);

			// Adds additional songs if album is in albumList
		} else {
			// Get album from albumList
			Album getAl = this.albumList.get(al.getTitle() + " " + al.getArtist());
			// Get songs from album
			ArrayList<Song> newList = getAl.getSongList();
			// Adds additional songs from al to newList
			for (Song song : al.getSongList()) {
				if (!getAl.contains(song.getTitle())) {
					newList.add(song);
				}
			}
			// Create new album and adds in albumlist
			al = new Album(album.getTitle(), album.getArtist(), album.getGenre(), album.getYear(), newList);
			this.albumList.put(al.getTitle() + " " + al.getArtist(), al);
		}
		// Adds songs from album to songList
		for (Song song : al.getSongList()) {
			this.addToSongList(song);
		}
		// Update Favorites, TopRated, and auto genres
		this.favSongsPlaylist();
		this.topSongsPlaylist();
		this.genrePlaylist();

		// Updates songs
		this.songs = new ArrayList<String>(this.songList.keySet());
	}

	// Remove album from albumList
	public void rmFromAlbumList(Album album) {
		if (this.albumList.containsKey(album.getTitle() + " " + album.getArtist())) {
			this.albumList.remove(album.getTitle() + " " + album.getArtist());

			// Removes songs from songList;
			for (Song song : album.getSongList()) {
				if (this.songList.containsKey(song.toString())) {
					this.rmFromSongList(song);
				}
			}
			// Update Favorites, TopRated, and auto genres
			this.favSongsPlaylist();
			this.topSongsPlaylist();
			this.genrePlaylist();

			// Updates songs
			this.songs = new ArrayList<String>(this.songList.keySet());
		}
	}

	// Create a playlist
	public void createPlaylist(String name) {
		if (!this.playlistList.containsKey(name)) {
			this.playlistList.put(name, new Playlist(name));
		}
	}

	// Create a Favorites playlist
	public void favSongsPlaylist() {
		for (Song song : this.songList.values()) {
			if (song.isFav()) {
				this.playlistList.get("Favorites").addSong(song);
			}
		}
	}

	// Create a Top Rated playlist
	public void topSongsPlaylist() {
		for (Song song : this.songList.values()) {
			if (song.getRating() == Rate.FOUR || song.getRating() == Rate.FIVE) {
				this.playlistList.get("Top Rated").addSong(song);
			}
		}
	}

	// Create hashmap and organize songs by genre
	private HashMap<String, ArrayList<Song>> helperGenreList() {
		HashMap<String, ArrayList<Song>> songs = new HashMap<>();
		for (Song song : this.songList.values()) {
			String genre = song.getAlbum()[2];
			if (!songs.containsKey(genre)) {
				songs.put(genre, new ArrayList<Song>());
			}
			songs.get(genre).add(song);
		}
		return songs;
	}

	// Create playlists based on genre
	public void genrePlaylist() {
		HashMap<String, ArrayList<Song>> songs = helperGenreList();
		for (String genre : songs.keySet()) {
			ArrayList<Song> song = songs.get(genre);
			if (song.size() >= 10) {
				this.createPlaylist(genre);
				for (Song s : song) {
					this.addToPlaylist(s, genre);
				}
			}
		}
	}

	// Add a song to a playlist
	public void addToPlaylist(Song song, String name) {
		// Adds song to songList but not the playlist
		if (!this.songList.containsKey(song.toString())) {
			this.addToSongList(song);
		}
		if (this.playlistList.containsKey(name)) {
			this.playlistList.get(name).addSong(song);
		}
	}

	// Remove a song from a playlist
	public void removeFromPlaylist(Song song, String name) {
		if (this.playlistList.containsKey(name)) {
			this.playlistList.get(name).rmSong(song);
		}
	}

	// Contains method for playlist
	public boolean containsPlaylist(String name) {
		return this.playlistList.containsKey(name);
	}

	// Shuffle songs
	public void shuffleSongList() {
		this.songs = new ArrayList<String>(this.songList.keySet());
		Collections.shuffle(songs);
	}

	// Shuffle songs in playlist
	public void shuffleInPlaylist(String name) {
		if (this.playlistList.containsKey(name)) {
			Playlist curr = this.playlistList.get(name);
			Playlist newPlay = new Playlist(name);
			ArrayList<Song> shuffled = new ArrayList<Song>(this.songList.values());
			Collections.shuffle(shuffled);
			for (Song song : shuffled) {
				if (curr.getList().contains(song.toString())) {
					newPlay.addSong(song);
				}
			}
			this.playlistList.remove(name);
			this.playlistList.put(name, newPlay);
		}
	}

	// Search for a song get its album in library
	public Album findAlbumBySong(Song song) {
		String albumTitle = song.getAlbum()[0];
		String artist = song.getArtist();
		if (this.albumList.containsKey(albumTitle + " " + artist)) {
			return this.albumList.get(albumTitle + " " + artist);
		}
		return null;
	}

	// Search for a song by title
	public ArrayList<Song> findSongByTitle(String title) {
		ArrayList<Song> songs = new ArrayList<>();
		for (Song s : this.songList.values()) {
			if (s.getTitle().equals(title)) {
				songs.add(s);
			}
		}
		return songs;
	}

	// Search for a song by artist
	public ArrayList<Song> findSongByArtist(String artist) {
		ArrayList<Song> songs = new ArrayList<>();
		for (Song s : this.songList.values()) {
			if (s.getArtist().equals(artist)) {
				songs.add(s);
			}
		}
		return songs;
	}

	// Search for songs by genre
	public ArrayList<Song> findSongsByGenre(String genre) {
		HashMap<String, ArrayList<Song>> songs = helperGenreList();
		if (songs.containsKey(genre)) {
			return songs.get(genre);
		}
		return null;
	}

	// Search for an album by title
	public ArrayList<Album> findAlbumByTitle(String title) {
		ArrayList<Album> albums = new ArrayList<>();
		for (Album a : this.albumList.values()) {
			if (a.getTitle().equals(title)) {
				albums.add(a);
			}
		}

		return albums;
	}

	// Search for an album by artist
	public ArrayList<Album> findAlbumByArtist(String artist) {
		ArrayList<Album> albums = new ArrayList<>();
		for (Album a : this.albumList.values()) {
			if (a.getArtist().equals(artist)) {
				albums.add(a);
			}
		}

		return albums;
	}

	// Search for a playlist by name
	public Playlist findPlaylist(String name) {
		if (this.playlistList.containsKey(name)) {
			return this.playlistList.get(name);
		}
		return null;
	}

	// Rate Song
	public void rateSong(Song song, Rate rate) {
		if (this.songList.containsKey(song.toString())) {
			this.songList.get(song.toString()).setRating(rate);
			if (rate.getValue() == 5) {
				this.setFav(song);
			}
		}
		this.topSongsPlaylist();
		this.favSongsPlaylist();
	}

	public void setFav(Song song) {
		if (this.songList.containsKey(song.toString())) {
			this.songList.get(song.toString()).setFav(true);
		}
		this.favSongsPlaylist();
	}

	/* GETTERS */
	// List of albums
	public ArrayList<String> getAlbumList() {
		ArrayList<String> list = new ArrayList<String>();
		for (Album album : this.albumList.values()) {
			list.add(album.getTitle() + " (" + album.getArtist() + ")");
		}
		return list;
	}

	// List of Song objects
	protected ArrayList<Song> getSongObjectList() {
		return new ArrayList<>(songList.values());
	}

	public ArrayList<String> getSongList() {
		ArrayList<String> list = new ArrayList<String>();
		for (String song : this.songs) {
			Song s = this.songList.get(song);
			list.add(s.getTitle() + " (" + s.getArtist() + ")");
		}
		return list;
	}

	public ArrayList<String> getSortedSongList() {
		ArrayList<String> list = this.getSongList();
		Collections.sort(list);
		return list;
	}

	public ArrayList<String> getSortedSongsByArtists() {
		ArrayList<String> artists = this.getArtists();
		ArrayList<String> list = new ArrayList<String>();
		Collections.sort(artists);
		for (String artist : artists) {
			for (Song song : this.songList.values()) {
				if (song.getArtist().equals(artist)) {
					list.add(song.getTitle() + " (" + song.getArtist() + ")");
				}
			}
		}
		return list;
	}

	public ArrayList<String> getSortedRatings() {
		// Create hashmap and organize songs by rating
		HashMap<Rate, ArrayList<String>> sort = new HashMap<>();
		ArrayList<String> list = new ArrayList<String>();
		sort.put(Rate.ONE, new ArrayList<String>());
		sort.put(Rate.TWO, new ArrayList<String>());
		sort.put(Rate.THREE, new ArrayList<String>());
		sort.put(Rate.FOUR, new ArrayList<String>());
		sort.put(Rate.FIVE, new ArrayList<String>());
		for (Song song : this.songList.values()) {
			Rate r = song.getRating();
			if (r == null) {
				list.add(song.getTitle() + " (" + song.getArtist() + ")");
			} else {
				sort.get(r).add(song.getTitle() + " (" + song.getArtist() + ")");
			}
		}
		// Insert all songs in order
		list.addAll(sort.get(Rate.ONE));
		list.addAll(sort.get(Rate.TWO));
		list.addAll(sort.get(Rate.THREE));
		list.addAll(sort.get(Rate.FOUR));
		list.addAll(sort.get(Rate.FIVE));

		return list;
	}

	// List of playlists
	public ArrayList<String> getAllPlayList() {
		ArrayList<String> list = new ArrayList<String>();
		for (Playlist playlist : this.playlistList.values()) {
			list.add(playlist.getName());
		}
		return list;
	}

	// List of artists
	public ArrayList<String> getArtists() {
		ArrayList<String> list = new ArrayList<String>();
		for (Album album : this.albumList.values()) {
			list.add(album.getArtist());
		}
		return list;
	}

	// List of favorite songs
	public ArrayList<String> getFavSongs() {
		ArrayList<String> list = new ArrayList<String>();
		for (Song song : this.songList.values()) {
			if (song.isFav()) {
				list.add(song.getTitle());
			}
		}
		return list;
	}

}
