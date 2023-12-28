package com.example.model.impl;

import com.example.model.ITaskType;

public enum TaskType implements ITaskType {

    NEW,
    REJECTED,
    IN_WORK,
    COMPLETE;

    @Override
    public ITaskType getTaskType() {
        return null;
    }

}
