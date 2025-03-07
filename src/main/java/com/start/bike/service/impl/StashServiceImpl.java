package com.start.bike.service.impl;

import com.start.bike.entity.Stash;
import com.start.bike.service.StashService;
import com.start.bike.mapper.StashMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StashServiceImpl implements StashService {

    @Autowired
    private StashMapper stashMapper;
    @Override
    public Stash selectStash(Stash stash) {
        return stashMapper.selectStash(stash);
    }

    @Override
    public Stash selectStashById(Stash stash) {
        return stashMapper.selectStashById(stash);
    }

    @Override
    public List<Stash> selectAllStash(Stash stash) {
        return stashMapper.selectAllStash(stash);
    }

    @Override
    public Boolean isStashExist(Stash stash) {
        return stashMapper.isStashExist(stash);
    }

    @Override
    public void insertStash(Stash stash) {
        stashMapper.insertStash(stash);
    }

    @Override
    public void updateStash(Stash stash) {
        stashMapper.updateStash(stash);
    }

    @Override
    public Boolean deleteStash(Stash stash) {
        int result = stashMapper.deleteStash(stash);
        return result > 0;
    }
}
