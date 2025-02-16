package com.start.bike.controller;

import com.start.bike.entity.Stash;
import com.start.bike.service.StashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Stash")
public class StashController {
    @Autowired
    private StashService stashService ;

    @RequestMapping("/selectStash")
    public ResponseEntity<?> stash(@RequestBody Stash stash){
        try {
            stashService.selectStash(stash);
            return ResponseEntity.badRequest().body("selectStash successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/insertStash")
    public ResponseEntity<?> insertStash(@RequestBody Stash stash){
        try {
            stashService.insertStash(stash);
            return ResponseEntity.badRequest().body("insertStash successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/updateStash")
    public ResponseEntity<?> updateStash(@RequestBody Stash stash){
        try {
            stashService.updateStash(stash);
            return ResponseEntity.badRequest().body("updateStash successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/deleteStash")
    public ResponseEntity<?> deleteStash(@RequestBody Stash stash){
        try {
            stashService.deleteStash(stash);
            return ResponseEntity.badRequest().body("deleteStash successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
