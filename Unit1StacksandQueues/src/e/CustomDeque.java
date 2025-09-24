package e;
//generic type element
public class CustomDeque<T> {
	T[] container;
	int front, back;

	public CustomDeque(int capacity) {
		container = (T[]) new Object[capacity];
		front = -1;
		back = -1;
	}

	public T addFront(T thing) {
		if (front == -1) {
			front = container.length / 2;
			back = container.length / 2;
			return thing;
		}
		container[--front] = thing;
		if (front == 0) {
			// move them
		}
		return thing;

	}
}
