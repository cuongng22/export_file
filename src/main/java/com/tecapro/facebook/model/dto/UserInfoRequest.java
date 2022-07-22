package com.tecapro.facebook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {
    private Long id;

    private String name;

    private String dateOfBirth;

    private String address;

    private String phoneNumber;

    private String email;

    private MultipartFile avatar;

    private MultipartFile background;

    public UserInfoRequest(String name, String dateOfBirth, String address, String phoneNumber, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UserInfoRequest(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
