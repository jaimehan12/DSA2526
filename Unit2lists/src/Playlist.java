
public class Playlist {

	Track first; // head
	Track current; // song that is currently being played
	// size as a variable...

	public Playlist(Track track) {
		first = track;
		current = track;
		//
	}

	public boolean addEnd(Track track) {
		if (first == null) {
			first = track;
			current = track;
			return true;
		}
		Track runner = first; // don't loose the list when iterating through it
		while (runner.next != null) {
			runner = runner.next;
		}
		runner.next = track;
		runner.next.next = null;
		return true;
	}

	public boolean addFirst(Track track) {
		if (track == null)
			return false;
		track.next = first;
		first = track;
		return true;
	}

	public boolean add(Track newTrack, String prec) {
		if (newTrack == null) //null is super important
			return false;
		Track runner = first;
		Track temp = new Track(prec, 0);
		while (runner!=null && !runner.equals(temp)) {
			runner = runner.next;
		}
		if (runner == null) 
			return false;
		newTrack.next = runner.next;
		runner.next = newTrack;
		return true;
	}
	public boolean add(Track newTrack, int i) {
		if (newTrack == null) 
			return false;
		if (i <= 1) {
			addFirst(newTrack);
		}
		
		Track runner = first;
		while (runner != null && i>2) {
			runner = runner.next;
			i--;
		}
		if (runner == null) //or it could be add last
			return false;
		newTrack.next = runner.next;
		runner.next = newTrack;
		return true;
	}
	

	@Override
	public String toString() {
		if (first == null) {
			return "Empty List";
		}
		String output = "";
		Track runner = first;
		while (runner != null) {
			output += runner + "\n";
			runner = runner.next;
		}
		output += "current: " + current;
		return output;
	}

}
