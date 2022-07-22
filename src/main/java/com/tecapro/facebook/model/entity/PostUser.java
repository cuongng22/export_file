package com.tecapro.facebook.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreated;

    private String content;

    private String status;

    @ManyToOne
    private User user;

    public PostUser(Date dateCreated, String content, String status, User user) {
        this.dateCreated = dateCreated;
        this.content = content;
        this.status = status;
        this.user = user;
    }
}
