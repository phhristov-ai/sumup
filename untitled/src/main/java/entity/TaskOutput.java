package entity;

public class TaskOutput {

    private String name;
    private String command;

    public TaskOutput (final Task task) {
        this.name = task.getName();
        this.command = task.getCommand();
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
}
