package com.example.model;

import com.example.http.HttpLinkRequest;

import java.util.List;

public interface ITaskManager {

    ITask getTask(Long id);

    List<ITask> getParentTasks();

    List<ITask> getAllTasks();

    Long addTask(ITask task);

    Long updateTask(ITask task);

    Long deleteTask(Long id);

    boolean linkTask(HttpLinkRequest request);


}
