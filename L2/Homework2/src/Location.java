public abstract class Location {
    private String name;
    private String type;
    private int X;
    private int Y;
    public  Location(String name, String type, int x, int y){
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
    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return getX() == location.getX() && getY() == location.getY() && getName().equals(location.getName()) && getType() == location.getType();
    }

}
