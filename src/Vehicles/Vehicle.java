package Vehicles;

public abstract class Vehicle {
    private int id;
    private String brand;
    private String model;
    private int year;
    private int pricePerDay;
    private boolean available;

    public Vehicle(int id,String brand,String model, int year,int pricePerDay,boolean available){
        this.id=id;
        setBrand(brand);
        setModel(model);
        setYear(year);
        setPricePerDay(pricePerDay);
        setAvailable(available);
    }

    public Vehicle(){}

    public int getId(){
        return id;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }
    public int getYear(){
        return year;
    }
    public int getPricePerDay(){
        return pricePerDay;
    }
    public boolean available(){
        return available;
    }

    public void setBrand(String brand){
        if(brand==null || brand.trim().isEmpty()){
            throw new IllegalArgumentException("Brand can't be empty.");
        }
        this.brand=brand;
    }
    public void setModel(String model){
        if(model==null || model.trim().isEmpty()){
            throw new IllegalArgumentException("Model can't be empty.");
        }
        this.model=model;
    }
    public void setYear(int year){
        if(year<=1450){
            throw new IllegalArgumentException("Year is incorrect!");
        }
        this.year=year;
    }
    public void setPricePerDay(int pricePerDay){
        if(pricePerDay<=0){
            throw new IllegalArgumentException("Price per day must be greater than 0!");
        }
        this.pricePerDay=pricePerDay;
    }
    public void setAvailable(boolean available){
        this.available=available;
    }

    public abstract double calculateRentalPrice(int days);

    @Override
    public String toString(){
        return "";
    }

}
