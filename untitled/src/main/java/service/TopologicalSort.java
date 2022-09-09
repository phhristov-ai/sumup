package service;

import entity.Task;

import java.util.*;

public class TopologicalSort {

    private final List<Task> tasks;

    public TopologicalSort(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private void calculateDegrees() {
        for (Task task: this.tasks) {
            for (Task neighbor: task.getPrecedingTasks()) {
                neighbor.setDegree(neighbor.getDegree() + 1);
            }
        }
    }

    public Optional<ArrayList<Task>> sort() {

        this.calculateDegrees();
        final Queue<Task> queue = new LinkedList<>();

        for (Task task: this.tasks) {
            if (task.getDegree() == 0) {
                queue.add(task);
            }
        }
        final ArrayList<Task> order = new ArrayList<>();

        int count = 0;

        while (!queue.isEmpty()) {
            Task currentTask = queue.poll();
            order.add(currentTask);

            for (Task precedingTask : currentTask.getPrecedingTasks()) {
                precedingTask.setDegree(precedingTask.getDegree() - 1);

                if (precedingTask.getDegree() == 0) {
                    queue.add(precedingTask);
                }

            }
            count++;

        }

        if (count != tasks.size()) {
            // There is a cycle
            return Optional.empty();
        } else {
            System.out.println(order);
            return Optional.of(order);
        }
    }
}
