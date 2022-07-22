package com.tecapro.facebook.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateWrite;

    private String content;

    @ManyToOne
    private CommentPostUser commentPostUser;

    @ManyToOne
    private User writer;

    public ReplyComment(Date dateWrite, String content, CommentPostUser commentPostUser, User writer) {
        this.dateWrite = dateWrite;
        this.content = content;
        this.commentPostUser = commentPostUser;
        this.writer = writer;
    }
}
