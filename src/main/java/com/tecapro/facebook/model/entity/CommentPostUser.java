package com.tecapro.facebook.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date dateWrite;

    @ManyToOne
    private User writer;

    @ManyToOne
    private PostUser postUser;

    public CommentPostUser(String content, Date dateWrite, User writer, PostUser postUser) {
        this.content = content;
        this.dateWrite = dateWrite;
        this.writer = writer;
        this.postUser = postUser;
    }
}
