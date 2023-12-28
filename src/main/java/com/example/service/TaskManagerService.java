package com.example.service;

import com.example.http.HttpLinkRequest;
import com.example.model.ITask;
import com.example.model.ITaskManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManagerService implements ITaskManager {

    private final Map<Long, ITask> taskMap = new HashMap<>();
    private Long taskId = 0L;

    @Override
    public ITask getTask(Long id) {
        return taskMap.get(id);
    }

    @Override
    public List<ITask> getParentTasks() {
        return taskMap.values().stream()
                .filter(task -> task.getParentId() == null)
                .toList();
    }

    @Override
    public List<ITask> getAllTasks() {
        return taskMap.values().stream()
                .toList();
    }

    @Override
    public Long addTask(ITask task) {
        task.setId(taskId);
        taskMap.put(taskId, task);
        return taskId++;
    }

    @Override
    public Long updateTask(ITask task) {
        if (task.getId() == null) return null;
        Long id = task.getId();
        if (!taskMap.containsKey(id)) {
            return -1L;
        } else {
            ITask target = taskMap.get(id);
            if (!task.getTitle().isBlank()) target.setTitle(task.getTitle());
            if (!task.getDescription().isBlank()) target.setDescription(task.getDescription());
            return id;
        }
    }

    @Override
    public Long deleteTask(Long id) {
        if (!taskMap.containsKey(id)) {
            return -1L;
        }
        ITask remove = taskMap.remove(id);
        return remove.getId();
    }

    @Override
    public boolean linkTask(HttpLinkRequest request) {
        if (!taskMap.containsKey(request.getParentId())) {
            System.out.println("Не найдена задача. id=" + request.getParentId());
            return false;
        }
        if (!taskMap.containsKey(request.getChildId())) {
            System.out.println("Не найдена задача. id=" + request.getChildId());
            return false;
        }
        ITask parentTask = taskMap.get(request.getParentId());
        ITask childTask = taskMap.get(request.getChildId());

        childTask.setParentId(request.getParentId());
        parentTask.getChildIds().add(request.getChildId());

        return true;
    }

}
