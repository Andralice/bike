package com.start.bike.service.impl;

import com.start.bike.entity.Stash;
import com.start.bike.service.StashService;
import com.start.bike.mapper.StashMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StashServiceImpl implements StashService {

    @Autowired
    private StashMapper stashMapper;
    @Override
    public Stash selectStash(Stash stash) {
        return stashMapper.selectStash(stash);
    }

    @Override
    public Stash insertStash(Stash stash) {
        return stashMapper.insertStash(stash);
    }

    @Override
    public Stash updateStash(Stash stash) {
        return stashMapper.updateStash(stash);
    }

    @Override
    public Stash deleteStash(Stash stash) {

        return stashMapper.deleteStash(stash);
    }
}
