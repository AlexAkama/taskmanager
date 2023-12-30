package com.example.service;

import com.example.http.HttpLinkRequest;
import com.example.model.ITask;
import com.example.model.ITaskManager;
import com.example.storage.ITaskStorage;
import com.example.storage.impl.TaskStorage;

import java.util.List;

public class TaskManagerService implements ITaskManager {

    private final ITaskStorage storage = new TaskStorage();

    @Override
    public ITask getTask(Long id) {
        return storage.read(id);
    }

    @Override
    public List<ITask> getParentTasks() {
        return storage.readUpLevel();
    }

    @Override
    public List<ITask> getAllTasks() {
        return storage.readAll();
    }

    @Override
    public ITask addTask(ITask task) {
        return storage.create(task);
    }

    @Override
    public ITask updateTask(ITask task) {
        if (task.getId() == null && task.getId() < 0) return null;
        return storage.update(task);
    }

    @Override
    public ITask deleteTask(Long id) {
        return storage.delete(id);
    }

    @Override
    public boolean addLink(HttpLinkRequest request) {
        ITask parentTask = storage.read(request.getParentId());
        ITask childTask = storage.read(request.getChildId());
        if (parentTask == null) {
            System.out.println("Не найдена задача. id=" + request.getParentId());
            return false;
        }
        if (childTask == null) {
            System.out.println("Не найдена задача. id=" + request.getChildId());
            return false;
        }


        return true;
    }

}
