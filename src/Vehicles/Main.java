package Vehicles;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RentalService service=new RentalService();
        Scanner sc=new Scanner(System.in);

        boolean running=true;

        while(running){
            printMenu();
            int choice= sc.nextInt();
            switch (choice){

                case 1:
                    System.out.println("Тип авто: 1 - Economy, 2 - Luxury");
                    int type = sc.nextInt();
                    System.out.println("Brand: ");
                    String brand=sc.next();
                    System.out.println("Model: ");
                    String model=sc.next();
                    System.out.println("Year: ");
                    int year=sc.nextInt();
                    System.out.print("Price per day: ");
                    int pricePerDay = sc.nextInt();
                    int id = service.generateVehicleId();
                    boolean available = true;

                    try{
                        if (type == 1) {
                            service.addVehicle(
                                    new EconomyCar(id, brand, model, year, pricePerDay, available)
                            );
                            System.out.println("EconomyCar добавлен. ID = " + id);
                        } else if(type==2){
                            System.out.print("Luxury fee percent: ");
                            double percent = sc.nextDouble();

                            System.out.print("Service fee: ");
                            int serviceFee = sc.nextInt();
                            service.addVehicle(new LuxuryCar(id, brand, model, year, pricePerDay, available,percent, serviceFee ));
                            System.out.println("LuxuryCar добавлен. ID = " + id);
                        } else {
                            System.out.println("Неверный тип авто.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 2:
                    List<Vehicle> vehicles=service.getAllVehicles();
                    if (vehicles.isEmpty()) {
                        System.out.println("Автомобилей нет.");
                    } else {
                        for (int i = 0; i < vehicles.size(); i++) {
                            System.out.println(vehicles.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.print("Введите id: ");
                    int id1 = sc.nextInt();

                    boolean removed = service.removeVehicleById(id1);
                    System.out.println(removed ? "Удалено." : "Не найдено.");
                    break;

                case 0:
                    running =false;
                    break;

                default:
                    System.out.println("Неверный пункт");
            }
            System.out.println();
        }
        System.out.println("Выход.");
        sc.close();
    }

    public static void printMenu(){
        System.out.println("""
                ===== Car Rental (v1) =====
                1. Добавить автомобиль
                2. Показать все автомобили
                3. Удалить автомобиль
                0. Выход
                """);
        System.out.print("Выберите пункт: ");
    }
}
