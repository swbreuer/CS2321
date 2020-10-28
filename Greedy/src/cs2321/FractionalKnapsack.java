package cs2321;

/**
 * @author: Sam Breuer
 *
 */
public class FractionalKnapsack {

   
	/**
	 * Goal: Choose items with maximum total benefit but with weight at most W.
	 *       You are allowed to take fractional amounts from items.
	 *       
	 * @param items items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
	 * @param knapsackWeight
	 * @return The maximum total benefit. Please use double type operation. For example 5/2 = 2.5
	 * 		 
	 */
	public static double MaximumValue(int[][] items, int knapsackWeight) {
		MaxComparator<Double> comp = new MaxComparator<Double>();
		HeapPQ<Double,Integer> heap =new HeapPQ<Double,Integer>(comp);
		
		//add all elements to the heap with value per weight as key, weight as value
		for (int[] i : items) {
			heap.insert(((double)i[1])/((double)i[0]), i[0]);
		}
		
		int weightleft = knapsackWeight;
		double totvalue = 0;
		
		while(weightleft>0 & !heap.isEmpty()) {
			
			//get weight and value for next element
			int weight = heap.min().getValue();
			double value = heap.removeMin().getKey();
			
			//if the weight is greater than the weight left, use full weight left and return
			if( weightleft<weight) {
				totvalue = totvalue + weightleft*value;
				return totvalue;
			}
			
			//if the weight is not greater than the weight left, use difference
			else {
				weightleft = weightleft - weight;
				totvalue = totvalue + weight*value;
			}
			
		}
		return totvalue;
	}
}
