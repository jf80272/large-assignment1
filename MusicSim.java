package view;

import java.util.ArrayList;
import java.util.Scanner;

import database.MusicStore;
import database.UserData;
import model.Album;
import model.LibraryModel;
import model.Playlist;
import model.Song;
import model.Song.Rate;
import model.User;

public class MusicSim {
	private MusicStore ms;
	private LibraryModel library;
	private Scanner scanner;
	private UserData ud;
	private User currUser;
	
	public MusicSim(String fileName) {
		ms = new MusicStore(fileName);
		library = new LibraryModel();
		scanner = new Scanner(System.in);
		ud = new UserData();
		currUser = new User("guest", "guestpass");
	}

	public void searchMusicStore() {
		System.out.println("\nSearch options:");
		System.out.println("1. For a song by title");
		System.out.println("2. For a song by artist");
		System.out.println("3. For an album by title");
		System.out.println("4. For an album by artist");
		System.out.print("Type a number: ");

		String userChoice = scanner.nextLine();
		System.out.println();

		switch (userChoice) {
		case "1":
			System.out.print("Enter song title: ");
			String title1 = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Song> songs1 = ms.findSongByTitle(title1);
			if (!songs1.isEmpty()) {
				for (Song s : songs1) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("No such song exists in the database.\n");
			}
			break;
		case "2":
			System.out.print("Enter the name of an artist: ");
			String artist2 = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Song> songs2 = ms.findSongByArtist(artist2);
			if (!songs2.isEmpty()) {
				for (Song s : songs2) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("No such song exists in the database.\n");
			}
			break;
		case "3":
			System.out.print("Enter album title: ");
			String title3 = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Album> albums3 = ms.findAlbumByTitle(title3);
			if (!albums3.isEmpty()) {
				for (Album a : albums3) {
					System.out.println(a);
				}
				System.out.println();
			} else {
				System.out.println("No such album exists in the database.\n");
			}
			break;
		case "4":
			System.out.print("Enter the name of an artist: ");
			String artist4 = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Album> albums4 = ms.findAlbumByArtist(artist4);
			if (!albums4.isEmpty()) {
				for (Album a : albums4) {
					System.out.println(a);
					System.out.println();
				}
			} else {
				System.out.println("No such album exists in the database.\n");
			}
			break;
		default:
			System.out.println("Invalid option\n");
		}
	}

	public void searchLibrary() {
		System.out.println("\nSearch options: ");
		System.out.println("1. For songs by title");
		System.out.println("2. For songs by artist");
		System.out.println("3. For songs by genre");
		System.out.println("4. For albums by title");
		System.out.println("5. For albums by artist");
		System.out.println("6. For an album by song");
		System.out.println("7. For a playlist by name");
		System.out.print("Type a number: ");

		String userChoice = scanner.nextLine();
		System.out.println();

		switch (userChoice) {
		case "1":
			System.out.print("Enter song title: ");
			String title1 = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Song> songs1 = ms.findSongByTitle(title1);
			if (!songs1.isEmpty()) {
				for (Song s : songs1) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("No such song exists in the database.\n");
			}
			break;
		case "2":
			System.out.print("Enter the name of an artist: ");
			String artist2 = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Song> songs2 = ms.findSongByArtist(artist2);
			if (!songs2.isEmpty()) {
				for (Song s : songs2) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("No such song exists in the database.\n");
			}
			break;
		case "3":
			System.out.print("Enter genre: ");
			String genre = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Song> songs3 = library.findSongsByGenre(genre);
			if (!songs3.isEmpty()) {
				for (Song s : songs3) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("No such song exists in the database.\n");
			}
			break;
		case "4":
			System.out.print("Enter album title: ");
			String title3 = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Album> albums3 = ms.findAlbumByTitle(title3);
			if (!albums3.isEmpty()) {
				for (Album a : albums3) {
					System.out.println(a);
				}
				System.out.println();
			} else {
				System.out.println("No such album exists in the database.\n");
			}
			break;
		case "5":
			System.out.print("Enter the name of an artist: ");
			String artist4 = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Album> albums4 = ms.findAlbumByArtist(artist4);
			if (!albums4.isEmpty()) {
				for (Album a : albums4) {
					System.out.println(a);
				}
				System.out.println();
			} else {
				System.out.println("No such album exists in the database.\n");
			}
			break;
		case "6":
			System.out.print("Enter song title: ");
			String title2 = scanner.nextLine().trim();
			System.out.print("Enter the name of an artist: ");
			String artist5 = scanner.nextLine().trim();
			System.out.println();
			ArrayList<Song> songs = library.findSongByTitle(title2);
			for (Song song : songs) {
				if (song.getArtist().equals(artist5)) {
					Album album = ms.findAlbumByTitle(song.getAlbum()[0]).get(0);
					Album albumInLibrary = library.findAlbumBySong(song);
					System.out.println("Full Album: ");
					System.out.println(album.toString());
					System.out.println();
					if (albumInLibrary != null && album.toString().equals(albumInLibrary.toString())) {
						System.out.println("Full album in library\n");
					} else {
						System.out.println("Full album not in library\n");
					}
					break;
				}
			}
			break;
		case "7":
			System.out.print("Enter the name of a playlist: ");
			String pName = scanner.nextLine().trim();
			System.out.println();
			Playlist p = library.findPlaylist(pName);
			if (p != null) {
				System.out.println(p);
			} else {
				System.out.println("Library does not contain a playlist with that name.\n");
			}
			break;
		default:
			System.out.println("Invalid option\n");
		}
	}

	public void addSongToLibrary() {
		System.out.print("\nEnter album title: ");
		String aTitle = scanner.nextLine().trim();
		System.out.print("Enter song title: ");
		String sTitle = scanner.nextLine().trim();
		ArrayList<Album> albums = ms.findAlbumByTitle(aTitle);
		boolean songAdded = false;

		if (!albums.isEmpty()) {
			for (Album a : albums) {
				for (Song s : a.getSongList()) {
					if (s.getTitle().equals(sTitle)) {
						library.addToSongList(s);
						songAdded = true;
						currUser.updateSongPlays();
						
						System.out.println("Added the following song: ");
						System.out.println(s);
						System.out.println();
					}
				}
			}
		}

		if (!songAdded) {
			System.out.println("Song was unable to be added.\n");
		}
	}

	public void removeSongFromLibrary() {
		System.out.print("\nEnter album title: ");
		String aTitle = scanner.nextLine().trim();
		System.out.print("Enter song title: ");
		String sTitle = scanner.nextLine().trim();
		ArrayList<Album> albums = ms.findAlbumByTitle(aTitle);
		boolean songRemoved = false;
		if (!albums.isEmpty()) {
			for (Album a : albums) {
				for (Song s : a.getSongList()) {
					if (s.getTitle().equals(sTitle)) {
						library.rmFromSongList(s);
						songRemoved = true;
						System.out.println("Removed the following song: ");
						System.out.println(sTitle + " in " + aTitle);
						System.out.println();
					}
				}
			}
		}
		if (!songRemoved) {
			System.out.println("Song was unable to be removed.\n");
		}
	}

	public void addAlbumToLibrary() {
		System.out.print("\nEnter album title: ");
		String title = scanner.nextLine().trim();
		ArrayList<Album> albums = ms.findAlbumByTitle(title);
		if (!albums.isEmpty()) {
			for (Album a : albums) {
				if (a.getTitle().equals(title)) {
					library.addToAlbumList(a);
					currUser.updateSongPlays();
					
					System.out.println("Added the following album: ");
					System.out.println(a);
					System.out.println();
				}
			}
		} else {
			System.out.println("Album was unable to be added.\n");
		}
	}

	public void removeAlbumFromLibrary() {
		System.out.print("\nEnter album title: ");
		String title = scanner.nextLine().trim();
		ArrayList<Album> albums = ms.findAlbumByTitle(title);
		if (!albums.isEmpty()) {
			for (Album a : albums) {
				if (a.getTitle().equals(title)) {
					library.rmFromAlbumList(a);
					System.out.println("Removed the following album: ");
					System.out.println(title);
					System.out.println();
				}
			}
		} else {
			System.out.println("Album was unable to be removed.\n");
		}
	}

	public void shuffleLibrary() {
		System.out.println("\nShuffle: ");
		System.out.println("1. Songs in Library");
		System.out.println("2. A Playlist");

		System.out.print("Type a number: ");
		String userChoice = scanner.nextLine();
		System.out.println();

		switch (userChoice) {
		case "1":
			library.shuffleSongList();
			System.out.println("Shuffled!");
			System.out.println();
			break;
		case "2":
			System.out.print("Playlist name: ");
			userChoice = scanner.nextLine();
			library.shuffleInPlaylist(userChoice);
			System.out.println("Shuffled " + userChoice + "!");
			System.out.println();
			break;
		default:
			System.out.println("Invalid option\n");
		}
	}

	public void getFromLibrary() {
		System.out.println("\nList options: ");
		System.out.println("1. Songs");
		System.out.println("2. Artists");
		System.out.println("3. Albums");
		System.out.println("4. Playlists");
		System.out.println("5. Favorite songs");
		System.out.print("Type a number: ");

		String userChoice = scanner.nextLine();
		System.out.println();

		switch (userChoice) {
		case "1":
			orderOfSongs();
			break;
		case "2":
			ArrayList<String> artistList = library.getArtists();
			if (!artistList.isEmpty()) {
				for (String s : artistList) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("Library contains no songs or albums.\n");
			}
			break;
		case "3":
			ArrayList<String> albumList = library.getAlbumList();
			if (!albumList.isEmpty()) {
				for (String s : library.getAlbumList()) {
					System.out.println(s);
				}
				System.out.println();
			}
			break;
		case "4":
			ArrayList<String> playListList = library.getAllPlayList();
			if (!playListList.isEmpty()) {
				for (String s : library.getAllPlayList()) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("Library contains no playlists.\n");
			}
			break;
		case "5":
			ArrayList<String> favSongList = library.getFavSongs();
			if (!favSongList.isEmpty()) {
				for (String s : library.getFavSongs()) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("Library contains no favorite songs.\n");
			}
			break;
		default:
			System.out.println("Invalid option\n");
		}
	}

	public void orderOfSongs() {
		System.out.println("\nOrder By: ");
		System.out.println("1. Default");
		System.out.println("2. Song title");
		System.out.println("3. Artist");
		System.out.println("4. Rating");
		System.out.print("Type a number: ");

		String userChoice = scanner.nextLine();
		System.out.println();

		switch (userChoice) {
		case "1":
			ArrayList<String> songList = library.getSongList();
			if (!songList.isEmpty()) {
				for (String s : songList) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("Library contains no songs.\n");
			}
			break;
		case "2":
			ArrayList<String> sortedSongList = library.getSortedSongList();
			if (!sortedSongList.isEmpty()) {
				for (String s : sortedSongList) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("Library contains no songs.\n");
			}
			break;
		case "3":
			ArrayList<String> ArtistSongList = library.getSortedSongsByArtists();
			if (!ArtistSongList.isEmpty()) {
				for (String s : ArtistSongList) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("Library contains no songs.\n");
			}
			break;
		case "4":
			ArrayList<String> songgRate = library.getSortedRatings();
			if (!songgRate.isEmpty()) {
				for (String s : songgRate) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("Library contains no songs.\n");
			}
			break;
		default:
			System.out.println("Invalid option\n");
		}
	}

	public void createPlaylist() {
		System.out.print("\nEnter a name for the playlist: ");
		String name = scanner.nextLine().trim();
		library.createPlaylist(name);
		System.out.println("Playlist created.\n");
	}

	public void addSongToPlaylist() {
		System.out.print("\nEnter playlist name: ");
		String name = scanner.nextLine().trim();
		if (library.containsPlaylist(name)) {
			Playlist playlist = library.findPlaylist(name);
			System.out.print("Enter album title: ");
			String aTitle = scanner.nextLine().trim();
			System.out.print("Enter song title: ");
			String sTitle = scanner.nextLine().trim();

			ArrayList<Song> songs = library.findSongByTitle(sTitle);
			if (!songs.isEmpty()) {
				for (Song s : songs) {
					if (s.getAlbum()[0].equals(aTitle)) {
						playlist.addSong(s);
						System.out.println("Added the following song: ");
						System.out.println(s);
						System.out.println();
					}
				}
			} else {
				System.out.println("Song was unable to be added.\n");
			}
		} else {
			System.out.println("Library does not contain a playlist with that name.\n");
		}

	}

	public void removeSongFromPlaylist() {
		System.out.print("\nEnter playlist name: ");
		String name = scanner.nextLine().trim();
		if (library.containsPlaylist(name)) {
			Playlist playlist = library.findPlaylist(name);
			System.out.print("Enter album title: ");
			String aTitle = scanner.nextLine().trim();
			System.out.print("Enter song title: ");
			String sTitle = scanner.nextLine().trim();

			ArrayList<Song> songs = library.findSongByTitle(sTitle);
			if (!songs.isEmpty()) {
				for (Song s : songs) {
					if (s.getAlbum()[0].equals(aTitle)) {
						playlist.rmSong(s);
						System.out.println("Removed the following song: ");
						System.out.println(s);
						System.out.println();
					}
				}
			} else {
				System.out.println("Song was unable to be removed.\n");
			}
		} else {
			System.out.println("Library does not contain a playlist with that name.\n");
		}
	}

	public void viewPlaylist() {
		System.out.print("\nEnter playlist name: ");
		String name = scanner.nextLine().trim();
		if (library.containsPlaylist(name)) {
			Playlist pl = library.findPlaylist(name);
			for (String song : pl.getList()) {
				System.out.println(song);
			}
		}
		System.out.println();
	}

	public void markFav() {
		System.out.print("Enter album title: ");
		String aTitle = scanner.nextLine().trim();
		System.out.print("Enter song title: ");
		String sTitle = scanner.nextLine().trim();

		ArrayList<Song> songs = library.findSongByTitle(sTitle);
		if (!songs.isEmpty()) {
			for (Song s : songs) {
				if (s.getAlbum()[0].equals(aTitle)) {
					s.setFav(true);
					System.out.println("Marked the following song as favorite: ");
					System.out.println(s);
					System.out.println();
				}
			}
		} else {
			System.out.println("Song was unable to be marked as favorite.\n");
		}
	}

	public void rateSong() {
		System.out.print("Enter album title: ");
		String aTitle = scanner.nextLine().trim();
		System.out.print("Enter song title: ");
		String sTitle = scanner.nextLine().trim();

		ArrayList<Song> songs = library.findSongByTitle(sTitle);
		if (!songs.isEmpty()) {
			System.out.print("Enter your rating: ");
			int rating = Integer.parseInt(scanner.nextLine().trim());
			Rate r = intToRate(rating);
			while (r == null) {
				System.out.print("Rating is out of range. Try again: ");
				rating = Integer.parseInt(scanner.nextLine().trim());
				r = intToRate(rating);
			}
			for (Song s : songs) {
				if (s.getAlbum()[0].equals(aTitle)) {
					s.setRating(r);
					library.topSongsPlaylist();

					System.out.println("Rated the following song: ");
					System.out.println(s);
					System.out.println();
				}
			}
		} else {
			System.out.println("Song was unable to be rated.\n");
		}
	}

	public void playSong() {
		System.out.print("Enter album title: ");
		String aTitle = scanner.nextLine().trim();
		System.out.print("Enter song title: ");
		String sTitle = scanner.nextLine().trim();
		
		ArrayList<Song> songs = library.findSongByTitle(sTitle);
		if (!songs.isEmpty()) {
			for (Song s : songs) {
				if (s.getAlbum()[0].equals(aTitle)) {
					if (currUser != null) {
						currUser.playSong(s.getTitle(), s.getArtist());
					}
					
					System.out.println("Playing: ");
					System.out.println(s);
					System.out.println();
				}
			}
		} else {
			System.out.println("Song was unable to be played.\n");
		}
	}

	public void getRecentPlays() {
		if (currUser != null) {
			Song[] recentPlays = currUser.getRecentPlays();
			System.out.println("Most recently played songs:");
			for (Song s : recentPlays) {
				System.out.println(s);
			}
			System.out.println();
		} else {
			System.out.println("Cannot be done without logging in.\n");
		}
	}

	public void getFrequentPlays() {
		if (currUser != null) {
			Song[] mostPlays = currUser.getMostPlays();
			System.out.println("Most frequently played songs:");
			for (Song s : mostPlays) {
				System.out.println(s);
			}
			System.out.println();
		} else {
			System.out.println("Cannot be done without logging in.\n");
		}
	}
	
	public void newUser() {
		System.out.print("Enter a username: ");
		String username = scanner.nextLine().trim();
		System.out.print("Enter a password: ");
		String password = scanner.nextLine().trim();
		
		User u = new User(username, password);
		ud.addUserData(username, u);
		System.out.println("User created successfully.\n");
	}
	
	public void login() {
		if (currUser.getUsername().equals("guest")) {
			System.out.print("Enter a username: ");
			String username = scanner.nextLine().trim();
			System.out.print("Enter a password: ");
			String password = scanner.nextLine().trim();
			
			User u = ud.getUser(username, password);
			if (u != null) {
				library = u.getLibrary();
				currUser = u;
				System.out.println("Login successful. Welcome, " + username + "!\n");
			} else {
				System.out.println("Username or password was incorrect.\n");
			}
		} else {
			System.out.println("You're already logged in.\n");
		}
	}
	
	public void logout() {
		if (!currUser.getUsername().equals("guest")) {
			currUser = ud.getUser("guest", "guestpass");
			library = new LibraryModel();
			System.out.println("Logout successful.\n");
		} else {
			System.out.println("You're already logged out.\n");
		}
	}

	private Rate intToRate(int rate) {
		if (rate == 1) {
			return Rate.ONE;
		} else if (rate == 2) {
			return Rate.TWO;
		} else if (rate == 3) {
			return Rate.THREE;
		} else if (rate == 4) {
			return Rate.FOUR;
		} else if (rate == 5) {
			return Rate.FIVE;
		} else {
			return null;
		}
	}
}
