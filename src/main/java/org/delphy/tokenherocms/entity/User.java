package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;
    private String openId;
    private String avatar;
    private String gender;
    private Double dpy;
    private Double totalDpy;
    private Long victories;
    private String phone;
    private Long create;
    private Long delete;
}
