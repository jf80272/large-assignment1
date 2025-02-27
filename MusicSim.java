package view;

import java.util.Scanner;

import database.MusicStore;
import model.LibraryModel;

public class MusicSim {
	public static void main(String[] args) {
		MusicStore MS = new MusicStore("albums/albums.txt");
		LibraryModel userLibrary = new LibraryModel();
		
		Scanner scanner = new Scanner(System.in);
		boolean active = true;

        while(active) {
        	System.out.println("Type a number to get started: ");
            System.out.println("1. Search for information from the music store");
            System.out.println("2. Search for information from the library");
            System.out.println("3. Add a song to the library");
            System.out.println("4. Add an album to the library");
            System.out.println("5. Get a list of items from the library");
            System.out.println("6. Create a playlist");
            System.out.println("7. Add a song to a playlist");
            System.out.println("8. Remove a song from a playlist");
            System.out.println("9. Mark a song as favorite");
            System.out.println("10. Rate a song");
            System.out.println("11. Exit the program");
            
            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                	searchMusicStore();
                	break;
                case "2":
                	searchLibrary();
                	break;
                case "3":
                	addSongToLibrary();
                	break;
                case "4":
                	addSongToLibrary();
                	break;
                case "5":
                	getFromLibrary();
                	break;
                case "6":
                	createPlaylist();
                	break;
                case "7":
                	addSongToPlaylist();
                	break;
                case "8":
                	removeSongFromPlaylist();
                	break;
                case "9":
                	markFav();
                	break;
                case "10":
                	rateSong();
                	break;
                case "11":
                	active = false;
                	System.out.println("Program closed");
                	break;
                default:
                	System.out.println("Invalid option");
            }
        }
        scanner.close();
		scanner.close();
		System.exit(0);
	}

	private static void rateSong() {
		// TODO Auto-generated method stub
		
	}

	private static void markFav() {
		// TODO Auto-generated method stub
		
	}

	private static void removeSongFromPlaylist() {
		// TODO Auto-generated method stub
		
	}

	private static void addSongToPlaylist() {
		// TODO Auto-generated method stub
		
	}

	private static void createPlaylist() {
		// TODO Auto-generated method stub
		
	}

	private static void getFromLibrary() {
		// TODO Auto-generated method stub
		
	}

	private static void addSongToLibrary() {
		// TODO Auto-generated method stub
		
	}

	private static void searchLibrary() {
		// TODO Auto-generated method stub
		
	}

	private static void searchMusicStore() {
		// TODO Auto-generated method stub
		
	}	
}
