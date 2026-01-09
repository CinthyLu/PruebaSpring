package vehicle.entity;

public class VehicleEntity {
     private int id;
    private String brand;
     private String model;
    private double price;
    private int stock;

    public Vehicle(int id, String brand, String Model, double price, int stock) {
        this.id = id;
        this.brand = brand;
        this.model = Model;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setModel(String model) { this.model = Model; }
    public void setPrice(double price) { this.price = price; }

}
