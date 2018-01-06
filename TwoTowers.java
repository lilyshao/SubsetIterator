/* Problem: given n uniquely sized cubic blocks and that each block has a face area between 1
and n. If we build two towers by stacking these blocks, how close can we get their heights?
Solve using iterators & bits
*/
import java.util.ArrayList;

public class TwoTowers {
	public static void main(String args[]) {
		int n = 20; // n should be moderately small, as # of subsets is 2^n
		ArrayList<Double> heights = new ArrayList<Double>();
		// inserts the values sqrt(1) to sqrt(n) into heights ArrayList
		for (int k=1; k<=n; k++) {
			heights.add( Math.sqrt(k) );
		}

		// use a SubsetIterator to construct the 2^n subsets of heights
		SubsetIterator si = new SubsetIterator(heights);
		Double target = getSum(heights)/2;
		ArrayList<Double> subset1 = new ArrayList<Double>();
		Double minDiff = target;
		// The values of each subset are summed,
		// and the sum that comes closest to, but does not exceed, the value h/2 is remembered.
		while (si.hasNext()) {
			ArrayList<Double> nextSubset = si.next();
			Double diff = target - getSum( nextSubset );
			if ( diff>=0 && diff<minDiff ) {
				minDiff = diff;
				subset1 = nextSubset;
			}
		}

		// the other stack/subset:
		ArrayList<Double> subset2 = new ArrayList<Double>();
		for (Double x: heights) {
			if (!subset1.contains(x)) {
				subset2.add(x);
			}
		}

		// After all the subsets have been considered, print the best solution
		System.out.println("The two subsets (in heights) are: ");
		Double sum1 = getSum(subset1);
		Double sum2 = getSum(subset2);
		System.out.println(subset1);
		System.out.println(subset2);
		System.out.println("Their sums are: " + sum1 + ", " + sum2);
		System.out.println("The difference in their heights is: " + (sum2-sum1));

		// print out area instead of heights(sqrts) for easier reading
		ArrayList<Integer> subset1Area = new ArrayList<Integer>();
		ArrayList<Integer> subset2Area = new ArrayList<Integer>();
		for (Double x: subset1) {
			subset1Area.add((int) Math.rint(x*x));
		}
		for (Double y: subset2) {
			subset2Area.add((int) Math.rint(y*y));
		}
		System.out.println("The two subsets (in area) are: ");
		System.out.println(subset1Area);
		System.out.println(subset2Area);
	}

	// return the sume of a ArrayList of doubles
	public static Double getSum(ArrayList<Double> v) {
		Double sum = 0.0;
		for (Double x: v) {
			sum+=x;
		}
		return sum;
	}

}
