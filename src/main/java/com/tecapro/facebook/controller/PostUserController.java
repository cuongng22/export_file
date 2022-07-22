package com.tecapro.facebook.controller;

import com.tecapro.facebook.model.dto.PostUserRequest;
import com.tecapro.facebook.model.dto.PostUserResponse;
import com.tecapro.facebook.model.entity.*;
import com.tecapro.facebook.service.comment.CommentService;
import com.tecapro.facebook.service.image.ImageService;
import com.tecapro.facebook.service.like_comment_user.LikeCmtUserService;
import com.tecapro.facebook.service.like_post_user.LikePostUserService;
import com.tecapro.facebook.service.post_user.PostUserService;
import com.tecapro.facebook.service.reply_comment_user.ReplyCommentService;
import com.tecapro.facebook.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post-user")
@CrossOrigin("*")
public class PostUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostUserService postUserService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeCmtUserService likeCmtUserService;

    @Autowired
    private LikePostUserService likePostUserService;

    @Autowired
    private ReplyCommentService replyCommentService;
    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/post/{userId}")
    public ResponseEntity<List<PostUserResponse>> showAllPostByUser(@PathVariable Long userId) {
        User user = userService.findById(userId).get();
        List<PostUser> postUsers = postUserService.findByUserId(userId);
        List<PostUserResponse> postUserResponses = new ArrayList<>();
        List<ImagePostUser> imagePostUsers = new ArrayList<>();
        List<LikePostUser> likePostUsers = new ArrayList<>();
        List<CommentPostUser> commentPostUsers = new ArrayList<>();
        List<LikeComment> likeComments = new ArrayList<>();
        List<ReplyComment> replyComments = new ArrayList<>();
        for (int i = 0; i < postUsers.size(); i++) {
            imagePostUsers = imageService.findAllByPostId(postUsers.get(i).getId());
            commentPostUsers = commentService.findAllByPostId(postUsers.get(i).getId());
            likePostUsers = likePostUserService.findAllByPostId(postUsers.get(i).getId());
            for (int j = 0; j < commentPostUsers.size(); j++) {
                likeComments.add(likeCmtUserService.findByCommentId(commentPostUsers.get(j).getId()));
            }
            postUserResponses.add(new PostUserResponse(
                    postUsers.get(i).getId(),
                    postUsers.get(i).getDateCreated(),
                    postUsers.get(i).getContent(),
                    postUsers.get(i).getStatus(),
                    imagePostUsers,
                    commentPostUsers,
                    likePostUsers,
                    likeComments,
                    user
            ));
        }
        return new ResponseEntity<>(postUserResponses, HttpStatus.OK);
    }

    @PostMapping("/create-post/{userId}")
    public ResponseEntity<PostUser> createNewPost(@ModelAttribute PostUserRequest postUserRequest, @PathVariable Long userId) {
        User user = userService.findById(userId).get();
        PostUser postUser = new PostUser(
                new Date(),
                postUserRequest.getContent(),
                postUserRequest.getStatus(),
                user
        );
        postUserService.save(postUser);
        List<MultipartFile> multipartFiles = postUserRequest.getImages();
        for (int  i = 0; i < multipartFiles.size(); i++) {
            String image = multipartFiles.get(i).getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFiles.get(i).getBytes(), new File(uploadPath + image));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImagePostUser imagePostUser = new ImagePostUser(
                    image,
                    postUser
            );
            imageService.save(imagePostUser);
        }
        return new ResponseEntity<>(postUser, HttpStatus.OK);
    }

    @GetMapping("/delete-post/{userId}/{postId}")
    public ResponseEntity<PostUser> deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        User user = userService.findById(userId).get();
        PostUser postUser = postUserService.findById(postId).get();
        if (!postUserService.findById(postId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (postUser.getUser().getId() != userId) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        imageService.deleteByPost(postId);
        commentService.deleteByPost(postId);
        likePostUserService.deleteByPost(postId);
        postUserService.remove(postId);
        return new ResponseEntity<>(postUser, HttpStatus.OK);
    }

}
