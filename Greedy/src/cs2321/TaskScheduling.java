package cs2321;

/**
 * @author: Sam Breuer
 *
 */

public class TaskScheduling {
	/**
	 * Goal: Perform all the tasks using a minimum number of machines. 
	 * 
	 *       
	 * @param tasks tasks[i][0] is start time for task i
	 *              tasks[i][1] is end time for task i
	 * @return The minimum number or machines
	 */
   public static int NumOfMachines(int[][] tasks) {
	   
	   //load data into heap and find largest finish time for array sizes
		HeapPQ<Integer,Integer> heap =new HeapPQ<Integer,Integer>();
		int lastEnd = 0;
		for (int[] i : tasks) {
			heap.insert(i[0], i[1]);
			if(lastEnd< i[1]) {
				lastEnd = i[1];
			}
		}
		
		//create schedule arraylist and first schedule array
		ArrayList<Integer[]> machines = new ArrayList<Integer[]>();
		machines.addFirst(new Integer[lastEnd]);
		
		//main algorithm loop
		while (!heap.isEmpty()) {
			Boolean inserted = false;
			
			//check all schedules for a place to fit the task
			for(Integer[] arr: machines) {
				
				//if the task fits
				if(arr[heap.min().getKey()-1]==null) {
					//put a one in all places where the task fits
					for(int i = heap.min().getKey(); i < heap.min().getValue(); i++) {
						arr[i-1]=1;
					}
					//remove element just added
					heap.removeMin();
					inserted = true;
					break;
				}
			}
			
			//if task was not added to a schedule, create new schedule and add task to it
			if(inserted == false) {
				machines.addFirst(new Integer[lastEnd]);
				
				//remove new schedule, put a one in all places where the task fits
				Integer[] temp = machines.removeFirst();
				for(int i = heap.min().getKey(); i < heap.min().getValue(); i++) {
					temp[i-1]=1;
				}
				//put updated schedule back in
				machines.addFirst(temp);
				//remove element just added
				heap.removeMin();
			}
		}
		
		
	  return machines.size();
   }
}
