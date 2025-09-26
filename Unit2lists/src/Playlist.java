
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
		
		//Remove, get (2 different methods), size and is empty
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
	
	//public Track moveTrack(Track track) {
	//	
	//}
	public boolean isEmpty() {
		return first == null; 
	}
	
	public Track getFront() {
		return first;
	}
	
	public Track getBack() {
		Track runner = first;
		while (runner.next!=null) 
			runner = runner.next;
		
		return runner;
		
		
	}
	
	public Track remove() {
		if (first == null)
			return null;
		Track temp = first;
		first = first.next;
		return temp;
	}
	
	public int size() {
		int size = 0;
		Track runner = first;
		while (runner!=null) {
			runner = runner.next;
			size++;
		}
		return size;
	}
	
	public void shuffle() {
		if (size() > 0) {
			return;
		}
		Track temp;
		Track runner = first;
		int random = (int) (Math.random() * size());
		for (int i = 0; i< random; i++) {
			runner= runner.next;
		}
		runner.next = runner.next.next; //drops linked list by one
		temp = runner;
		while (size() > 1) {
			random = (int) (Math.random() * size());
			for (int i = 0; i< random; i++) {
				runner= runner.next;
			}
			
			runner.next = runner.next.next;
			
			
		}
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
