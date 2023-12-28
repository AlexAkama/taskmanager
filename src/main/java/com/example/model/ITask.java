package com.example.model;

import java.util.List;

public interface ITask {

    void setId(Long id);

    Long getId();

    void setTitle(String title);

    String getTitle();

    void setDescription(String description);

    String getDescription();

    void setTaskType(ITaskType type);

    ITaskType getTaskType();

    String getCreateDate();

    void setDeadLine(String deadLine);

    String getDeadLine();

    void setParentId(Long id);

    Long getParentId();

    boolean addChildId(Long id);

    boolean deleteChildId(Long id);

    List<Long> getChildIds();

}
