
package e;

import java.util.Iterator;
import java.util.Stack;

public class Stacked {

	public static void main(String[] args) {
		//Stack cats = new Stack<Integer>();
		Stack<Integer> hats = new Stack<Integer>();
		//Add Element to Stack
		hats.push(10);
		hats.push(8);
		hats.push(6);
		//You can only see the first one
		System.out.println(hats.peek());
		//Includes toString
		System.out.println("Original" + hats); 
		//To keep the original stack & look at all items in stack
		Stack temp = new Stack<Integer>();
		while(!hats.isEmpty()) {
			System.out.println(hats.peek());
			temp.push(hats.pop());
		}
		//Put elements back in stack
		while (!temp.isEmpty()) {
			hats.push((Integer)temp.pop());
		}
		System.out.println("After Looking" + hats); 
		hats.push(8); 
		hats.push(11);
		System.out.println(isPalindrome(hats));
		System.out.println("After Palindrome" + hats); 
		//hats.push(10); 
		//hats.push(10);
		//System.out.println(isPalindrome(hats));
		//System.out.println("After Palindrome" + hats); 
		
		//Easier way to iterate through all of the elements
		
		System.out.println("________________________");
		for (Integer elem:hats) {
			System.out.println(elem);
		}
		System.out.println("________________________");
		//Iterator, March forward to find items
		Iterator<Integer> it = hats.iterator();
		//System.out.println(it.next());
		//it.next();
		//System.out.println(hats.peek());
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		
		System.out.println(isExpressionValid("[(3+3)/{[5-4]}+4"));
		
	}
	/**
	 * Check if string of brackets has brackets that line up correctly
	 * @param expression
	 * @return
	 */
	public static boolean isExpressionValid(String expression) {
		String brackets = stripBrackets(expression);
		String open = "[{(";
		String closed = "]})";
		Stack<Character> stack = new Stack<Character>();
		for (char c: brackets.toCharArray()) {
			if (open.indexOf(c) > -1) {
				stack.push(c);
			}
			else {
				if (stack.isEmpty() || open.indexOf(stack.pop()) != closed.indexOf(c)) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	public static String stripBrackets(String expression) {
		String brackets = "(){}[]";
		String output = "";
		for(char c: expression.toCharArray()) {
			if (brackets.indexOf(c) > -1) {
				output+=c;
			}
		}
		
		return output;
	}
	public static boolean isPalindrome(Stack<Integer> st) {
		//Temp
		Stack <Integer>temp = new Stack<Integer>();
		//Keep Original Stack
		st = (Stack<Integer>)st.clone();
		//Put half of integers in other Stack
		while(st.size()-temp.size() >1) {
				temp.push(st.pop());
			}
		Integer middle;
		if (st.size()-temp.size()==1)
			middle = st.pop();
		while(!st.isEmpty()) {
			if (!st.pop().equals(temp.pop())) {
				return false;
			}
		}
		return true;
}
}


