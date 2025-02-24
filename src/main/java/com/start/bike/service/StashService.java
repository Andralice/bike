package com.start.bike.service;

import com.start.bike.entity.Stash;


public interface StashService {
    Stash selectStash(Stash stash);
    Stash insertStash(Stash stash);
    Stash updateStash(Stash stash);
    Boolean deleteStash(Stash stash);
}
