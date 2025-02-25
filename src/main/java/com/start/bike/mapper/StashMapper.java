package com.start.bike.mapper;
import com.start.bike.entity.Stash;

public interface StashMapper {
    Stash selectStash(Stash stash);
    Stash selectStashById(Stash stash);
    void insertStash(Stash stash);
    Boolean isStashExist(Stash stash);
    void updateStash(Stash stash);
    int deleteStash(Stash stash);
}
