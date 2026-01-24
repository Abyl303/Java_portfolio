package Vehicles;

public class EconomyCar extends Vehicle{

    public EconomyCar(int id,String brand,String model, int year,int pricePerDay,boolean available){
        super(id,brand,model,year,pricePerDay,available);
    }


    @Override
    public double calculateRentalPrice(int days) {
        if(days>0) {
            return getPricePerDay()*days;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "LuxuryCar{" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", pricePerDay=" + getPricePerDay() +
                ", available=" + available() +
                '}';
    }
}
