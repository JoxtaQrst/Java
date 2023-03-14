import java.util.*;

public class Main {
    public static void main(String[] args) {

        Person p1 = new Person("Leo");
        Person p2 = new Person("Bogdan");
        Person p3 = new Person("Ionut");
        Person p4 = new Person("Fabian");

        Company c1 = new Company("AMD");
        Company c2 = new Company("Tiktok");
        Company c3 = new Company("EdyLiv");

        p4.addFriend(p2);
        p4.addFriend(p3);
        p2.addFriend(p1);

        c2.addEmployee(p2);
        c3.addEmployee(p4);
        c3.addEmployee(p3);

        List<Node> nodes = new ArrayList<Node>();
        nodes.add(p1);
        nodes.add(p2);
        nodes.add(p4);
        nodes.add(c3);
        nodes.add(c2);


        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.getName().compareTo(n2.getName());
            }
        });

        for (Node element : nodes) {
            System.out.println(element.getName());
        }
    }
}