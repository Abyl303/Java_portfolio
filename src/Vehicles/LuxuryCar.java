package Vehicles;

public class LuxuryCar extends Vehicle{
    private double luxuryFeePercent; // например 20 (%)
    private int serviceFee;

    public LuxuryCar(int id,String brand,String model, int year,int pricePerDay,boolean available,double luxuryFeePercent,int serviceFee  ){
        super(id,brand,model,year,pricePerDay,available);
        setLuxuryFeePercent(luxuryFeePercent);
        setServiceFee(serviceFee);
    }

    public double getLuxuryFeePercent(){
        return luxuryFeePercent;
    }
    public int getServiceFee(){
        return serviceFee;
    }

    public void setLuxuryFeePercent(double luxuryFeePercent){
        if(luxuryFeePercent<0){
            throw new IllegalArgumentException("Percentage must be greater than 0!");
        }
        this.luxuryFeePercent=luxuryFeePercent;
    }
    public void setServiceFee(int serviceFee){
        if(serviceFee<0){
            throw new IllegalArgumentException("Service must be greater than 0!");
        }
        this.serviceFee=serviceFee;
    }


    @Override
    public double calculateRentalPrice(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be greater than 0!");
        }
        double base = getPricePerDay() * days;
        double fee = base * (luxuryFeePercent / 100.0);
        return base+fee+serviceFee;
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
                ", luxuryFeePercent=" + luxuryFeePercent +
                ", serviceFee=" + serviceFee +
                '}';
    }
}
