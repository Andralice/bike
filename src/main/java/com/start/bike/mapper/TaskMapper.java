package com.start.bike.mapper;

import com.start.bike.entity.Task;

import java.util.List;

public interface TaskMapper {
    Task selectTaskById(int taskId);
    List<Task> selectAllTask(int page, int size);
    void insertTask(Task task);
    void updateTask(Task task);
    int deleteTaskById(int taskId);
}
