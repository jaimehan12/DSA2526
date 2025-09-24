package e;

import java.util.Arrays;
import java.util.Iterator;

public class Den implements Iterable<Bear> {
	Bear[] bears;
	int count;
	double totalWeight;
	
	public Den() {
		bears= new Bear[3]; 
		
	}
	
	
	@Override
	public String toString() {
		return "Den [bears=" + Arrays.toString(bears) + ", count=" + count + ", totalWeight=" + totalWeight + "]";
	}


	/**
	 * 
	 * @param b bear to add
	 * @return bear that was potentially added
	 */
	//Pushing a bear
	public Bear pushBear(Bear b) {
		//We have to check if capacity isn't reached
		if (count > 0 && bears[count-1].weight < b.weight) {
			return null;
		} //Return null if unccessful;
		//we have to check if the last bear is heavier
		totalWeight+=b.weight;
		if(count < bears.length) {
				bears[count++] = b; //Size
				return b;
		} //New Temp array if previous 
		Bear[] temp = new Bear[bears.length*2];
		for (int i = 0; i < bears.length; i++) {
			temp[i] = bears[i];
			
		}
		temp[count++] = b;
		bears = temp;
		return b;
		 
		//return null if not sucessful
	}
	
	public Bear peekABear() {
		
		//turnary opperator--> condition,"?", value if true,":", value if false
		return count == 0?null:bears[count-1];
		//if (count ==0) {
		//	return null;
		//}
		//return bears[count-1];
	}
	
	public Bear huntABear() {
		if (count > 0) {
			Bear temp = bears[count-1];
			bears[--count] = null;
			//if (1.0*count/bears.length<.1) {
			///	trim();
			//}
			totalWeight -=temp.weight;
			return temp;
		}
		return null;
		
	
	}
	
	
	public boolean hasBear() {
		return count>0;
	}
	//itterable
	//remove
	//get
	//size is public
	//isEmpty
	
	
	@Override
	public Iterator<Bear> iterator() {
		// TODO Auto-generated method stub
		return new BearIterator();
	}
	
	//To be iterable you need an iterator
	private class BearIterator implements Iterator<Bear>{
		int fingy = count-1;
		@Override
		public boolean hasNext() {
			return fingy>=0;
		}

		@Override
		public Bear next() { 
			// TODO Auto-generated method stub
			return !hasNext()?null:bears[fingy--];
			//if(!hasNext()) {
			//	return null;
			//}
			//return bears[fingy--];
		}
		
	}
}
