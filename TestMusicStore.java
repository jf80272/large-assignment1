package test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import database.MusicStore;
import model.Album;
import model.Song;

public class TestMusicStore {
	
	@Test
	public void testMusicStore() {
		MusicStore ms = new MusicStore("albums/albums.txt");
		
		ArrayList<Song> songByTitle = ms.findSongByTitle("Lullaby");
		ArrayList<Song> songByArtist = ms.findSongByArtist("Adele");
		
		ArrayList<Album> albumByTitle = ms.findAlbumByTitle("21");
		ArrayList<Album> albumByArtist = ms.findAlbumByArtist("Adele");
		
		assertEquals(songByArtist.size(), 24);
		assertEquals(songByTitle.size(), 2);
		assertEquals(albumByArtist.size(), 2);
		assertEquals(albumByTitle.size(), 1);
	}
}
