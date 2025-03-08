package com.start.bike.service;

import com.start.bike.entity.Stash;

import java.util.List;


public interface StashService {
    Stash selectStash(Stash stash);
    Stash selectStashById(Integer stashId);
    List<Stash> selectAllStash(Stash stash);
    void insertStash(Stash stash);
    Boolean isStashExist(Stash stash);
    void updateStash(Stash stash);
    Boolean deleteStashById(Integer inventoryId);
}
