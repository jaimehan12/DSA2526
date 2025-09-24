
public class Playlist {

	Track first; //head
	Track current; //song that is currently being played
	//size as a variable...
	public Playlist(Track track) {
		first = track; current = track;
		//
	}
	public boolean addEnd(Track track) {
		if (first == null) {
			first = track; current = track; return true;  
		}
		Track runner = first; //don't loose the list when iterating through it
		while (runner.next!=null) {
			runner = runner.next;
		}
		runner.next = track;
		runner.next.next = null; 
		return true;
	}
	
	public boolean addFirst(Track track) {
		if(track == null) 
			return false;
		track.next= first;
		first = track;
		return true;
	}
	@Override
	public String toString() {
		if (first == null) {
			return "Empty List";
		}
		String output = "";
		Track runner = first;
		while (runner!= null) {
			output+= runner+ "\n";
			runner = runner.next;
			}
		output += "current: " + current;
		return output;
	}

}
