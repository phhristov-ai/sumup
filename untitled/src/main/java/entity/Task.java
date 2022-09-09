package entity;

import java.util.ArrayList;

public class Task {
    private String name;
    private String command;
    private ArrayList<String> requires;
    private ArrayList<Task> precedingTasks;
    private int degree;

    public Task() {
        this.requires = new ArrayList<String>();
        this.precedingTasks = new ArrayList<Task>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList<String> getRequires() {
        return requires;
    }

    public void setRequires(ArrayList<String> requires) {
        this.requires = requires;
    }

    public ArrayList<Task> getPrecedingTasks() {
        return precedingTasks;
    }

    public void setPrecedingTasks(ArrayList<Task> precedingTasks) {
        this.precedingTasks = precedingTasks;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

}

