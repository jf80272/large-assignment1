package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import database.UserData;

public class TestUserData {

    private UserData userData;
    private User testUser;
    private String testUsername = "JohnDoe1234";
    private String testPassword = "test!P@ssw0rd";
    private String testSalt;
    private String testHashedPassword;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        userData = new UserData();
        testUser = new User("JohnDoe1234", "test!P@ssw0rd");
        
        byte[] saltBytes = new byte[]{1, 2, 3, 4, 5};
        testSalt = Base64.getEncoder().encodeToString(saltBytes);
        testHashedPassword = Base64.getEncoder().encodeToString(hashPassword(testPassword, saltBytes));

        File tempFile = tempDir.resolve("users.txt").toFile();
        tempFile.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(testUsername + "," + testSalt + "," + testHashedPassword);
        }
    }

    @Test
    void testAddUserData() {
        userData.addUserData(testUsername, testUser);
        assertEquals(testUser, userData.getUser(testUsername, testPassword));
    }

    @Test
    void testGetUserValidCredentials() {
        userData.addUserData(testUsername, testUser);
        User retrievedUser = userData.getUser(testUsername, testPassword);
        assertNotNull(retrievedUser);
        assertEquals(testUser, retrievedUser);
    }

    @Test
    void testGetUserInvalidCredentials() {
        userData.addUserData(testUsername, testUser);
        User retrievedUser = userData.getUser(testUsername, "wrongPassword");
        assertNull(retrievedUser);
    }

    @Test
    void testGetUserNonexistentUser() {
        User retrievedUser = userData.getUser("nonexistentUser", "somePassword");
        assertNull(retrievedUser);
    }

    private byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            return md.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
