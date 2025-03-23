package test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import model.*;
import model.Song.Rate;

public class TestModel {
	
	@Test
	public void testGetAlbum(){
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		Song s5 = new Song("s5", "Jill", quickAlbumInfo2);
		Song s6 = new Song("s6", "Jill", quickAlbumInfo2);
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToAlbumList(quickAlbum2);
		
		
		assertEquals(library.getAlbumList().size(), 2);
	}
	
	@Test
	public void testGetSong() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		Song s5 = new Song("s5", "Jill", quickAlbumInfo2);
		Song s6 = new Song("s6", "Jill", quickAlbumInfo2);
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToSongList(s4);
		
		String[] test1 = {"s1 (Mary)", "s2 (Mary)", "s3 (Mary)", "s4 (Jill)"};
		ArrayList <String> sortedSongList1 = library.getSongList();
		Collections.sort(sortedSongList1);
		assertEquals(sortedSongList1, new ArrayList<String>(Arrays.asList(test1)));
		
		library.addToAlbumList(quickAlbum2);
		ArrayList <String> sortedSongList2 = library.getSortedSongList();
		String[] test2 = {"s1 (Mary)", "s2 (Mary)", "s3 (Mary)", "s4 (Jill)", "s5 (Jill)", "s6 (Jill)"};
		assertEquals(sortedSongList2, new ArrayList<String>(Arrays.asList(test2)));
	}
	
	@Test
	public void testGetSongsSortedByArtist() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		String[] quickAlbumInfo3 = {"t3", "Lily", "Pop", "2008"};
		String[] quickAlbumInfo4 = {"t4", "Tom", "Pop", "2011"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Jill", quickAlbumInfo2);
		Song s3 = new Song("s3", "Lily", quickAlbumInfo3);
		Song s4 = new Song("s1", "Tom", quickAlbumInfo4);
		
		library.addToSongList(s1);
		library.addToSongList(s2);
		library.addToSongList(s3);
		library.addToSongList(s4);
		
		String test[] = {"s2 (Jill)", "s3 (Lily)", "s1 (Mary)", "s1 (Tom)"};
		assertEquals(library.getSortedSongsByArtists(), new ArrayList<String>(Arrays.asList(test)));
		
	}
	
	@Test
	public void testShuffleSongList() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		Song s5 = new Song("s5", "Jill", quickAlbumInfo2);
		Song s6 = new Song("s6", "Jill", quickAlbumInfo2);
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToAlbumList(quickAlbum2);
		
		String shuffle0[] = {"s2 (Mary)", "s3 (Mary)", "s1 (Mary)", "s6 (Jill)", "s4 (Jill)", "s5 (Jill)"};
		ArrayList<String> songs = new ArrayList<String>(Arrays.asList(shuffle0));
		assertTrue(songs.equals(library.getSongList()));
		
		library.shuffleSongList();
		ArrayList<String> shuffle1 = library.getSongList();
		assertFalse(songs.equals(library.getSongList()));
		assertTrue(shuffle1.equals(library.getSongList()));
		
		library.shuffleSongList();
		assertFalse(shuffle1.equals(library.getSongList()));
	}
	
	@Test
	public void getSortedRatings() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		Song s4 = new Song("s4", "Mary", quickAlbumInfo1);
		Song s5 = new Song("s5", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3, s4, s5));
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		
		library.addToAlbumList(quickAlbum1);
		
		library.rateSong(s1, Rate.FIVE);
		library.rateSong(s2, Rate.ONE);
		library.rateSong(s3, Rate.THREE);
		library.rateSong(s4, Rate.FOUR);
		
		String test[] = {"s5 (Mary)", "s2 (Mary)", "s3 (Mary)", "s4 (Mary)", "s1 (Mary)"};
		assertEquals(library.getSortedRatings(), new ArrayList<String>(Arrays.asList(test)));
		
	}

	@Test
	public void testGetArtists() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		
		library.addToAlbumList(quickAlbum1);
		library.addToSongList(s4);
		
		ArrayList<String> sortedArtists = library.getArtists();
		Collections.sort(sortedArtists);
		assertEquals(sortedArtists, new ArrayList<String>(Arrays.asList("Jill", "Mary")));
	}

	@Test
	public void testGetPlayList() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		
		library.createPlaylist("p1");
		library.createPlaylist("p2");
		library.createPlaylist("p2");
		library.addToPlaylist(s1, "p1");
		library.addToPlaylist(s2, "p1");
		library.removeFromPlaylist(s2, "p1");
		library.addToPlaylist(s3, "p2");
		
		assertEquals(library.getAllPlayList().size(), 4);
		
	}
	
	@Test
	public void getFavSongs() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("sameName", "Mary", quickAlbumInfo1);
		Song s4 = new Song("sameName", "Jill", quickAlbumInfo2);
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
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		Song s5 = new Song("s5", "Jill", quickAlbumInfo2);
		Song s6 = new Song("s6", "Jill", quickAlbumInfo2);
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		Album quickAlbum3 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		Album quickAlbum4 = new Album("t3", "Jill", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToAlbumList(quickAlbum2);
		library.addToAlbumList(quickAlbum3);
		library.addToAlbumList(quickAlbum4);
		
		ArrayList<String> sortedList = library.getAlbumList();
		Collections.sort(sortedList);
		assertEquals(sortedList, new ArrayList<String>(Arrays.asList("t1 (Mary)", "t2 (Jill)", "t3 (Jill)")));
		assertEquals(library.getSongList().size(), 6);
		
	}
	
	@Test
	public void testRemoveAlbum() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		Song s5 = new Song("s5", "Jill", quickAlbumInfo2);
		Song s6 = new Song("s6", "Jill", quickAlbumInfo2);
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		
		library.addToAlbumList(quickAlbum1);
		library.addToAlbumList(quickAlbum2);
		
		assertEquals(library.getAlbumList().size(), 2);
		assertEquals(library.getSongList().size(), 6);
		library.rmFromAlbumList(quickAlbum2);
		assertEquals(library.getAlbumList().size(), 1);
		assertEquals(library.getSongList().size(), 3);
	}
	
	@Test
	public void testAddSong() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s4 = new Song("s1", "Jill", quickAlbumInfo2);
		
		library.addToSongList(s1);
		Album test = library.findAlbumByTitle("t1").get(0);
		assertEquals(test.getSongList().size(), 1);
		library.addToSongList(s2);
		library.addToSongList(s2);
		library.addToSongList(s3);
		library.addToSongList(s4);
		
		assertEquals(library.getSongList().size(), 4);
		assertEquals(library.getAlbumList().size(), 2);
		test = library.findAlbumByTitle("t1").get(0);
		assertEquals(test.getSongList().size(), 3);
		
	}
	
	@Test
	public void testRemoveSong() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		library.addToAlbumList(quickAlbum1);
		
		library.createPlaylist("name1");
		library.addToPlaylist(s3, "name1");
		
		assertEquals(library.getSongList().size(), 3);
		assertEquals(library.findAlbumByArtist("Mary").get(0).getSongList().size(), 3);
		assertEquals(library.findPlaylist("name1").getList().size(), 1);
		library.rmFromSongList(s3);
		assertEquals(library.getSongList().size(), 2);
		assertEquals(library.findAlbumByArtist("Mary").get(0).getSongList().size(), 2);
		assertEquals(library.findPlaylist("name1").getList().size(), 0);
	}
	
	@Test
	public void testAddPlaylist() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		
		library.createPlaylist("name1");
		library.createPlaylist("name1");
		library.createPlaylist("name2");
		
		library.addToPlaylist(s1, "name1");
		library.addToPlaylist(s2, "name1");
		library.addToPlaylist(s3, "name2");
		library.addToPlaylist(s4, "name2");
		library.addToPlaylist(s4, "name1");
		
		assertEquals(library.getAllPlayList().size(), 4);
		
		Playlist test1 = library.findPlaylist("name1");
		assertEquals(test1.getList(), new ArrayList<String>(Arrays.asList("s1 (t1, Mary)", "s2 (t1, Mary)", "s4 (t2, Jill)")));
	}
	
	@Test
	public void testRmFromPlaylist() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		
		library.createPlaylist("name1");
		library.addToPlaylist(s1, "name1");
		library.addToPlaylist(s2, "name1");
		
		library.removeFromPlaylist(s2, "name1");
		Playlist test1 = library.findPlaylist("name1");
		assertEquals(test1.getList(), new ArrayList<String>(Arrays.asList("s1 (t1, Mary)")));
	}
	
	@Test
	public void testFavPlaylist() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		library.addToAlbumList(quickAlbum1);
		
		library.setFav(s3);
		library.setFav(s2);
		
		Playlist testFav = library.findPlaylist("Favorites");
		assertEquals(testFav.getList().size(), 2);
	}
	
	@Test
	public void testTopRatedPlaylist() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		library.addToAlbumList(quickAlbum1);
		
		library.rateSong(s3, Rate.FIVE);
		library.rateSong(s2, Rate.FOUR);
		
		Playlist testTop = library.findPlaylist("Top Rated");
		assertEquals(testTop.getList().size(), 2);
	}
	
	@Test
	public void testGenrePlaylist() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		Song s4 = new Song("s4", "Mary", quickAlbumInfo1);
		Song s5 = new Song("s5", "Mary", quickAlbumInfo1);
		Song s6 = new Song("s6", "Mary", quickAlbumInfo1);
		Song s7 = new Song("s7", "Mary", quickAlbumInfo1);
		Song s8 = new Song("s8", "Mary", quickAlbumInfo1);
		Song s9 = new Song("s9", "Mary", quickAlbumInfo1);
		Song s10 = new Song("s10", "Mary", quickAlbumInfo1);
		Song[] songs = {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10};
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(songs));
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		library.addToAlbumList(quickAlbum1);
		
		assertTrue(library.containsPlaylist("Pop"));
		String[] quickAlbumInfo2 = {"t1", "Lily", "Pop", "2008"};
		library.addToSongList(new Song("s1", "Lily", quickAlbumInfo2));
		assertEquals(library.findPlaylist("Pop").getList().size(), 11);
	
	}
	
	@Test
	public void testShuffleInPlaylist() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		Song s4 = new Song("s4", "Mary", quickAlbumInfo1);
		Song s5 = new Song("s5", "Mary", quickAlbumInfo1);
		Song s6 = new Song("s6", "Mary", quickAlbumInfo1);
		Song s7 = new Song("s7", "Mary", quickAlbumInfo1);
		Song s8 = new Song("s8", "Mary", quickAlbumInfo1);
		Song s9 = new Song("s9", "Mary", quickAlbumInfo1);
		Song s10 = new Song("s10", "Mary", quickAlbumInfo1);
		Song[] songs = {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10};
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(songs));
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		library.addToAlbumList(quickAlbum1);
		
		Playlist unShuffled = library.findPlaylist("Pop");
		library.shuffleInPlaylist("Pop");
		Playlist shuffled = library.findPlaylist("Pop");
		assertFalse(unShuffled.getList().equals(shuffled.getList()));
	}
	
	@Test
	public void testFindSongByTitle() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t1", "Lily", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s1", "Lily", quickAlbumInfo2);
		Song s3 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s4 = new Song("s3", "Mary", quickAlbumInfo1);
		
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
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t1", "Lily", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s1", "Lily", quickAlbumInfo2);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		Song s4 = new Song("s4", "Mary", quickAlbumInfo1);
		
		library.addToSongList(s1);
		library.addToSongList(s2);
		library.addToSongList(s3);
		library.addToSongList(s4);
		
		ArrayList<Song> test1 = library.findSongByArtist("Mary");
		assertEquals(test1.size(), 3);
	}
	
	@Test
	public void testFindSongByGenre() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t1", "Lily", "Country", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s1", "Lily", quickAlbumInfo2);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		Song s4 = new Song("s4", "Mary", quickAlbumInfo1);
		
		library.addToSongList(s1);
		library.addToSongList(s2);
		library.addToSongList(s3);
		library.addToSongList(s4);
		
		assertEquals(library.findSongsByGenre("Pop").size(), 3);
	}
	@Test
	public void testFindAlbumByTitle() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		Song s5 = new Song("s5", "Jill", quickAlbumInfo2);
		Song s6 = new Song("s6", "Jill", quickAlbumInfo2);
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
	public void findAlbumBySong() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s4 = new Song("s1", "Jill", quickAlbumInfo2);
		Song s5 = new Song("s5", "Jill", quickAlbumInfo2);
		Song s6 = new Song("s6", "Jill", quickAlbumInfo2);
		ArrayList<Song> songlist2 = new ArrayList<Song>(Arrays.asList(s4, s5, s6));
		
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		Album quickAlbum2 = new Album("t2", "Jill", "Pop", 2011, songlist2);
		String[] quickAlbumInfo3 = {"FakeTitle", "Lily", "Pop", "2008"};
		Song s7 = new Song("FakeSong", "Lily", quickAlbumInfo3);
		library.addToAlbumList(quickAlbum1);
		library.addToAlbumList(quickAlbum2);
		
		Album found = library.findAlbumBySong(s1);
		Album nullCheck = library.findAlbumBySong(s7);
		assertEquals(found.toString(), quickAlbum1.toString());
		assertEquals(nullCheck, null);
	}
	
	@Test
	public void testFindAlbumByArtist() {
		LibraryModel library = new LibraryModel();
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		Song s5 = new Song("s5", "Jill", quickAlbumInfo2);
		Song s6 = new Song("s6", "Jill", quickAlbumInfo2);
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
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		String[] quickAlbumInfo2 = {"t2", "Jill", "Pop", "2011"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Jill", quickAlbumInfo2);
		Song s4 = new Song("s4", "Jill", quickAlbumInfo2);
		
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
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		library.addToSongList(s1);
		library.addToSongList(s2);
		
		library.rateSong(s1, Rate.ONE);
		library.rateSong(s2, Rate.FIVE);
		
		ArrayList<Song> test1 = library.findSongByArtist("Mary");
		assertEquals(test1.get(1).getRating(), Rate.ONE);
		assertTrue(test1.get(0).isFav());
	}
	
	@Test
	public void testAlbumToString() {
		String[] quickAlbumInfo1 = {"t1", "Mary", "Pop", "2008"};
		Song s1 = new Song("s1", "Mary", quickAlbumInfo1);
		Song s2 = new Song("s2", "Mary", quickAlbumInfo1);
		Song s3 = new Song("s3", "Mary", quickAlbumInfo1);
		ArrayList<Song> songlist1 = new ArrayList<Song>(Arrays.asList(s1, s2, s3));
		Album quickAlbum1 = new Album("t1", "Mary", "Pop", 2008, songlist1);
		String test1 = "t1_Mary_Pop_2008\ns1\ns2\ns3";
		assertEquals(quickAlbum1.toString(), test1);
	}
}
