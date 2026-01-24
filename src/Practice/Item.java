package Practice;

public abstract class Item {
    private int id;
    private String title;
    private int year;

    public Item(int id,String title, int year){
        this.id=id;
        setTitle(title);
        setYear(year);
    }

    public Item(){}

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public int getYear(){
        return year;
    }

    public void setTitle(String title){
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't be empty");
        }
        this.title=title;
    }
    public void setYear(int year){
        if(year<1450){
            throw new IllegalArgumentException("Wrong year!");
        }
        this.year=year;
    }

    public abstract String getInfo();
}
