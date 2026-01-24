package Task_Manager;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    List<Task> tasks=new ArrayList<>();

    private int genId=1;

    public int generateTaskId(){
        return genId++;
    }

    public void addTask(Task task){
        if(task==null){
            throw new IllegalArgumentException("Пусто");
        }
        tasks.add(task);
    }

    public ArrayList<Task> getAllTasks(){
        return new ArrayList<>(tasks);
    }

    public boolean removeTaskById(int id){
        for(int i=0; i<tasks.size(); i++){
            if(tasks.get(i).getId()==id){
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    public Task findTaskById(int id){
        for(int i=0; i<tasks.size(); i++){
            if(tasks.get(i).getId()==id){
                return tasks.get(i);
            }
        }
        return null;
    }

    public List<Task> getTasksbyPriority(int priority){
        List<Task> result=new ArrayList<>();

        for(int i=0; i<tasks.size(); i++){
            if(tasks.get(i).getPriority()==priority){
                result.add(tasks.get(i));
            }
        }
        return result;
    }

    public void updateTask(Task task){

    }
}
