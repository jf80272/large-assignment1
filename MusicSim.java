package view;

import java.util.ArrayList;
import java.util.Scanner;

import database.MusicStore;
import model.Album;
import model.LibraryModel;
import model.Playlist;
import model.Song;

public class MusicSim {
	private MusicStore ms;
	private LibraryModel userLibrary;
	private Scanner scanner;
	
	public MusicSim(String fileName) {
		ms = new MusicStore(fileName);
		userLibrary = new LibraryModel();
		scanner = new Scanner(System.in);
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
        		} else {
        			System.out.println("No such album exists in the database.\n");
        		}
        		break;
        	case  "4":
        		System.out.print("Enter the name of an artist: ");
        		String artist4 = scanner.nextLine().trim();
        		System.out.println();
        		ArrayList<Album> albums4 = ms.findAlbumByTitle(artist4);
        		if (!albums4.isEmpty()) {
        			for (Album a : albums4) {
        				System.out.println(a);
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
		System.out.println("1. For a song by title");
		System.out.println("2. For a song by artist");
		System.out.println("3. For an album by title");
		System.out.println("4. For an album by artist");
		System.out.println("5. For a playlist by name");
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
        		} else {
        			System.out.println("No such album exists in the database.\n");
        		}
        		break;
        	case  "4":
        		System.out.print("Enter the name of an artist: ");
        		String artist4 = scanner.nextLine().trim();
        		System.out.println();
        		ArrayList<Album> albums4 = ms.findAlbumByTitle(artist4);
        		if (!albums4.isEmpty()) {
        			for (Album a : albums4) {
        				System.out.println(a);
        			}
        		} else {
        			System.out.println("No such album exists in the database.\n");
        		}
        		break;
        	case "5":
        		System.out.print("Enter the name of a playlist: ");
        		String pName = scanner.nextLine().trim();
        		System.out.println();
        		Playlist p = userLibrary.findPlaylist(pName);
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
						userLibrary.addToSongList(s);
						songAdded = true;
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
	
	public void addAlbumToLibrary() {
		System.out.print("\nEnter album title: ");
		String title = scanner.nextLine().trim();
		ArrayList<Album> albums = ms.findAlbumByTitle(title);
		if (!albums.isEmpty()) {
			for (Album a : albums) {
				if (a.getTitle().equals(title)) {
					userLibrary.addToAlbumList(a);
					System.out.println("Added the following album: ");
					System.out.println(a);
					System.out.println();
				}
			}
		} else {
			System.out.println("Album was unable to be added.\n");
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
        		ArrayList<String> songList = userLibrary.getSongList();
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
        		ArrayList<String> artistList = userLibrary.getArtists();
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
        		ArrayList<String> albumList = userLibrary.getAlbumList();
        		if (!albumList.isEmpty()) {
	        		for (String s : userLibrary.getAlbumList()) {
	        			System.out.println(s);
	        		}
	        		System.out.println();
        		}
        		break;
        	case "4":
        		ArrayList<String> playListList = userLibrary.getAllPlayList();
        		if (!playListList.isEmpty()) {
	        		for (String s : userLibrary.getAllPlayList()) {
	        			System.out.println(s);
	        		}
	        		System.out.println();
        		} else {
        			System.out.println("Library contains no playlists.\n");
        		}
        		break;
        	case "5":
        		ArrayList<String> favSongList = userLibrary.getFavSongs();
        		if (!favSongList.isEmpty()) {
	        		for (String s : userLibrary.getFavSongs()) {
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

	public void createPlaylist() {
		System.out.print("\nEnter a name for the playlist: ");
		String name = scanner.nextLine().trim();
		userLibrary.createPlaylist(name);
		System.out.println("Playlist created.\n");
	}

	public void addSongToPlaylist() {
		System.out.print("\nEnter playlist name: ");
		String name = scanner.nextLine().trim();
		if (userLibrary.containsPlaylist(name)) {
			Playlist playlist = userLibrary.findPlaylist(name);
			System.out.print("Enter album title: ");
			String aTitle = scanner.nextLine().trim();
			System.out.print("Enter song title: ");
			String sTitle = scanner.nextLine().trim();
			
			ArrayList<Song> songs = userLibrary.findSongByTitle(sTitle);
			if (!songs.isEmpty()) {
				for (Song s : songs) {
					if (s.getAlbum().equals(aTitle)) {
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
		if (userLibrary.containsPlaylist(name)) {
			Playlist playlist = userLibrary.findPlaylist(name);
			System.out.print("Enter album title: ");
			String aTitle = scanner.nextLine().trim();
			System.out.print("Enter song title: ");
			String sTitle = scanner.nextLine().trim();
			
			ArrayList<Song> songs = userLibrary.findSongByTitle(sTitle);
			if (!songs.isEmpty()) {
				for (Song s : songs) {
					if (s.getAlbum().equals(aTitle)) {
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

	public void markFav() {
		System.out.print("Enter album title: ");
		String aTitle = scanner.nextLine().trim();
		System.out.print("Enter song title: ");
		String sTitle = scanner.nextLine().trim();
		
		ArrayList<Song> songs = userLibrary.findSongByTitle(sTitle);
		if (!songs.isEmpty()) {
			for (Song s : songs) {
				if (s.getAlbum().equals(aTitle)) {
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
		
		ArrayList<Song> songs = userLibrary.findSongByTitle(sTitle);
		if (!songs.isEmpty()) {
			System.out.print("Enter your rating: ");
			int rating = Integer.parseInt(scanner.nextLine().trim());
			
			for (Song s : songs) {
				if (s.getAlbum().equals(aTitle)) {
					// s.setRating(rating);
					System.out.println("Rated the following song: ");
					System.out.println(s);
					System.out.println();
				}
			}
		} else {
			System.out.println("Song was unable to be rated.\n");
		}
	}
}
