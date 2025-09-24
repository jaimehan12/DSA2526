package e;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StandInLine {
	public static void main(String[] args) {
		Queue<String> TheHulk = new LinkedList<String>();
		TheHulk.offer("Sarge");
		TheHulk.offer("Ak");
		TheHulk.offer("Ali");
		for (String s: TheHulk) {
			System.out.println(s);
		}
		System.out.println(TheHulk.peek());
		/*Removes people from the line
		 * for(int i = 0; i < 5; i++) 
			System.out.println(TheHulk.poll());
			//Crash System.out.println(TheHulk.remove());
		 */
		System.out.println(TheHulk);
		int size = TheHulk.size();
		for (int i = 0; i < size; i++) {
			//String temp = TheHulk.poll();
			TheHulk.offer(TheHulk.peek());
			TheHulk.offer(TheHulk.poll());
		}
		System.out.println(TheHulk);
		
		Stack<String> aux = new Stack<String>();
		for (String s: TheHulk) {
			aux.push(s);
		}
		while(!aux.isEmpty()) {
			TheHulk.add(aux.pop());
		}
		System.out.println(TheHulk);
	}
}










































