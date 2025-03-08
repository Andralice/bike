package com.start.bike.controller;

import com.start.bike.entity.Stash;
import com.start.bike.service.StashService;
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
    public ResponseEntity<Map<String, Object>> selectAllStash(@RequestBody int page, int size){
        Map<String,Object> body = new HashMap<>();
        try {
            List<Stash> result = stashService.selectAllStash(page,size);
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
    public ResponseEntity<Map<String, Object>>  insertStash(@RequestBody Stash stash){
        Map<String,Object> body = new HashMap<>();
        try {
            if (stashService.isStashExist(stash)){
                body.put("success", "false");
                body.put("message","仓库已存在");
                return ResponseEntity.ok(body);
            }
            stashService.insertStash(stash);
            // 返回新建仓库信息
            Stash result = stashService.selectStashById(stash.getStashId());
            body.put("success", "true");
            body.put("message", "仓库创建成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库创建失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/updateStash")
    public ResponseEntity<Map<String,Object>> updateStash(@RequestBody Stash stash){
        Map<String,Object> body = new HashMap<>();
        try {
            stashService.updateStash(stash);
            // 返回更新后的仓库信息
            Stash result = stashService.selectStashById(stash.getStashId());
            body.put("success", "true");
            body.put("message", "仓库更新成功");
            body.put("result", result);
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
    public ResponseEntity<Map<String,Object>> deleteStash(@PathVariable  Integer stashId){
        Map<String,Object> body = new HashMap<>();
        try {
            boolean delete = stashService.deleteStashById(stashId);
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
