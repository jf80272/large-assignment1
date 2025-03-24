package view;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		MusicSim ms = new MusicSim("albums/albums.txt");
		Scanner scanner = new Scanner(System.in);
		boolean active = true;

		while (active) {
			System.out.println("Library options:");
			System.out.println("1. Search for information from the music store");
			System.out.println("2. Search for information from the library");
			System.out.println("3. Add a song to the library");
			System.out.println("4. Add an album to the library");
			System.out.println("5. Remove a song from the library");
			System.out.println("6. Remove an album from the library");
			System.out.println("7. Get a list of items from the library");
			System.out.println("8. Shuffle Songs");
			System.out.println("9. Create a playlist");
			System.out.println("10. Add a song to a playlist");
			System.out.println("11. View a playlist");
			System.out.println("12. Remove a song from a playlist");
			System.out.println("13. Mark a song as favorite");
			System.out.println("14. Rate a song");
			System.out.println("15. Exit the program");
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
				ms.removeSongFromLibrary();
				break;
			case "6":
				ms.removeAlbumFromLibrary();
				break;
			case "7":
				ms.getFromLibrary();
				break;
			case "8":
				ms.shuffleLibrary();
				break;
			case "9":
				ms.createPlaylist();
				break;
			case "10":
				ms.addSongToPlaylist();
				break;
			case "11":
				ms.viewPlaylist();
				break;
			case "12":
				ms.removeSongFromPlaylist();
				break;
			case "13":
				ms.markFav();
				break;
			case "14":
				ms.rateSong();
				break;
			case "15":
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
