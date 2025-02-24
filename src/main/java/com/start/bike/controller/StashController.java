package com.start.bike.controller;

import com.start.bike.entity.Stash;
import com.start.bike.service.StashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Stash")
public class StashController {
    @Autowired
    private StashService stashService ;

    @RequestMapping("/selectStash")
    public ResponseEntity<Map<String, Object>> stash(@RequestBody Stash stash){
        Map<String,Object> body = new HashMap<>();
        try {
            Stash result = stashService.selectStash(stash);
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库查询失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/insertStash")
    public ResponseEntity<Map<String, Object>>  insertStash(@RequestBody Stash stash){
        Map<String,Object> body = new HashMap<>();
        try {
            Stash result = stashService.insertStash(stash);
            body.put("success", "true");
            body.put("message", "仓库创建成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库创建失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/updateStash")
    public ResponseEntity<Map<String,Object>> updateStash(@RequestBody Stash stash){
        Map<String,Object> body = new HashMap<>();
        try {
            Stash result = stashService.updateStash(stash);
            body.put("success", "true");
            body.put("message", "仓库更新成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        }
        catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库更新失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/deleteStash")
    public ResponseEntity<Map<String,Object>> deleteStash(@RequestBody Stash stash){
        Map<String,Object> body = new HashMap<>();
        try {
            boolean delete = stashService.deleteStash(stash);
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

}
