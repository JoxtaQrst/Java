
public class City extends Location {

    private int population;

    /**
     * Constructor for City class, uses the Location superclass
     *
     * @param name       of the city
     * @param x          coordinate
     * @param y          coordinate
     * @param population city
     */
    public City(String name, int x, int y, int population) {
        super(name, "CITY", x, y);
        this.population = population;
    }

    /**
     * Function that gives the population of the city
     *
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return the Object with its attributes as a string, uses the
     * .toString function from its superclass and adds the population
     */
    @Override
    public String toString() {
        return "City{" +
                "population=" + population +
                "} " + super.toString();
    }
}
