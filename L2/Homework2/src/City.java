public class City extends Location {

    private int population;
    public City(String name, int x, int y,int population) {
        super(name, "CITY", x, y);
        this.population=population;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "population=" + population +
                "} " + super.toString();
    }
}
