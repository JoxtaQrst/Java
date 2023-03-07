public class Main {
    public static void main(String[] args) {
        City l1 = new City("Iasi",1,2,5000);
        Express r1=new Express("E28", 20,90);

        System.out.println(l1);
        l1.setName("Roman");
        System.out.println(l1);
        System.out.println(r1);
        System.out.println(r1.getName());
    }
}