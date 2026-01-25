package User_Management_System;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        UserService service=new UserService();
        Scanner sc=new Scanner(System.in);

        boolean running=true;
        while (running){
            printMenu();
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    int id= service.generateId();
                    System.out.println("Введите имя:");
                    String username= sc.next();
                    System.out.println("Введите email:");
                    String email= sc.next();
                    System.out.println("Введите пароль:");
                    String password= sc.next();
                    System.out.println("Введите роль:");
                    String role= sc.next();
                    System.out.println("Введите статус:");
                    boolean active= Boolean.parseBoolean(sc.next());
                    System.out.println("Введите дату:");
                    String createdAt= sc.next();
                    try{
                        service.addUser(new User(id, username, email,password, role, active, createdAt));
                        System.out.println("Пользователь добавлен. ID = " + id);
                    } catch (IllegalArgumentException e){
                        System.out.println("Ошибка: "+e.getMessage());
                    }
                    break;

                case 2:
                    List<User> users=service.getAllUsers();
                    if(users.isEmpty()){
                        System.out.println("Нет пользователей");
                    } else {
                        for (int i = 0; i < users.size(); i++) {
                            System.out.println(users.get(i));
                        }
                    }
                    break;


                case 3:
                    System.out.println("Введите ID: ");
                    int idd= sc.nextInt();
                    boolean users3=service.removeUserById(idd);
                    System.out.println(users3);
                    break;

                case 4:
                    System.out.print("Введите имя: ");
                    String username1 = sc.next();

                    User found = service.findUserByUsername(username1);
                    if (found == null) {
                        System.out.println("Пользователь не найден.");
                    } else {
                        System.out.println(found);
                    }
                    break;

                case 5:
                    int n= sc.nextInt();
                    boolean users4=service.deactivateUser(n);
                    System.out.println(users4);
                    break;

                case 6:
                    int n1= sc.nextInt();
                    boolean users7=service.activateUser(n1);
                    System.out.println(users7);
                    break;

                case 7:
                    List<User> users1=service.getActiveUsers();
                    for(int i=0; i<users1.size(); i++){
                        System.out.println(users1.get(i));
                    }
                    break;

                case 8:
                    String role1= sc.next();
                    List<User> users2=service.getUsersByRole(role1);
                    if(users2.isEmpty()){
                        System.out.println("Пользователей с такой ролью нет.");
                    } else {
                        for (int i = 0; i < users2.size(); i++) {
                            System.out.println(users2.get(i));
                        }
                    }
                    break;

                case 0:
                    running=false;
                    break;
            }
        }
        System.out.println("Выход");
        sc.close();
    }

    public static void printMenu(){
        System.out.println("1. Добавить пользователя");
        System.out.println("2. Показать всех пользователей");
        System.out.println("3. Удалить пользователя по id");
        System.out.println("4. Найти по username");
        System.out.println("5. Деактивировать пользователя");
        System.out.println("6. Активировать пользователя");
        System.out.println("7. Показать активных пользователей");
        System.out.println("8. Показать пользователей по роли");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт: ");
    }

}
