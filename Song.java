package model;

public class Song {
	/* INSTANCE VARIABLES */
	private final String title;
	private final String artist;
	private final String[] albumName;
	private boolean favorite;
	private Rate rating;

	/* ENUM FOR RATE */
	public enum Rate {
		ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

		private int value;

		Rate(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/* CONSTRUCTORS */
	public Song(String title, String artist, String[] albumName) {
		this.title = title;
		this.artist = artist;
		this.favorite = false;
		this.albumName = albumName;
		this.rating = null;
	}

	/* METHODS */

	/* SETTERS */
	public void setRating(Rate rate) {
		this.rating = rate;
	}

	public void setFav(boolean fav) {
		this.favorite = fav;
	}

	/* GETTERS */
	public String getTitle() {
		return this.title;
	}

	public String getArtist() {
		return this.artist;
	}

	public String[] getAlbum() {
		return this.albumName;
	}

	public boolean isFav() {
		return this.favorite;
	}

	public Rate getRating() {
		return this.rating;
	}

	// To String method
	public String toString() {
		return this.title + " (" + this.albumName[0] + ", " + this.artist + ")";
	}

}
