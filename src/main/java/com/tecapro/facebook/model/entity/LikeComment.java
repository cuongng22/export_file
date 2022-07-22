package com.tecapro.facebook.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CommentPostUser commentPostUser;

    @ManyToOne
    private User LikerComment;

    public LikeComment(CommentPostUser commentPostUser, User likerComment) {
        this.commentPostUser = commentPostUser;
        LikerComment = likerComment;
    }
}
