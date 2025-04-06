package com.start.bike.service;

import com.start.bike.entity.Stash;

import java.util.List;


public interface StashService {
    Stash selectStashById(Integer stashId);
    Stash selectStashCreate(Stash stash );
    List<Stash> selectAllStash(Stash stash);
    List<Stash> selectAllStash();
    void insertStash(Stash stash);
    Boolean isStashExist(Stash stash);
    void updateStash(Stash stash);
    Boolean deleteStashById(Integer inventoryId);
}
