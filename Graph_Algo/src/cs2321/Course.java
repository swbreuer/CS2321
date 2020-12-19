package cs2321;

import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Vertex;

/**
 * @author Ruihong Zhang
 * Reference: Text book: R14.17 on page 678
 *
 */
public class Course {

	AdjListGraph<String,Boolean> graph = new AdjListGraph<String,Boolean>(true);
	/**
	 * @param courses: An array of course information. Each element in the array is also array:
	 * 				starts with the course name, followed by a list (0 or more) of prerequisite course names.
	 * 
	 */
	public Course(String courses[][]) {
		for(String[] list : courses) {
			Vertex<String> newvert = graph.insertVertex(list[0]);
			for(String course : list) {
				if(course == newvert.getElement()) {
					continue;
				}
				Vertex<String> vert = graph.vertMap().get(course);
				graph.insertEdge(vert, newvert, true);
			}
		}
		
	}
	
	/**
	 * @param course
	 * @return find the earliest semester that the given course could be taken by a students after taking all the prerequisites. 
	 */
	public int whichSemester(String course) {
		int count = 1;
		int tempcount = 0;
		int comp = 0;
		Vertex<String> search = graph.vertMap().get(course);
		for(Edge<Boolean> e : graph.incomingEdges(search)) {
			comp = whichSemester(graph.opposite(search, e),count);
			if(comp > tempcount) {
				tempcount = comp;
			}
		}
		if(tempcount > count) {
			count = tempcount;
		}
		
		return count;
	}
	
	public int whichSemester(Vertex<String> course, int count) {
		int tempcount = 0;
		int comp = 0;
		for(Edge<Boolean> e : graph.incomingEdges(course)) {
			comp = whichSemester(graph.opposite(course, e),count);
			if(comp > tempcount) {
				tempcount = comp;
			}
		}
		if(tempcount > count) {
			count = tempcount;
		}
		count++;
		return count;
	}
			
}
