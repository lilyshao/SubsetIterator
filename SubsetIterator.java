// name: Lily Shao
// returns subsets of a list
import java.util.ArrayList;
import java.util.Iterator;

public class SubsetIterator<E> {

	// represent the current subset; This value increases from 0 (the empty set)
	// to 2^n-1 (the entire set of values) as the Iterator progresses.
	Long subset;
	ArrayList<E> theList;

	public SubsetIterator( ArrayList<E> l ) {
		theList = l;
		reset();
	}

	public void reset() {
		subset = 0L; // resets the subset counter to 0
	}

	// returns true if the current value is a reasonable representation of a subset
	public boolean hasNext() {
		// subset <= 2^n - 1
		int n = theList.size();
		return subset <= (1L << n) - 1;
	}

	// returns a new ArrayList<E> of values that are part of the current subset.
	public ArrayList<E> get() {
		// If bit i of the current counter is 1, element i of the original ArrayList
		// is included in the resulting subset ArrayList.
		ArrayList<E> subsetList = new ArrayList<E>();
		for (int i = 0; i<theList.size(); i++) {
			// To retrieve bit i of a long integer m, we need only compute m & (1L << i).
			if ((subset & (1L << i))!=0) {
				subsetList.add(theList.get(i));
			}
		}
		return subsetList;
	}

	public ArrayList<E> next() {
		ArrayList<E> result = get();
		subset++;
		return result;
	}

	public static void main(String args[]) {
		ArrayList<Integer> v = new ArrayList<Integer>();
		v.add(1);
		v.add(2);
		v.add(3);
		v.add(4);
		v.add(5);
		v.add(6);
		v.add(7);
		v.add(8);
		System.out.println("the original list is: " + v);

		SubsetIterator si = new SubsetIterator(v);
		int count = 0;

		System.out.println("the subsets are: ");
		while (si.hasNext()) {
			System.out.println(si.next());
			count ++;
		}
		System.out.println("there are this many subsets: " + count); // should be 256
	}
}
