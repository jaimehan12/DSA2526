package e;

import java.util.Stack;

public class Postfix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String postfix1 = "43*5+"; // 17
		String postfix2 = "64-2^3*5+63-/"; // 5.6666666
		
		System.out.println(evaluate(postfix1));
		System.out.println(evaluate(postfix2));
		System.out.println(convertPostfix("5+4*3"));
		System.out.println(convertPostfix("(3*4+(5+6*7))/(8-9)"));
		CustomDeque<Bear> dequebears = new CustomDeque(20);
		dequebears.addFront(new Bear("Smokey", 10, 1));
	}
	
	public static int prec(char opperator) {
		switch(opperator) {
		case '+', '-': return 0;
		case '*','/': return 1;
		case '^': return 2;
		default: return 3;
		}
	}

	public static double evaluate(String post) {

		Stack<Double> stk = new Stack<Double>();
		for (char c : post.toCharArray()) {
			if (Character.isDigit(c)) {
				stk.push((double) Character.getNumericValue(c));
			} else {
				double right = stk.pop();
				double left = stk.pop();
				switch (c) {
				case '+':
					stk.push(left + right);
					break;
				case '-':
					stk.push(left - right);
					break;
				case '*':
					stk.push(left * right);
					break;
				case '/':
					stk.push(left / right);
					break;
				case '^':
					stk.push(Math.pow(left, right));
					break;
				default:
					return Double.POSITIVE_INFINITY;
				}

			}

		}
		return stk.pop();

	}
	
	public static String convertPostfix(String infix) {
		String post = "";
		Stack<Character> stk = new Stack<Character>();
		for (char c:infix.toCharArray()) {
			if (c == '(') {
				stk.push(c);
			}
			else if(Character.isDigit(c)) {
				post+=c;
			}
			else if(c == ')') {
				
				while(!stk.isEmpty()) {
					if (stk.peek() == '(') {
						stk.pop(); break;
					} else {
					post+=stk.pop();
					}
				}
				
			}
			
			else {
				while (!stk.isEmpty() && prec(stk.peek()) >= prec(c)) {
					post+=stk.pop();
				}
				stk.push(c);
			}
		}
		while(!stk.isEmpty()) {
			post += stk.pop();
		}
		return post;
	}

}
