package Vehicles;

import java.util.ArrayList;
import java.util.List;

public class RentalService {
    List<Vehicle> vehicles=new ArrayList<>();
    private int nextVehicleId = 1;


    public int generateVehicleId(){
        return nextVehicleId++;
    }

    public void addVehicle(Vehicle vehicle){
        if (vehicle == null) throw new IllegalArgumentException("Vehicle can't be null");
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        if (vehicle == null) throw new IllegalArgumentException("Vehicle can't be null");
        vehicles.remove(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return new ArrayList<>(vehicles);
    }

    public boolean removeVehicleById(int id) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getId() == id) {
                vehicles.remove(i);
                return true;
            }
        }
        return false;
    }


}
