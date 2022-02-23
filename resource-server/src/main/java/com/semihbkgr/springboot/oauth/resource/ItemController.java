package com.semihbkgr.springboot.oauth.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository repository;

    @GetMapping
    public Set<Item> items(Authentication authentication) {
        return repository.findAllByOwner(authentication.getName());
    }

    @PostMapping
    public Set<Item> save(@RequestBody Item item, Authentication authentication) {
        item.setId(UUID.randomUUID().toString());
        item.setOwner(authentication.getName());
        repository.save(item);
        return repository.findAllByOwner(authentication.getName());
    }

}
