package com.example.model.impl;

import com.example.model.ITask;
import com.example.model.ITaskType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task implements ITask {

    private Long id;

    private String title;
    private String description;
    private ITaskType taskType;

    private final String createDate = LocalDateTime.now().toString();
    private String deadLine;

    private Long parentId;
    private final Set<Long> childIdSet = new HashSet<>();

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setTaskType(ITaskType type) {
        this.taskType = type;
    }

    @Override
    public ITaskType getTaskType() {
        return taskType;
    }

    @Override
    public String getCreateDate() {
        return createDate;
    }

    @Override
    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String getDeadLine() {
        return deadLine;
    }

    @Override
    public void setParentId(Long id) {
        this.parentId = id;
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public boolean addChildId(Long id) {
        return childIdSet.add(id);
    }

    @Override
    public boolean deleteChildId(Long id) {
        return childIdSet.remove(id);
    }

    @Override
    public List<Long> getChildIds() {
        return childIdSet.stream().toList();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", createDate='" + createDate + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", parentId=" + parentId +
                ", childIdSet=" + childIdSet +
                '}';
    }

}
