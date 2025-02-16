package com.start.bike.mapper;
import com.start.bike.entity.Stash;

public interface StashMapper {
    Stash selectStash(Stash stash);
    Stash insertStash(Stash stash);
    Stash updateStash(Stash stash);
    Stash deleteStash(Stash stash);
}
