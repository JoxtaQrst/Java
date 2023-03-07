public class Location {
    enum LocationType{
        CITY,AIRPORT,GAS_STATION,VILLAGE;
    }
    private String name;
    private LocationType type;
    private int X;
    private int Y;
    public  Location(String name, LocationType type, int x, int y){
        this.name=name;
        this.type=type;
        this.X=x;
        this.Y=y;
    }
    public String getName()
    {
        return name;
    }
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public LocationType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", X=" + X +
                ", Y=" + Y +
                '}';
    }
}
