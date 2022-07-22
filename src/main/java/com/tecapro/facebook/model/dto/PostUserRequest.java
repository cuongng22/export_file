package com.tecapro.facebook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserRequest {

    private String content;

    private List<MultipartFile> images;

    private String status;
}
