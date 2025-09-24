import java.util.LinkedList;

public class Link {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> spies = new LinkedList<>();
		spies.add("James Bond");
		spies.add("Ethan Hunt");
		spies.add("Agent X");
		System.out.println(spies);
		System.out.println(spies.remove());
		System.out.println(spies.peek()); //head of the list
		//there is also peekLast()
		Playlist pop = new Playlist(new Track("Manchild", 130));
		System.out.println(pop);
		pop.addEnd(new Track("Love Story", 250));
		System.out.println(pop);
		pop.addFirst(new Track("Dancing Queen", 200));
		System.out.println(pop);
		pop.add(new Track("Sorry", 180), "Manchild");
		System.out.println(pop);
		pop.add(new Track("I am the Walrus", 180), "Love Story");
		System.out.println(pop);
		pop.add(new Track("Good News", 190), 6);
		System.out.println(pop);
	}

}
