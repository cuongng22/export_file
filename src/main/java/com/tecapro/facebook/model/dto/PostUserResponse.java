package com.tecapro.facebook.model.dto;

import com.tecapro.facebook.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserResponse {
    private Long id;

    private Date dateCreated;

    private String content;

    private String status;

    private List<ImagePostUser> imagePostUsers;

    private List<CommentPostUser> commentPostUsers;

    private List<LikePostUser> likePostUsers;

    private List<LikeComment> likeComments;

    private User user;
}
