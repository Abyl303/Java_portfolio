package Book;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Sevice service = new Sevice();
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine(); // очистка ENTER

            switch (choice) {

                case 1: {
                    long id = service.generateId();

                    System.out.print("Enter title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter genre (HORROR/ACTION): ");
                    String genreInput = sc.nextLine();

                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    sc.nextLine(); // очистка ENTER

                    System.out.print("Enter status (AVAILABLE/BORROWED): ");
                    String statusInput = sc.nextLine();

                    try {
                        BookGenre genre = BookGenre.valueOf(genreInput.trim().toUpperCase());
                        BookStatus status = BookStatus.valueOf(statusInput.trim().toUpperCase());

                        service.addBook(new Book(id, title, author, genre, year, status));
                        System.out.println("✅ Book added!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                    break;
                }

                case 2: {
                    System.out.print("Enter id to remove: ");
                    long id1 = sc.nextLong();
                    sc.nextLine();

                    boolean removed = service.removeById(id1);
                    System.out.println(removed ? "✅ Removed!" : "❌ Book not found");
                    break;
                }

                case 3: {
                    List<Book> books = service.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("Library is empty");
                    } else {
                        for(int i=0; i<books.size(); i++){
                            System.out.println(books.get(i));
                        }
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter id: ");
                    long id2 = sc.nextLong();
                    sc.nextLine();

                    boolean ok = service.updateGenreToAction(id2);
                    System.out.println(ok ? "✅ Genre updated to ACTION" : "❌ Book not found");
                    break;
                }

                case 5: {
                    System.out.print("Enter id: ");
                    long id3 = sc.nextLong();
                    sc.nextLine();

                    boolean ok = service.updateGenreToHorror(id3);
                    System.out.println(ok ? "✅ Genre updated to HORROR" : "❌ Book not found");
                    break;
                }

                case 6: {
                    System.out.print("Enter id: ");
                    long id4 = sc.nextLong();
                    sc.nextLine();

                    boolean ok = service.updateStatusDone(id4);
                    System.out.println(ok ? "✅ Status updated to AVAILABLE" : "❌ Book not found");
                    break;
                }

                case 7: {
                    System.out.print("Enter id: ");
                    long id5 = sc.nextLong();
                    sc.nextLine();

                    boolean ok = service.updateStatusToDo(id5);
                    System.out.println(ok ? "✅ Status updated to BORROWED" : "❌ Book not found");
                    break;
                }

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("❌ Wrong choice");
            }
        }

        sc.close();
        System.out.println("Exit");
    }

    private static void printMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Add book");
        System.out.println("2. Remove book");
        System.out.println("3. Show books");
        System.out.println("4. Update genre to ACTION");
        System.out.println("5. Update genre to HORROR");
        System.out.println("6. Update status to AVAILABLE");
        System.out.println("7. Update status to BORROWED");
        System.out.println("0. Exit");
    }
}
