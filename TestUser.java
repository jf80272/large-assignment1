package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import database.MusicStore;
import model.LibraryModel;
import model.Song;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class TestUser {
    private User user;
    private LibraryModel library;
    private Song song1;
    private Song song2;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        // Create a temporary file for user storage
        File tempFile = tempDir.resolve("users.txt").toFile();
        tempFile.createNewFile();

        MusicStore ms = new MusicStore("albums/albums.txt");
        song1 = ms.findSongByTitle("Banjo").get(0);
        song2 = ms.findSongByTitle("El Borracho").get(0);
        user = new User("testUser", "testPassword");
    }

    @Test
    void testPlaySong() {
        user.getLibrary().addToSongList(song1);
        user.getLibrary().addToSongList(song2);
        user.updateSongPlays();
        
        assertTrue(user.playSong("Banjo", "Leonard Cohen"));
        assertFalse(user.playSong("Nonexistent Song", "Unknown Artist"));
    }

    @Test
    void testRecentPlays() {
        user.getLibrary().addToSongList(song1);
        user.updateSongPlays();
        user.playSong("Banjo", "Leonard Cohen");
        user.updateSongPlays();
        Song[] recent = user.getRecentPlays();
        
        assertNotNull(recent[0]);
        assertEquals("Banjo", recent[0].getTitle());
    }

    @Test
    void testMostPlays() {
        user.getLibrary().addToSongList(song1);
        user.updateSongPlays();
        user.playSong("Banjo", "Leonard Cohen");
        user.playSong("Banjo", "Leonard Cohen");
        user.playSong("El Borracho", "Mana");
        
        Song[] mostPlays = user.getMostPlays();
        assertNotNull(mostPlays[0]);
        assertEquals("Banjo", mostPlays[0].getTitle());
    }
}
