package com.start.bike.mapper;
import com.start.bike.entity.Stash;

import java.util.List;

public interface StashMapper {
    Stash selectStashById(Integer stashId);
    Stash selectStashCreate(Stash stash );
    List<Stash> selectAllStash(Stash stash);
    List<Stash> selectAllStash();
    void insertStash(Stash stash);
    Boolean isStashExist(Stash stash);
    void updateStash(Stash stash);
    int deleteStashById(Integer stashId);
}
