public class Cars {
    protected int id;
    protected int km;
    protected double price;
    protected String model;
    protected String color;

    public Cars() {

    }

    public Cars(int id) {
        this.id = id;
    }

    public Cars(int id, int km, double price, String model, String color) {
        this.id = id;
        this.km = km;
        this.price = price;
        this.model = model;
        this.color = color;
    }

    public Cars(int km, double price, String model, String color) {
        this.km = km;
        this.price = price;
        this.model = model;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
