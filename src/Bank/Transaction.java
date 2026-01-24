package Bank;

public abstract class Transaction {
    protected int id;            // уникальный идентификатор
    protected double amount;     // сумма операции
    protected String category;   // категория (еда, транспорт и т.д.)
    protected String date;       // дата операции
    protected String comment;    // комментарий

    public Transaction(int id,double amount,String category,String date,String comment){
        this.id=id;
        setAmount(amount);
        setCategory(category);
        setDate(date);
        setComment(comment);
    }
    public Transaction(){}

    public int getId(){
        return id;
    }
    public double getAmount(){
        return amount;
    }
    public String getCategory(){
        return category;
    }
    public String getDate(){
        return date;
    }
    public String getComment(){
        return comment;
    }

    public void setAmount(double amount){
        if(amount<=0){
            throw new IllegalArgumentException("Amount must be greater than 0!");
        }
        this.amount=amount;
    }
    public void setCategory(String category){
        if(category==null || category.trim().isEmpty()){
            throw new IllegalArgumentException("Category can't be empty!");
        }
        this.category=category;
    }
    public void setDate(String date){
        if(date==null || date.trim().isEmpty()){
            throw new IllegalArgumentException("Date can't be empty!");
        }
        this.date=date;
    }
    public void setComment(String comment){
        if(comment==null || comment.trim().isEmpty()){
            throw new IllegalArgumentException("Comment can't be empty!");
        }
        this.comment=comment;
    }

    public abstract double signedAmount();

    @Override
    public String toString(){
        return "ID: "+id+". Amount: "+amount+". Category: "+category+". Date: "+date+". Comment: "+comment;
    }
}
