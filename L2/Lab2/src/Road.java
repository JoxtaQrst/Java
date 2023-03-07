public class Road {
    enum RoadType{
        EXPRESS,HIGHWAY,COUNTRY,PATH;
    }
    private String name;
    private RoadType type;
    private int length;
    private int speed_limit;

    public Road(String name, RoadType type, int length, int speed_limit) {
        this.name = name;
        this.type = type;
        this.length = length;
        this.speed_limit = speed_limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoadType getType() {
        return type;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSpeed_limit() {
        return speed_limit;
    }

    public void setSpeed_limit(int speed_limit) {
        this.speed_limit = speed_limit;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", length=" + length +
                ", speed_limit=" + speed_limit +
                '}';
    }
}
