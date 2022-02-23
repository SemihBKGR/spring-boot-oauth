package com.semihbkgr.springboot.oauth.resource;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("items")
public class Item {

    @Id
    private String id;
    private String name;
    private String owner;

}
