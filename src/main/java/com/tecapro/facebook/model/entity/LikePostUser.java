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
public class LikePostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User liker;

    @ManyToOne
    private PostUser postUser;

    public LikePostUser(User liker, PostUser postUser) {
        this.liker = liker;
        this.postUser = postUser;
    }
}
