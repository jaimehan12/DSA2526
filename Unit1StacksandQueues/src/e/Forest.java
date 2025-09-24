package e;

public class Forest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Den JBWilliams = new Den();
		for (int i = 0; i < 10; i++) {
			Bear b = new Bear("Bear" + i, Math.random()*1000+100,i);
			System.out.println(b);
			JBWilliams.pushBear(b);
			
		}
		System.out.println(JBWilliams);
		System.out.println(JBWilliams.peekABear());
		System.out.println("Iterating Bears");
		for (Bear b: JBWilliams) {
			System.out.println(b);
		}
		System.out.println("Hunting Bears");
		while(JBWilliams.hasBear()) {
			System.out.println(JBWilliams.huntABear());
		}
	}

}
