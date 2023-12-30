package com.example.storage.impl;

import com.example.model.ITask;
import com.example.storage.ITaskStorage;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskStorage implements ITaskStorage {

    private final Comparator<ITask> sortComparator =
            (Comparator.comparing(ITask::getCreateDate).thenComparing(ITask::getDeadLine));

    private final Map<Long, ITask> taskMap = new HashMap<>();
    private Long taskId = 0L;

    @Override
    public ITask create(ITask task) {
        task.setId(taskId);
        taskMap.put(taskId, task);
        taskId++;
        return task;
    }

    @Override
    public ITask read(Long id) {
        return taskMap.get(id);
    }

    @Override
    public List<ITask> readAll() {
        return taskMap.values().stream()
                .sorted(sortComparator)
                .toList();
    }

    @Override
    public ITask update(ITask task) {
        ITask target = taskMap.get(task.getId());
        if (target == null) return null;
        update(target, task);
        return target;
    }

    @Override
    public ITask delete(Long id) {
        return taskMap.remove(id);
    }

    @Override
    public List<ITask> readUpLevel() {
        return taskMap.values().stream()
                .filter(task -> task.getParentId() == null)
                .sorted(sortComparator)
                .toList();
    }

    private void update(ITask target, ITask task) {
        //TODO
    }

}
