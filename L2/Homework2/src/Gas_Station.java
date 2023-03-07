public class Gas_Station extends Location {
    private double gas_price;

    public Gas_Station(String name, int x, int y,double gas_price) {
        super(name, "GAS_STATION", x, y);
        this.gas_price=gas_price;
    }

    public double getGas_price() {
        return gas_price;
    }

    public void setGas_price(double gas_price) {
        this.gas_price = gas_price;
    }
}
