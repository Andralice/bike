package com.start.bike.service.impl;

import com.start.bike.entity.Task;
import com.start.bike.service.TaskService;
import com.start.bike.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Task selectTaskById(int taskId) {
        return taskMapper.selectTaskById(taskId);
    }
    @Override
    public Task selectTaskCreate(Task task ) {
        return taskMapper.selectTaskCreate(task);
    }

    @Override
    public List<Task> selectAllTask(Task task) {
        return taskMapper.selectAllTask(task);
    }
    @Override
    public List<Task> selectAllTask() {
        return taskMapper.selectAllTask();
    }

    @Override
    public void insertTask(Task task) {
        taskMapper.insertTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    @Override
    public Boolean deleteTaskById(int taskId) {
        int result = taskMapper.deleteTaskById(taskId);
        return result > 0;
    }
}
