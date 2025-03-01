package test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import model.*;
import model.Song.Rate;

public class TestModel {
	
	@Test
	public void testGetAlbum(){
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Song s4 = new Song("s4", "Jill", "t2");
		Song s5 = new Song("s5", "Jill", "t2");
		Song s6 = new Song("s6", "Jill", "t3");
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToAlbumList(quickAlbum2);
		
		assertEquals(library.getAlbumList(), new ArrayList<String>(Arrays.asList("t1 (Mary)", "t2 (Jill)")));
	}
	
	@Test
	public void testGetSong() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		Song s4 = new Song("s4", "Jill", "t2");
		Song s5 = new Song("s5", "Jill", "t2");
		Song s6 = new Song("s6", "Jill", "t3");
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToSongList(s4);
		
		String[] test1 = {"s1 (Mary)", "s2 (Mary)", "s3 (Mary)", "s4 (Jill)"};
		assertEquals(library.getSongList(), new ArrayList<String>(Arrays.asList(test1)));
		
		library.addToAlbumList(quickAlbum2);
		
		String[] test2 = {"s1 (Mary)", "s2 (Mary)", "s3 (Mary)", "s4 (Jill)", "s5 (Jill)", "s6 (Jill)"};
		assertEquals(library.getSongList(), new ArrayList<String>(Arrays.asList(test2)));
	}
	
	@Test
	public void testGetArtists() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		Song s4 = new Song("s4", "Jill", "t2");
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		
		library.addToAlbumList(quickAlbum1);
		library.addToSongList(s4);
		
		assertEquals(library.getArtists(), new ArrayList<String>(Arrays.asList("Mary", "Jill")));
	}
	
	@Test
	public void testGetPlayList() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		
		library.createPlaylist("p1");
		library.createPlaylist("p2");
		library.createPlaylist("p2");
		library.addToPlaylist(s1, "p1");
		library.addToPlaylist(s2, "p1");
		library.removeFromPlaylist(s2, "p1");
		library.addToPlaylist(s3, "p2");
		
		assertEquals(library.getAllPlayList(), new ArrayList<String>(Arrays.asList("p1", "p2")));
		
	}
	
	@Test
	public void getFavSongs() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("sameName", "Mary", "t1");
		Song s4 = new Song("sameName", "Jill", "t2");
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		
		library.addToAlbumList(quickAlbum1);
		library.addToSongList(s4);
		library.setFav(s2);
		library.setFav(s4);
		
		assertEquals(library.getFavSongs(), new ArrayList<String>(Arrays.asList("s2", "sameName")));
		
	}
	
	// Test Adders
	@Test
	public void testAddAlbum() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Song s4 = new Song("s4", "Jill", "t2");
		Song s5 = new Song("s5", "Jill", "t2");
		Song s6 = new Song("s6", "Jill", "t2");
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		Album quickAlbum3 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		Album quickAlbum4 = new Album("t3", "Jill", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToAlbumList(quickAlbum2);
		library.addToAlbumList(quickAlbum3);
		library.addToAlbumList(quickAlbum4);
		
		assertEquals(library.getAlbumList(), new ArrayList<String>(Arrays.asList("t1 (Mary)", "t2 (Jill)", "t3 (Jill)")));
		
	}
	
	@Test
	public void testAddSong() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		Song s4 = new Song("s1", "Jill", "t2");
		
		library.addToSongList(s1);
		library.addToSongList(s2);
		library.addToSongList(s2);
		library.addToSongList(s3);
		library.addToSongList(s4);
		
		String test[]  = {"s1 (Mary)", "s2 (Mary)", "s3 (Mary)", "s1 (Jill)"};
		assertEquals(library.getSongList(), new ArrayList<String>(Arrays.asList(test)));
		
	}
	
	@Test
	public void testAddPlaylist() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Jill", "t2");
		Song s4 = new Song("s4", "Jill", "t2");
		
		library.createPlaylist("name1");
		library.createPlaylist("name1");
		library.createPlaylist("name2");
		
		library.addToPlaylist(s1, "name1");
		library.addToPlaylist(s2, "name1");
		library.addToPlaylist(s3, "name2");
		library.addToPlaylist(s4, "name2");
		library.addToPlaylist(s4, "name1");
		
		assertEquals(library.getAllPlayList(), new ArrayList<String>(Arrays.asList("name1", "name2")));
		
		Playlist test1 = library.findPlaylist("name1");
		assertEquals(test1.getList(), new ArrayList<String>(Arrays.asList("s1 (t1, Mary)", "s2 (t1, Mary)", "s4 (t2, Jill)")));
	}
	
	@Test
	public void testRmFromPlaylist() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		
		library.createPlaylist("name1");
		library.addToPlaylist(s1, "name1");
		library.addToPlaylist(s2, "name1");
		
		library.removeFromPlaylist(s2, "name1");
		Playlist test1 = library.findPlaylist("name1");
		assertEquals(test1.getList(), new ArrayList<String>(Arrays.asList("s1 (t1, Mary)")));
	}
	
	@Test
	public void testFindSongByTitle() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s1", "Lily", "t1");
		Song s3 = new Song("s1", "Mary", "t1");
		Song s4 = new Song("s3", "Mary", "t1");
		
		library.addToSongList(s1);
		library.addToSongList(s2);
		library.addToSongList(s3);
		library.addToSongList(s4);
		
		ArrayList<Song> test1 = library.findSongByTitle("s1");
		assertEquals(test1.size(), 2);
		
	}
	
	@Test
	public void testFindSongByArtist() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s1", "Lily", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		Song s4 = new Song("s4", "Mary", "t1");
		
		library.addToSongList(s1);
		library.addToSongList(s2);
		library.addToSongList(s3);
		library.addToSongList(s4);
		
		ArrayList<Song> test1 = library.findSongByArtist("Mary");
		assertEquals(test1.size(), 3);
	}
	
	@Test
	public void testFindAlbumByTitle() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Song s4 = new Song("s4", "Jill", "t2");
		Song s5 = new Song("s5", "Jill", "t2");
		Song s6 = new Song("s6", "Jill", "t2");
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		Album quickAlbum3 = new Album("t2", "FAKE", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToAlbumList(quickAlbum2);
		library.addToAlbumList(quickAlbum3);
		
		ArrayList<Album> test1 = library.findAlbumByTitle("t2");
		assertEquals(test1.size(), 2);
	}
	
	@Test
	public void testFindAlbumByArtist() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Song s4 = new Song("s4", "Jill", "t2");
		Song s5 = new Song("s5", "Jill", "t2");
		Song s6 = new Song("s6", "Jill", "t2");
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		Album quickAlbum3 = new Album("t2", "Mary", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToAlbumList(quickAlbum2);
		library.addToAlbumList(quickAlbum3);
		
		ArrayList<Album> test1 = library.findAlbumByArtist("Mary");
		assertEquals(test1.size(), 2);
	}
	
	@Test
	public void testFindPlaylist() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Jill", "t2");
		Song s4 = new Song("s4", "Jill", "t2");
		
		library.createPlaylist("name1");
		library.createPlaylist("name1");
		library.createPlaylist("name2");
		
		library.addToPlaylist(s1, "name1");
		library.addToPlaylist(s2, "name1");
		library.addToPlaylist(s3, "name2");
		library.addToPlaylist(s4, "name2");
		library.addToPlaylist(s4, "name1");
		
		Playlist test1 = library.findPlaylist("name1");
		Playlist test2 = library.findPlaylist("DNE");
		assertEquals(test1.getList().size(), 3);
		assertEquals(test2, null);
	}
	
	@Test
	public void testRateSong() {
		LibraryModel library = new LibraryModel();
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		library.addToSongList(s1);
		library.addToSongList(s2);
		
		library.rateSong(s1, Rate.ONE);
		library.rateSong(s2, Rate.FIVE);
		
		ArrayList<Song> test1 = library.findSongByArtist("Mary");
		assertEquals(test1.get(0).getRating(), Rate.ONE);
		assertTrue(test1.get(1).isFav());
	}
	
	@Test
	public void testAlbumToString() {
		Song s1 = new Song("s1", "Mary", "t1");
		Song s2 = new Song("s2", "Mary", "t1");
		Song s3 = new Song("s3", "Mary", "t1");
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		String test1 = "t1_Mary_Pop_2008\ns1\ns2\ns3";
		assertEquals(quickAlbum1.toString(), test1);
	}
}
