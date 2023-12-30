package com.example.storage;

import com.example.model.ITask;

import java.util.List;

public interface ITaskStorage extends IStorage<ITask> {

    List<ITask> readUpLevel();

}
