import java.util.ArrayList;

/**
 * Heap class keeps track of a heap and gives it many functions such as adding ,
 * removing and printing a node.
 * 
 * @author jvarg
 *
 */
public class Heap<T extends Comparable<T>> {

	/**
	 * Generic heap class
	 */
	private ArrayList<T> heap;
 
	/**
	 * Heap constructor
	 */
	public Heap() {

		heap = new ArrayList<T>();
	}

	/**
	 * getSize gets the size of the heap
	 * 
	 * @return size value
	 */
	public int getSize() {
		return heap.size();
	}

	/**
	 * isEmpty checks the heap if its empty
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	/**
	 * getPloc gets parent location
	 * 
	 * @param i
	 *            passes in integer value
	 * @return location value
	 */
	public int getPLoc(int i) {
		return (i - 1) / 2;
	}

	/**
	 * getCloc gets left child location
	 * 
	 * @param i
	 *            passes in integer value
	 * @return location
	 */
	public int getLCLoc(int i) {
		return 2 * i + 1;
	}

	/**
	 * getCloc gets right child location
	 * 
	 * @param i integer value
	 *           
	 * @return location value
	 */
	public int getRCLoc(int i) {
		return 2 * i + 2;
	}

	/**
	 * getNode gets the node at that loation
	 * 
	 * @param i integer value
	 *           
	 * @return the node
	 */
	public T getNodeAt(int i) {

		if (heap.get(i) == null) {
			System.out.println("Item does not exist");
			return null;
		} else {
			return heap.get(i);
		}
	}

	/**
	 * addNode adds a node to the heap
	 * 
	 * @param n gengeric object is passed in
	 *          
	 */
	public void addNode(T n) {
		heap.add(null);
		int index = heap.size() - 1;

		while (index > 0 && getNodeAt(getPLoc(index)).compareTo(n) > 0) {
			heap.set(index, getNodeAt(getPLoc(index)));
			index = getPLoc(index);
		}
		heap.set(index, n);
	}

	/**
	 * Removes a node in the heap class
	 * 
	 * @return min the min value
	 */
	public T removeMin() {

		T min = heap.get(0);
		int index = heap.size() - 1;
		T last = heap.remove(index);
		if (index > 0) {
			heap.set(0, last);
			T root = heap.get(0);
			int end = heap.size() - 1;
			index = 0;
			boolean done = false;
			while (!done) {
				if (getLCLoc(index) <= end) {// left exist
					T child = getNodeAt(getLCLoc(index));
					int childLoc = getLCLoc(index);
					if (getRCLoc(index) <= end) {// rt exist

						if (getNodeAt(getRCLoc(index)).compareTo(child) < 0) {
							child = getNodeAt(getRCLoc(index));

							childLoc = getRCLoc(index);
						}

					}
					if (child.compareTo(root) < 0) {
						heap.set(index, child);
						index = childLoc;
					} else {
						done = true;
					}

				} else {// no children
					done = true;
				}

			
			}

			heap.set(index, root);
		}
		return min;
	}

	/**
	 * printHeap prints the heap
	 * 
	 */
	public void PrintHeap() {
		int counter = 0;

		for (int i = 0; i < heap.size(); i++) {
			counter++;
			System.out.println(counter + "   " + heap.get(i) + " ");

		}
		System.out.println();
	}

}
