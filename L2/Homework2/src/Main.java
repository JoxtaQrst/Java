import java.awt.geom.Point2D;
import java.util.*;

public class Main {

    /**
     * Main method for running the program and testing the solution
     *
     * @param args arguments passed to the program
     *
     */
    public static void main(String[] args) {
        //we add 2 cities
        City l1 = new City("Iasi",1,2,5000);
        Express r1=new Express("E28", 10,90);
        City l2 = new City("Roman",2,6,1000);
        r1.setRoad(l1,l2);

        System.out.println(l1);
        System.out.println(l2);

        //we initialize a vector list with Roads and Locations
        List<Road> roads = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        locations.add(l1);
        locations.add(l2);
        roads.add(r1);
        if(validInstance(locations,roads)) // we verify that the instance of the problem is valid
        {
            System.out.println("Valid");

        }
        else System.out.println("Not valid");

        System.out.println(possibleRoute(l1,l2,roads)); // we see if there exists a possible route between 2 cities
    }

    /**
     * Function that creates a graph and uses BFS in order to determine is there can exist a possible route
     * (see <a href="https://www.w3schools.com/java/java_hashmap.asp">HashMap</a> ,
     * <a href="https://www.geeksforgeeks.org/queue-interface-java/">Queue</a> ,
     * <a href="https://www.w3schools.com/java/java_hashset.asp">HashSet</a> ,
     * <a href="https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/">BFS Algorithm in java</a> for generating a BFS algorithm)
     *
     * @param A the starting location
     * @param B the final destination
     * @param roads a list of roads
     * @return TRUE if there is a possible route between 2 given locations, FALSE otherwise
     */
    public static boolean possibleRoute(Location A,Location B, List<Road> roads) // functions that creates a graph and uses BFS algorithm in order to solve the problem
    {
        //create the graph [ matrice adiacenta ]
        Map<Location,List<Location>> matrix = new HashMap<>();
        for(Road road : roads)
        {
            if (!matrix.containsKey(road.getPointA()))
            {
                matrix.put(road.getPointA(), new ArrayList<>());
            }
            if (!matrix.containsKey(road.getPointB()))
            {
                matrix.put(road.getPointB(), new ArrayList<>());
            }
             matrix.get(road.getPointA()).add(road.getPointB());
             matrix.get(road.getPointB()).add(road.getPointA());
        }
        //we do the BFS
        Queue<Location>queue = new LinkedList<>();//we initialize a que for the nodes
        Set<Location>viz = new HashSet<>();
        queue.offer(A);
        viz.add(A);
        while(!queue.isEmpty())
        {
            Location current = queue.poll();
            if(current.equals(B))
            {
                return true;
            }
            for(Location l : matrix.getOrDefault(current,new ArrayList<>()))
            {
                if(!viz.contains(l))
                {
                    queue.offer(l);
                    viz.add(l);
                }
            }
        }
        return false;
    }

    /**
     * Function that verifies if an instance of the problem is valid
     *
     * @param locations a list of locations
     * @param roads a list of roads
     * @return TRUE if the instance is valid, FALSE otherwise
     */
    public static  boolean validInstance(List<Location> locations, List<Road> roads)
    {
        boolean ok=true;

        for(int i=0; i<locations.size()-1;i++)
        {
            for(int j=i+1;j<locations.size();j++)
            {
                if(locations.get(i).equals(locations.get(j)))
                {
                    System.out.println("Duplicate location found: "+ locations.get(i).getName());
                    ok=false;
                    break;
                }
            }
        }

        for(int i=0;i<roads.size()-1;i++)
        {
            for(int j=i+1;j<roads.size();j++)
            {
                if(roads.get(i).equals(roads.get(j)))
                {
                    System.out.println("Duplicate road found: "+roads.get(i).getName());
                    ok=false;
                    break;
                }
            }
        }
        for (Road road : roads) {
            double distanta = calculateDistance(road.getPointA(), road.getPointB());
            if (road.getLength() < distanta) {
                System.out.println("Invalid road");
                ok = false;
            }
        }
        return ok;
    }

    /**
     * Function that computes the distance between 2 locations
     *
     * @param c1 location object for the first city
     * @param c2 location object for the second city
     * @return the distance between the c1 and c2 locations
     */
    private static double calculateDistance(Location c1,Location c2){
        return Point2D.distance(c1.getX(),c1.getY(),c2.getX(),c2.getY());
    }
}