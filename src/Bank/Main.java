package Bank;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        FinanceService service = new FinanceService();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = InputUtil.readInt("Выберите пункт: ");

            switch (choice) {
                case 1 -> handleAdd(service);
                case 2 -> handleShowAll(service);
                case 3 -> handleFilterByCategory(service);
                case 4 -> handleFilterByType(service);
                case 5 -> handleStats(service);
                case 6 -> handleStatsByPeriod(service);
                case 7 -> handleRemove(service);
                case 9 -> {
                    service.addDemoData();
                    System.out.println("Демо-данные добавлены.");
                }
                case 0 -> running = false;
                default -> System.out.println("Неверный пункт меню.");
            }
        }

        System.out.println("Выход.");
    }

    private static void printMenu() {
        System.out.println("""
                ===== Finance Manager =====
                1. Добавить операцию
                2. Показать все операции
                3. Фильтр по категории
                4. Фильтр по типу (доход/расход)
                5. Статистика (за всё время)
                6. Статистика за период (YYYY-MM-DD)
                7. Удалить операцию по id
                9. Добавить демо-данные
                0. Выход
                """);
    }

    private static void handleAdd(FinanceService service) {
        int type = InputUtil.readInt("Тип (1 - доход, 2 - расход): ");
        double amount = InputUtil.readDouble("Сумма: ");

        System.out.println("Подсказка категорий: ");
        for (String c : service.getDefaultCategories()) {
            System.out.print(c + "  ");
        }
        System.out.println();

        String category = InputUtil.readString("Категория: ");
        String date = InputUtil.readString("Дата (YYYY-MM-DD): ");
        String comment = InputUtil.readString("Комментарий: ");

        try {
            if (type == 1) {
                service.addIncome(amount, category, date, comment);
                System.out.println("Доход добавлен.");
            } else if (type == 2) {
                service.addExpense(amount, category, date, comment);
                System.out.println("Расход добавлен.");
            } else {
                System.out.println("Неверный тип.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void handleShowAll(FinanceService service) {
        ArrayList<Transaction> list = service.getAllTransactions();
        if (list.isEmpty()) {
            System.out.println("Операций нет.");
            return;
        }
        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    private static void handleFilterByCategory(FinanceService service) {
        String cat = InputUtil.readString("Введите категорию: ");
        ArrayList<Transaction> list = service.filterByCategory(cat);
        if (list.isEmpty()) {
            System.out.println("Ничего не найдено.");
            return;
        }
        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    private static void handleFilterByType(FinanceService service) {
        int type = InputUtil.readInt("Тип (1 - доход, 2 - расход): ");
        ArrayList<Transaction> list = (type == 1) ? service.getIncomeTransactions() : service.getExpenseTransactions();

        if (list.isEmpty()) {
            System.out.println("Ничего не найдено.");
            return;
        }
        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    private static void handleStats(FinanceService service) {
        System.out.println("Баланс: " + service.calculateBalance());
        System.out.println("Доходы всего: " + service.calculateTotalIncome());
        System.out.println("Расходы всего: " + service.calculateTotalExpense());
        System.out.println("Средний расход: " + service.calculateAverageExpense());

        HashMap<String, Double> byCat = service.calculateExpenseByCategory();
        if (!byCat.isEmpty()) {
            System.out.println("Расходы по категориям:");
            for (String k : byCat.keySet()) {
                System.out.println(" - " + k + ": " + byCat.get(k));
            }

            System.out.println("Топ-3 категорий расходов: " + service.getTopExpenseCategories(3));
        }
    }

    private static void handleStatsByPeriod(FinanceService service) {
        String start = InputUtil.readString("Start date (YYYY-MM-DD): ");
        String end = InputUtil.readString("End date (YYYY-MM-DD): ");

        ArrayList<Transaction> period = service.filterByDateRange(start, end);
        if (period.isEmpty()) {
            System.out.println("За этот период операций нет.");
            return;
        }

        double balance = 0;
        double income = 0;
        double expense = 0;

        for (Transaction t : period) {
            balance += t.signedAmount();
            if (t instanceof IncomeTransaction) income += t.getAmount();
            if (t instanceof ExpenseTransaction) expense += t.getAmount();
        }

        System.out.println("Операции за период:");
        for (Transaction t : period) {
            System.out.println(t);
        }
        System.out.println("Итого за период:");
        System.out.println(" - Баланс: " + balance);
        System.out.println(" - Доходы: " + income);
        System.out.println(" - Расходы: " + expense);
    }

    private static void handleRemove(FinanceService service) {
        int id = InputUtil.readInt("Введите id для удаления: ");
        boolean ok = service.removeTransactionById(id);
        System.out.println(ok ? "Удалено." : "Не найдено.");
    }
}
