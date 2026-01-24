package Task_Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        TaskService service=new TaskService();
        boolean running=true;
        while(running){
            printMenu();
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    int id = service.generateTaskId();

                    System.out.println("Title: ");
                    String title = sc.next();

                    System.out.println("Description: ");
                    String description = sc.next();

                    System.out.println("Due date (YYYY-MM-DD): ");
                    String dueDate = sc.next();

                    System.out.println("Priority (1-5): ");
                    int priority = sc.nextInt();

                    try{
                        service.addTask(new Task(id, title, description, dueDate, priority));
                        System.out.println("Задача добавлена. ID = " + id);
                    } catch (IllegalArgumentException e){
                        System.out.println("Error: "+e.getMessage());
                    }
                    break;
                case 2:
                    int id1= sc.nextInt();
                    System.out.println("Are you sure? (y/n)");
                    String sure=sc.next();
                    sure=sure.toLowerCase();
                    if (sure.equals("y")) {
                        boolean removed = service.removeTaskById(id1);
                        System.out.println(removed ? "Удалено." : "Задача не найдена.");
                    } else{
                        System.out.println("Отмена операции.");
                    }

                    break;

                case 3:
                    List<Task> tasks=service.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("Задач нет.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(tasks.get(i));
                        }
                    }
                    break;

                case 4:
                    int id2= sc.nextInt();
                    Task task=service.findTaskById(id2);
                    if (task == null) {
                        System.out.println("Задача не найдена.");
                    } else {
                        System.out.println("Найдена задача: " + task);
                    }
                    break;

                case 5:
                    int id3=sc.nextInt();
                    Task task2=service.findTaskById(id3);
                    if(task2==null){
                        System.out.println("Задача не найдена");
                    }
                    else{
                        String newTitle=sc.next();
                        String newDescription=sc.next();
                        String newDueDate=sc.next();
                        int newPriority=sc.nextInt();
                        task2.setTitle(newTitle);
                        task2.setDescription(newDescription);
                        task2.setDueDate(newDueDate);
                        task2.setPriority(newPriority);
                        System.out.println("Задача обновлена.");
                    }
                    break;

                case 6:
                    System.out.print("Введите приоритет (1-5): ");
                    int p = sc.nextInt();

                    List<Task> filtered = service.getTasksbyPriority(p);

                    if (filtered.isEmpty()) {
                        System.out.println("Задач с таким приоритетом нет.");
                    } else {
                        for (Task t : filtered) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 0:
                    running=false;
                    break;
                default:
                    System.out.println("Неверный пункт меню.");
            }
        }
        System.out.println("Выход.");
        sc.close();
    }

    public static void printMenu(){
        System.out.println("1. Добавить задание");
        System.out.println("2. Удалить задание");
        System.out.println("3. Показать все задания");
        System.out.println("4. Поиск по айди");
        System.out.println("5. Обновить данные");
        System.out.println("6. Отфильровать данные");
        System.out.println("0. Выход");
    }
}
