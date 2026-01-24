package Library;

public class Magazine extends Item{

    private int issueNumber;
    private String month;

    public Magazine(int id,String title, int year,int issueNumber,String month){
        super(id, title, year);
        setIssueNumber(issueNumber);
        setMonth(month);
    }

    public int getIssueNumber(){
        return issueNumber;
    }
    public String getMonth(){
        return month;
    }

    public void setIssueNumber(int issueNumber){
        if(issueNumber<=0){
            throw new IllegalArgumentException("Issue number must be greater than 0!");
        }
        this.issueNumber=issueNumber;
    }

    public void setMonth(String month){
        if(month==null || month.trim().isEmpty()){
            throw new IllegalArgumentException("Month can't be empty!");
        }
        this.month=month;
    }
    @Override
    public String getInfo() {
        return "ID: "+getId()+". Title: "+getTitle()+". Year: "+getYear()+". Issuenumber: "+issueNumber+". Month: "+month;
    }
}
