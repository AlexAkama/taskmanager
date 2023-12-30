package com.example.model;

import com.example.http.HttpLinkRequest;

import java.util.List;

public interface ITaskManager {

    ITask getTask(Long id);

    List<ITask> getParentTasks();

    List<ITask> getAllTasks();

    ITask addTask(ITask task);

    ITask updateTask(ITask task);

    ITask deleteTask(Long id);

    boolean addLink(HttpLinkRequest request);


}
