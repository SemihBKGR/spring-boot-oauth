package com.semihbkgr.springboot.oauth.resource;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface ItemRepository extends MongoRepository<Item, String> {

    Set<Item> findAllByOwner(String owner);

}
