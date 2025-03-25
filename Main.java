package view;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		MusicSim ms = new MusicSim("albums/albums.txt");
		Scanner scanner = new Scanner(System.in);
		boolean active = true;

        while(active) {
        	System.out.println("Library options:");
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
            System.out.println("11. Create a new user account");
            System.out.println("12. Log in to a pre-existing user account");
            System.out.println("13. Log out of current account");
            System.out.println("14. Exit the program");
            System.out.print("\nType a number to get started: ");
            
            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                	ms.searchMusicStore();
                	break;
                case "2":
                	ms.searchLibrary();
                	break;
                case "3":
                	ms.addSongToLibrary();
                	break;
                case "4":
                	ms.addAlbumToLibrary();
                	break;
                case "5":
                	ms.getFromLibrary();
                	break;
                case "6":
                	ms.createPlaylist();
                	break;
                case "7":
                	ms.addSongToPlaylist();
                	break;
                case "8":
                	ms.removeSongFromPlaylist();
                	break;
                case "9":
                	ms.markFav();
                	break;
                case "10":
                	ms.rateSong();
                	break;
                case "11":
                	ms.newUser();
                	break;
                case "12":
                	ms.login();
                	break;
                case "13":
                	ms.logout();
                	break;
                case "14":
                	active = false;
                	System.out.println("Program closed");
                	break;
                default:
                	System.out.println("Invalid option");
            }
        }
        scanner.close();
		System.exit(0);
	}
}
