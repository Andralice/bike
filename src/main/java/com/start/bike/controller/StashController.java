package com.start.bike.controller;

import com.start.bike.context.ThreadLocalContext;
import com.start.bike.entity.Page;
import com.start.bike.entity.Product;
import com.start.bike.entity.Stash;
import com.start.bike.service.StashService;
import com.start.bike.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Stash")
public class StashController {
    @Autowired
    private StashService stashService ;

    @Autowired
    private LogUtil logUtil;

    @PostMapping("/selectStashById/{stashId}")
    public ResponseEntity<Map<String, Object>> selectStashById(@PathVariable  Integer stashId) {
        Map<String, Object> body = new HashMap<>();
        try {
            Stash result = stashService.selectStashById(stashId);
            if (result != null) {
                body.put("success", "true");
                body.put("message", "查询成功");
                body.put("result", result);
                return ResponseEntity.ok(body);
            } else {
                body.put("success", "false");
                body.put("message", "未找到对应的仓库");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库查询失败，请稍后重试");
            body.put("error",e.getMessage() );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/selectAllStash")
    public ResponseEntity<Map<String, Object>> selectAllStash(
            @RequestBody Stash stash){
        Map<String,Object> body = new HashMap<>();
        try {
            // 定义 result 变量，确保其作用域覆盖整个方法
            List<Stash> result;
            // 根据 stash 是否为空调用不同的服务方法
            if (stash == null) {
                result = stashService.selectAllStash(); // 查询所有仓库
            } else {
                result = stashService.selectAllStash(stash); // 根据条件查询仓库
            }


            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库查询失败，请稍后重试");
            body.put("error",e.getMessage() );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/createStash")
    public ResponseEntity<Map<String, Object>>  insertStash(
            @RequestBody Stash stash,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser){
        Map<String,Object> body = new HashMap<>();
        try {
            if (stashService.isStashExist(stash)){
                body.put("success", "false");
                body.put("message","仓库已存在");
                return ResponseEntity.ok(body);
            }
            stashService.insertStash(stash);
            // 返回新建仓库信息
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            // 获取创建数据
            Stash updateData = stashService.selectStashCreate(stash);
            // 记录操作日志
            logUtil.logOperation("createStash","0", executedSql, updateData, operatorUser);
            body.put("success", "true");
            body.put("message", "仓库创建成功");
            body.put("result", updateData);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库创建失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/updateStash")
    public ResponseEntity<Map<String,Object>> updateStash(
            @RequestBody Stash stash,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser){
        Map<String,Object> body = new HashMap<>();
        try {
            Stash hisData = stashService.selectStashById(stash.getStashId());
            stashService.updateStash(stash);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            // 返回更新后的仓库信息
            Stash updateData = stashService.selectStashById(stash.getStashId());
            // 记录操作日志
            logUtil.logOperation("updateStash",hisData, executedSql, updateData, operatorUser);

            body.put("success", "true");
            body.put("message", "仓库更新成功");
            body.put("result", updateData);
            return ResponseEntity.ok(body);
        }
        catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库更新失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/deleteStashById/{stashId}")
    public ResponseEntity<Map<String,Object>> deleteStash(
            @PathVariable  Integer stashId,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser){
        Map<String,Object> body = new HashMap<>();
        try {
            Stash hisData = stashService.selectStashById(stashId);
            boolean delete = stashService.deleteStashById(stashId);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            // 记录操作日志
            logUtil.logOperation("delStash",hisData, executedSql, "0", operatorUser);
            if(!delete){
                body.put("success", "false");
                body.put("message", "仓库删除失败");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
            body.put("success", "true");
            body.put("message", "仓库删除成功");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库删除失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

}
