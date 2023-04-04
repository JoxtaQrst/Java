import java.util.*;

public class Main {
    public static void main(String[] args) {

        Programmer p1 = new Programmer("Leo", "14/03/2002",150);
        Programmer p2 = new Programmer("Robert", "25/03/2002",110);
        Designer d2 = new Designer("Ionut", "10/01/2003",20);
        Designer d1 = new Designer("Fabian","29/06/2002",80);
        d1.addRelations(d2,"best friends");
        d2.addRelations(d1,"best friends");
        d1.addRelations(p1,"friend");
        p1.addRelations(d1,"friend");
        d1.addRelations(p2,"friend");
        p2.addRelations(d1,"friend");

        p2.addRelations(p1,"friend");
        p1.addRelations(p2,"friend");

        Company c1 = new Company("AMD");
        Company c2 = new Company("Mojang");
        Company c3 = new Company("EdyLiv");
        c3.addEmployee(d1);
        c3.addEmployee(p1);
        c1.addEmployee(d2);
        c2.addEmployee(p2);
        c2.addEmployee(d1);
        //c3.getEmployees();

        System.out.println();

        Network network=new Network();
        network.addNode(d1);
        network.addNode(d2);
        network.addNode(p1);
        network.addNode(p2);
        network.addNode(c1);
        network.addNode(c2);
        network.addNode(c3);

        List<Object> sortedNodes = network.getNodesByImportance();
        for(Object node :sortedNodes)
        {
            if(node instanceof Person)
            {
                System.out.println(((Person) node).getName() + " is a " + node.getClass().getName() + " at " + ((Person) node).getCompany().getName() + " (importance: " + network.getImportance(node) + ")");
            }
            else if (node instanceof Company)
            {
                System.out.println("Company : " + ((Company) node).getName() + " (importance: " + network.getImportance(node) + ")");
            }

        }
    }
}