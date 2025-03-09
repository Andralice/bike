package com.start.bike.controller;


import com.start.bike.entity.Page;
import com.start.bike.entity.Task;
import com.start.bike.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/selectTaskById/{taskId}")
    public ResponseEntity<Map<String, Object>> selectTaskById(@PathVariable int taskId) {
        Map<String, Object> body = new HashMap<>();
        try {
            Task result = taskService.selectTaskById(taskId);
            if (result == null) {
                body.put("success", "false");
                body.put("message", "任务不存在");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        }catch (Exception e) {
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/selectAllTask")
    public ResponseEntity<Map<String, Object>> selectAllTask(@RequestBody Page data) {
        Map<String, Object> body = new HashMap<>();
        int page = data.getPage();
        int size = data.getSize();
        try {
            List<Task> result = taskService.selectAllTask(page, size);
            body.put("success", "true");
            body.put("result", result);
            return ResponseEntity.ok(body);
        }catch (Exception e) {
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/createTask")
    public ResponseEntity<Map<String, Object>> createTask(@RequestBody Task task) {
        Map<String, Object> body = new HashMap<>();
        try {
            taskService.insertTask(task);
            body.put("success", "true");
            body.put("message","任务创建成功");
            return ResponseEntity.ok(body);
        }catch (Exception e) {
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/updateTask")
    public ResponseEntity<Map<String, Object>> updateTask(@RequestBody Task tasks) {
        Map<String, Object> body = new HashMap<>();
        try {
            Task task = taskService.selectTaskById(tasks.getTaskId());
            if (task == null) {
                body.put("success", "false");
                body.put("message", "任务不存在");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            taskService.updateTask(tasks);
            Task result = taskService.selectTaskById(tasks.getTaskId());
            body.put("success", "true");
            body.put("result", result);
            return ResponseEntity.ok(body);
        }catch (Exception e){
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/deleteTaskById/{taskId}")
    public ResponseEntity<Map<String, Object>> deleteTaskById(@PathVariable int taskId){
        Map<String, Object> body = new HashMap<>();
        try {
            Task result = taskService.selectTaskById(taskId);
            if (result == null){
                body.put("success", "false");
                body.put("message", "任务不存在");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            taskService.deleteTaskById(taskId);
            body.put("success", "true");
            body.put("message", "任务删除成功");
            return ResponseEntity.ok(body);
        }catch (Exception e){
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}
