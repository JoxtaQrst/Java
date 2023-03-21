public class Programmer extends Person {
    private static final String birthDate = null;

    private final int typeSpeed;
    public Programmer(String name,String birthDate,int typeSpeed) {
        super(name, birthDate);
        this.typeSpeed=typeSpeed;
    }
    public int getTypeSpeed() {
        return typeSpeed;
    }
}
