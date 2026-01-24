package Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FinanceService {

    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private int nextId = 1;

    // чтобы показать массивы (можешь расширять)
    private final String[] defaultCategories = {
            "Food", "Transport", "Home", "Health", "Education", "Entertainment", "Salary", "Other"
    };

    public int generateId() {
        return nextId++;
    }

    public String[] getDefaultCategories() {
        return defaultCategories;
    }

    public boolean isValidCategory(String category) {
        if (category == null) return false;
        String c = category.trim();
        if (c.isEmpty()) return false;
        for (String dc : defaultCategories) {
            if (dc.equalsIgnoreCase(c)) return true;
        }
        return false;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null) throw new IllegalArgumentException("Transaction can't be null");
        transactions.add(transaction);
    }

    public void addIncome(double amount, String category, String date, String comment) {
        int id = generateId();
        addTransaction(new IncomeTransaction(id, amount, category, date, comment));
    }

    public void addExpense(double amount, String category, String date, String comment) {
        int id = generateId();
        addTransaction(new ExpenseTransaction(id, amount, category, date, comment));
    }

    public ArrayList<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    public Transaction findTransactionById(int id) {
        for (Transaction t : transactions) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public boolean removeTransactionById(int id) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == id) {
                transactions.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Transaction> filterByCategory(String category) {
        ArrayList<Transaction> result = new ArrayList<>();
        if (category == null) return result;
        String target = category.trim();

        for (Transaction t : transactions) {
            if (t.getCategory() != null && t.getCategory().equalsIgnoreCase(target)) {
                result.add(t);
            }
        }
        return result;
    }

    public ArrayList<Transaction> getIncomeTransactions() {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t instanceof IncomeTransaction) result.add(t);
        }
        return result;
    }

    public ArrayList<Transaction> getExpenseTransactions() {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t instanceof ExpenseTransaction) result.add(t);
        }
        return result;
    }

    // Даты как String: ожидаем формат YYYY-MM-DD, сравнение строк работает для такого формата
    public ArrayList<Transaction> filterByDateRange(String startDate, String endDate) {
        ArrayList<Transaction> result = new ArrayList<>();
        if (startDate == null || endDate == null) return result;

        String start = startDate.trim();
        String end = endDate.trim();
        if (start.isEmpty() || end.isEmpty()) return result;

        for (Transaction t : transactions) {
            String d = t.getDate();
            if (d == null) continue;
            String dd = d.trim();
            if (dd.compareTo(start) >= 0 && dd.compareTo(end) <= 0) {
                result.add(t);
            }
        }
        return result;
    }

    public double calculateBalance() {
        double sum = 0;
        for (Transaction t : transactions) {
            sum += t.signedAmount(); // полиморфизм
        }
        return sum;
    }

    public double calculateTotalIncome() {
        double sum = 0;
        for (Transaction t : transactions) {
            if (t instanceof IncomeTransaction) sum += t.getAmount();
        }
        return sum;
    }

    public double calculateTotalExpense() {
        double sum = 0;
        for (Transaction t : transactions) {
            if (t instanceof ExpenseTransaction) sum += t.getAmount();
        }
        return sum;
    }

    public double calculateAverageExpense() {
        double sum = 0;
        int count = 0;
        for (Transaction t : transactions) {
            if (t instanceof ExpenseTransaction) {
                sum += t.getAmount();
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

    public HashMap<String, Double> calculateExpenseByCategory() {
        HashMap<String, Double> map = new HashMap<>();
        for (Transaction t : transactions) {
            if (t instanceof ExpenseTransaction) {
                String cat = t.getCategory();
                double val = t.getAmount();
                map.put(cat, map.getOrDefault(cat, 0.0) + val);
            }
        }
        return map;
    }

    public ArrayList<String> getTopExpenseCategories(int topN) {
        HashMap<String, Double> byCat = calculateExpenseByCategory();
        ArrayList<String> top = new ArrayList<>();

        // простой топ без сортировок коллекциями: выбираем максимум topN раз
        for (int k = 0; k < topN; k++) {
            String bestCat = null;
            double bestVal = -1;

            for (Map.Entry<String, Double> e : byCat.entrySet()) {
                if (!top.contains(e.getKey()) && e.getValue() > bestVal) {
                    bestVal = e.getValue();
                    bestCat = e.getKey();
                }
            }

            if (bestCat == null) break;
            top.add(bestCat);
        }
        return top;
    }

    public void addDemoData() {
        addIncome(5000, "Salary", "2026-01-10", "January salary");
        addExpense(1200, "Food", "2026-01-11", "Groceries");
        addExpense(600, "Transport", "2026-01-12", "Taxi");
        addExpense(300, "Food", "2026-01-13", "Snack");
    }
}
