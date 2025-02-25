package view;

import java.util.Scanner;

import database.MusicStore;
import model.LibraryModel;

public class MusicSim {
	public static void main(String[] args) {
		MusicStore MS = new MusicStore();
		LibraryModel userLibrary = new LibraryModel();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("What would you like to do? ");
		String input = scanner.nextLine();
		while (!input.equals("q")) {
			System.out.print("What would you like to do? ");
			input = scanner.nextLine();
		}
		scanner.close();
		System.exit(0);
	}
}
