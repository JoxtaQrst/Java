public class Main {

    public static void main(String[] args) {
        Location l1 = new Location("Iasi", Location.LocationType.CITY,1,2);
        Road r1=new Road("E28", Road.RoadType.EXPRESS,20,90);

        System.out.println(l1);
        l1.setName("Roman");
        System.out.println(l1);
        System.out.println(r1);
        System.out.println(r1.getName());
    }
}