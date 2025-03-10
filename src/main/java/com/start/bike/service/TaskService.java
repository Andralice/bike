package com.start.bike.service;

import com.start.bike.entity.Task;

import java.util.List;

public interface TaskService {
    Task selectTaskById(int taskId);
    Task selectTaskCreate(Task task);
    List<Task> selectAllTask(int page, int size);
    void insertTask(Task task);
    void updateTask(Task task);
    Boolean deleteTaskById(int taskId);
}
