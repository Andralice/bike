package com.start.bike.mapper;

import com.start.bike.entity.Task;

import java.util.List;

public interface TaskMapper {
    Task selectTaskById(int taskId);
    Task selectTaskCreate(Task task);
    List<Task> selectAllTask(int page, int size);
    List<Task> selectTask(Task task);
    void insertTask(Task task);
    void updateTask(Task task);
    int deleteTaskById(int taskId);
}
