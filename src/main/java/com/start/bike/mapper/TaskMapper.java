package com.start.bike.mapper;

import com.start.bike.entity.Task;

import java.util.List;

public interface TaskMapper {
    Task selectTaskById(int taskId);
    Task selectTaskCreate(Task task);
    List<Task> selectAllTask(Task task);
    List<Task> selectAllTask();
    void insertTask(Task task);
    void updateTask(Task task);
    int deleteTaskById(int taskId);
}
