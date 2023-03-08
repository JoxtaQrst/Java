import java.awt.geom.Point2D;
public class Main {

    public static void main(String[] args) {
        City l1 = new City("Iasi",1,2,5000);
        Express r1=new Express("E28", 2,90);
        City l2 = new City("Roman",1,2,1000);

        System.out.println(l1);
        System.out.println(l2);
        //equals
        if(l1.equals(l2)){
            System.out.println("Orase la fel");
        }
        else{
            System.out.println("Orase diferite");
        }

        double lungime=r1.getLength();
        if(calculateDistance(l1,l2)>lungime){
            System.out.println(calculateDistance(l1,l2));
            System.out.println("Drum valid");
            //setRoad
        }
        else{
            System.out.println("Drum nevalid");
        }
        //
    }
    private static double calculateDistance(City c1,City c2){
        return Point2D.distance(c1.getX(),c1.getY(),c2.getX(),c2.getY());
    }
}