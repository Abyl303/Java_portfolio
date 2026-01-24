package Task_Manager;

public class Task {
    private int id;
    private String title;
    private String description;
    private String dueDate;
    private int priority;

    public Task(int id,String title,String description,String dueDate,int priority){
        this.id=id;
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        setPriority(priority);
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getDueDate(){
        return dueDate;
    }
    public int getPriority(){
        return priority;
    }

    public void setTitle(String title){
        if(title==null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't be empty!");
        }
        this.title=title;
    }
    public void setDescription(String description){
        if(description==null || description.trim().isEmpty()){
            throw new IllegalArgumentException("Description can't be empty!");
        }
        this.description=description;
    }
    public void setDueDate(String dueDate){
        if(dueDate==null || dueDate.trim().isEmpty()){
            throw new IllegalArgumentException("Due date can't be empty!");
        }
        this.dueDate=dueDate;
    }
    public void setPriority(int priority){
        if(priority>5 || priority<=0){
            throw new IllegalArgumentException("Priority must be between 1 and 5!");
        }
        this.priority=priority;
    }
    @Override
    public String toString(){
        return "ID: "+id+". Title: "+title+". Description: "+description+". Due date: "+dueDate+". Priority: "+priority;
    }
}
