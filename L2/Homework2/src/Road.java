import java.util.Objects;

public class Road {
    private String name;
    private String type;
    private int length;
    private int speed_limit;

    public Road(String name, String type, int length, int speed_limit) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Road road)) return false;
        return getLength() == road.getLength() && getSpeed_limit() == road.getSpeed_limit() && getName().equals(road.getName()) && getType().equals(road.getType());
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
