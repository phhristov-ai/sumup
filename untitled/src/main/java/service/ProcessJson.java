package service;

import com.google.gson.Gson;
import entity.Root;
import entity.Task;
import entity.TaskOutput;

import java.util.ArrayList;
import java.util.Optional;

public class ProcessJson {

    public static Optional<ArrayList<Task>> convertJsonToObject(String jsonData) {
        final Gson gson = new Gson();

        final Root root = gson.fromJson(jsonData, Root.class);

        final ArrayList<Task> tasks = root.getTasks();

        // Add preceding tasks for each task
        for (Task task : tasks) {
            for (String requiredName : task.getRequires()) {
                for (Task requiredTask : tasks) {
                    if (requiredTask.getName().equals(requiredName)) {
                        requiredTask.getPrecedingTasks().add(task);
                    }
                }
            }
        }

        final TopologicalSort topologicalSort = new TopologicalSort(tasks);
        return topologicalSort.sort();
    }

    public static String convertObjectToJson(ArrayList<TaskOutput> taskOutputs) {
        final Gson gson = new Gson();
        return gson.toJson(taskOutputs);
    }
}
