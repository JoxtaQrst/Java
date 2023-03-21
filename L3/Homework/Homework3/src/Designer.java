public class Designer extends Person{
    private static final String birthDate = null;
    private final int creativity;

    public Designer(String name,String birthDate,int creativity) {
        super(name, birthDate);
        this.creativity=creativity;
    }

    public int getCreativity() {
        return creativity;
    }
}
