import java.awt.geom.Point2D;
import java.util.*;

public class Problem {

    /**
     * Main method for running the program and testing the solution
     *
     * @param args arguments passed to the program
     */
    public static void main(String[] args) {
        //we add 2 cities
        City l1 = new City("Iasi", 1, 2, 5000);
        Express r1 = new Express("E28", 10, 90);
        City l2 = new City("Roman", 2, 6, 1000);
        r1.setRoad(l1, l2);

        System.out.println(l1);
        System.out.println(l2);

        //we initialize a vector list with Roads and Locations
        List<Road> roads = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        locations.add(l1);
        locations.add(l2);
        roads.add(r1);
        if (validInstance(locations, roads)) // we verify that the instance of the problem is valid
        {
            System.out.println("Valid");

        } else System.out.println("Not valid");
    }

    /**
     * Function that verifies if an instance of the problem is valid
     *
     * @param locations a list of locations
     * @param roads     a list of roads
     * @return TRUE if the instance is valid, FALSE otherwise
     */
    public static boolean validInstance(List<Location> locations, List<Road> roads) {
        boolean ok = true;

        for (int i = 0; i < locations.size() - 1; i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                if (locations.get(i).equals(locations.get(j))) {
                    System.out.println("Duplicate location found: " + locations.get(i).getName());
                    ok = false;
                    break;
                }
            }
        }

        for (int i = 0; i < roads.size() - 1; i++) {
            for (int j = i + 1; j < roads.size(); j++) {
                if (roads.get(i).equals(roads.get(j))) {
                    System.out.println("Duplicate road found: " + roads.get(i).getName());
                    ok = false;
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
    private static double calculateDistance(Location c1, Location c2) {
        return Point2D.distance(c1.getX(), c1.getY(), c2.getX(), c2.getY());
    }
}