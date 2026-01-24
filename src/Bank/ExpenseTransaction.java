package Bank;

public class ExpenseTransaction extends Transaction {

    public ExpenseTransaction(int id, double amount, String category, String date, String comment) {
        super(id, amount, category, date, comment);
    }

    @Override
    public double signedAmount() {
        return -getAmount();
    }
}
