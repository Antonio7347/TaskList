package org.example.tasklist.model;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(String taskName) {
        tasks.removeIf(task -> task.getName().equalsIgnoreCase(taskName));
    }

    public List<Task> getPendingTasks() {
        return tasks;
    }
}
