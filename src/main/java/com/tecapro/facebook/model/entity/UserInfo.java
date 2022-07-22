package com.tecapro.facebook.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String dateOfBirth;

    private String address;

    private String phoneNumber;

    private String email;

    private String avatar;

    private String background;

    @OneToOne
    private User user;

    public UserInfo(String name, String dateOfBirth, String address, String phoneNumber, String email, User user) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.user = user;
    }

    public UserInfo(String name, String dateOfBirth, String address, String phoneNumber, String email, String avatar, String background, User user) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatar = avatar;
        this.background = background;
        this.user = user;
    }
}
