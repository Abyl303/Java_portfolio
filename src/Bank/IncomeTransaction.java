package Bank;

public class IncomeTransaction extends Transaction{
    public IncomeTransaction(int id,double amount,String category,String date,String comment){
        super(id, amount, category, date, comment);
    }
    @Override
    public double signedAmount() {
        return getAmount();
    }
}
