
public class Track {
	String title;
	String artist;
	int duration; 
	Track next; //important for link list
	public Track(String title, String artist, int duration, Track next) {
		super();
		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.next = next;
	}
	public Track(String title, int duration) {
		this(title, "Artist", duration, null);
	}
	
	@Override
	public String toString() {
		return "Track [title=" + title + ", artist=" + artist + ", duration=" + duration + "]";
	}

}
