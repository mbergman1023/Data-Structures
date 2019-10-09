package rockcountdown;

public class Song {
	int rank;
	String title;
	String artist;


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public Song(int rank, String title, String artist) {
		super();
		this.rank = rank;
		this.title = title;
		this.artist = artist;

	}


	public Song(String str) {
		super();
		String[] strs = str.split("\t", 0);

		rank = Integer.parseInt(strs[0].trim());
		title = strs[1].trim();
		artist = strs[2].trim();

		// TODO Auto-generated constructor stub
	}

}