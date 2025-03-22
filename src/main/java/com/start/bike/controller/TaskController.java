package com.start.bike.controller;


import com.start.bike.context.ThreadLocalContext;
import com.start.bike.entity.Page;
import com.start.bike.entity.Task;
import com.start.bike.service.TaskService;
import com.start.bike.util.LogUtil;
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

    @Autowired
    private LogUtil logUtil;

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
    public ResponseEntity<Map<String, Object>> createTask(
            @RequestBody Task task,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            taskService.insertTask(task);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();

            Task updateData = taskService.selectTaskCreate(task);
            // 记录操作日志
            logUtil.logOperation("createTask","0", executedSql, updateData, operatorUser);

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
    public ResponseEntity<Map<String, Object>> updateTask(
            @RequestBody Task task,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            Task hisData = taskService.selectTaskById(task.getTaskId());
            taskService.updateTask(task);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();

            Task updateData = taskService.selectTaskById(task.getTaskId());
            // 记录操作日志
            logUtil.logOperation("updateTask",hisData, executedSql, updateData, operatorUser);

            body.put("success", "true");
            body.put("result", updateData);
            return ResponseEntity.ok(body);
        }catch (Exception e){
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/deleteTaskById/{taskId}")
    public ResponseEntity<Map<String, Object>> deleteTaskById(
            @PathVariable int taskId,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser){
        Map<String, Object> body = new HashMap<>();
        try {
            Task hisData = taskService.selectTaskById(taskId);
            taskService.deleteTaskById(taskId);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            // 记录操作日志
            logUtil.logOperation("delTask",hisData, executedSql, '0', operatorUser);
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
