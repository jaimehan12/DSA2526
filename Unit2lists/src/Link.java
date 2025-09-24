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
		
	}

}
