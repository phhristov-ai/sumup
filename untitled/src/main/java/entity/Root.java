package entity;

import java.util.ArrayList;

public class Root{
    private ArrayList<Task> tasks;

    public Root() {
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "entities.Root{" +
                "tasks=" + tasks +
                '}';
    }
}