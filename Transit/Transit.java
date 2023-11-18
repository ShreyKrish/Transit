import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 * @return The zero node in the train layer of the final layered linked list
	 */
	public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {
		// WRITE YOUR CODE HERE	
	/*
	TNode thead = new TNode();
	thead.location = 0;
	TNode trptr = thead;

	TNode bhead = new TNode();
	bhead.location = 0;
	TNode buptr = thead;

	TNode whead = new TNode();
	whead.location = 0;
	TNode waptr = thead;

	
	*/
	TNode thead = new TNode();
	TNode trptr = thead;
	TNode c = new TNode(trainStations[0]);
	trptr.next = c;

	TNode bhead = new TNode();
	TNode buptr = thead;
	TNode b = new TNode(busStops[0]);
	buptr.next = b;

	TNode whead = new TNode();
	TNode waptr = thead;
	TNode a = new TNode(locations[0]);
	waptr.next = a;

	thead.down = bhead;
	bhead.down = whead;

	trptr = thead;
	for(int i = 0; i < trainStations.length; i++){
		TNode temp = new TNode (trainStations[i]);
		trptr.next = temp;
		trptr = trptr.next;
	}
	buptr = bhead;
	for(int i = 0; i < busStops.length; i++){
		TNode temp = new TNode (busStops[i]);
		buptr.next = temp;
		buptr = buptr.next;
	}
	waptr = whead;
	for(int i = 0; i < locations.length; i++){
		TNode temp = new TNode (locations[i]);
		waptr.next = temp;
		waptr = waptr.next;
	}
	trptr = thead;
	while(trptr != null){
		if(trptr.location == buptr.location){
			trptr.down = buptr;
		}
		trptr = trptr.next;
	}

	buptr = bhead;
	waptr = whead;
	while(buptr != null){
		
		if(buptr.location == waptr.location){
			buptr.down = waptr;
		}
		buptr = buptr.next;
	}
	return thead;
	}
	
	/**
	 * Modifies the given layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param station The location of the train station to remove
	 */
	public static void removeTrainStation(TNode trainZero, int station) {
		// WRITE YOUR CODE HERE
		StdOut.print("Enter a station to remove =>");
		TNode thead = trainZero;
		TNode ptr = thead.next;
		TNode prev = thead;
		while(ptr!= null){
			if(ptr.location == station){
				prev.next = ptr.next;
				break;
		}else{
			prev = ptr;
			ptr = ptr.next;
		}
			
		}
	
	}

	/**
	 * Modifies the given layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param busStop The location of the bus stop to add
	 */
	public static void addBusStop(TNode trainZero, int busStop) {
		// WRITE YOUR CODE HERE
		TNode thead =  trainZero;
		thead.location = 0;

		TNode bhead = trainZero.down;
		bhead.location = 0;
		TNode buptr = bhead;
		
		TNode whead = trainZero.down.down;
		whead.location = 0;
		TNode waptr = whead;

		TNode n = new TNode(busStop);
		
		while(buptr.location < busStop){
			buptr = buptr.next;
			if(buptr.next.location> busStop){
				n.next = buptr.next;
				buptr.next = n;
				break;
			}
			//iterate through walking liked list
			

		}
		while(waptr.location <= busStop){
			waptr = waptr.next;
			if(waptr.location == busStop){
				n.down = waptr;
				break;
			}
		}
		





		
		
	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param destination An int representing the destination
	 * @return
	 */
	public static ArrayList<TNode> bestPath(TNode trainZero, int destination){
		// WRITE YOUR CODE HERE
		ArrayList<TNode> list = new ArrayList<>();

		TNode ptr = trainZero;
		ptr.location = 0;
		while(ptr.next != null ){
			if(ptr.down != null){
			if(ptr.location >= destination || ptr.next == null){
				list.add(ptr);
				ptr = ptr.down;
			}
			if(ptr.next.location <= destination){
				list.add(ptr);
				ptr = ptr.next;
			}
			if(ptr.next.location>destination && ptr.down != null){
				list.add(ptr);
				ptr=ptr.down;
			}
			if(ptr.down == null && ptr.location == destination){
				list.add(ptr);
				break;
			}}
		
		}return list;
}
/*while(ptr.next.location <= destination){
			list.add(ptr);
			ptr = ptr.next;
		}
		while(ptr.next.location>destination || ptr.next == null && ptr.down != null){
			list.add(ptr);
			ptr=ptr.down;
		}
		if(ptr.location == destination){
			while(ptr.down != null){
				list.add(ptr);
				ptr = ptr.down;
			}
		}*/
		/*while(ptr.next != null){
			
			if(ptr.location == destination){
				while(ptr.down != null){
					list.add(ptr);
					ptr = ptr.down;
					System.out.println("down");
				}
				list.add(ptr);
				
			} 
			else if(ptr.next == null){
				list.add(ptr);
				ptr = ptr.down;
			} else if (ptr.location < destination && ptr.next.location > destination){
				list.add(ptr);
				ptr = ptr.down;
				System.out.println("in");
			}
			 else if (ptr.next.location < destination){
				list.add(ptr);
				ptr = ptr.next;
			}else if( ptr.next.location == destination){
				list.add(ptr);
				ptr = ptr.next; 

			
		}*/
//can i change the return statement?
		

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @return
	 */
	public static TNode duplicate(TNode trainZero) {
		// WRITE YOUR CODE HERE
		TNode thead =  trainZero;
		thead.location = 0;
		TNode trptr = thead;

		TNode bhead = trainZero.down;
		bhead.location = 0;
		TNode buptr = bhead;
		
		TNode whead = trainZero.down.down;
		whead.location = 0;
		TNode waptr = whead;

		thead.down = bhead;
		bhead.down = whead;

		while(trptr.next != null){
			trptr = new TNode();
			trptr = trptr.next;
		}
		while(buptr.next != null){
			buptr = new TNode();
			buptr = buptr.next;
		}
		while(waptr.next != null){
			waptr = new TNode();
			waptr = waptr.next;
		}

		return thead;
	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public static void addScooter(TNode trainZero, int[] scooterStops) {
		// WRITE YOUR CODE HERE
		TNode thead = trainZero;
		thead.location = 0;
		TNode trptr = thead;

		TNode bhead = trainZero.down;
		bhead.location = 0;
		TNode buptr = bhead;
		
		TNode whead = trainZero.down.down;
		whead.location = 0;
		TNode waptr = whead;

		TNode shead = new TNode();
		bhead.location = 0;
		TNode suptr = shead;

		thead.down = bhead;
		bhead.down = shead;

		if(shead.down == null){
			shead.down = whead;
		}
		while(trptr.next != null){
			trptr = trptr.next;
		}
		while(buptr.next != null){
			buptr = buptr.next;
		}
		while(waptr.next != null){
			waptr = waptr.next;
		}
		suptr = shead;
		for(int i =0; i < scooterStops.length; i++){
			TNode temp = new TNode(scooterStops[i]);
			suptr.next = temp;
			suptr = suptr.next;
		}
		//bus to scooter down ptr
		suptr = shead;
		buptr = bhead;
		while(buptr != null){
			if(buptr.location == suptr.location){
				buptr.down = suptr;
				buptr = buptr.next;
			}
		}
		
		//Scooter  to walking down pointer
		suptr = shead;
		waptr = whead;
		while(suptr != null){
			if(suptr.location == waptr.location){
				suptr.down = waptr;
				suptr = suptr.next;
			}
			waptr = waptr.next;
		}

	}
}