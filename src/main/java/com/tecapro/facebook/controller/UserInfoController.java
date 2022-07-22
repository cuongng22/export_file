package com.tecapro.facebook.controller;

import com.tecapro.facebook.model.dto.UserInfoRequest;
import com.tecapro.facebook.model.entity.UserInfo;
import com.tecapro.facebook.service.export.ExportUserInfo;
import com.tecapro.facebook.service.user_info.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user-info")
public class UserInfoController {
    @Autowired
    private UserInfoService service;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfo> findById(@PathVariable Long userId) {
        Optional<UserInfo> userInfo = service.findById(userId);
        if (!userInfo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userInfo.get(), HttpStatus.OK);
    }

    @PostMapping("/set-avatar/{userId}")
    public ResponseEntity<UserInfo> setAvatar(@PathVariable Long userId, @ModelAttribute UserInfoRequest userInfoRequest){
        Optional<UserInfo> userInfo = service.findById(userId);
        if (!userInfo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile multipartFile = userInfoRequest.getAvatar();
        String avatar = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + avatar));
        } catch (IOException e) {
            e.printStackTrace();
        }
        userInfo.get().setAvatar(avatar);
        return new ResponseEntity<>( service.save(userInfo.get()), HttpStatus.OK);
    }

    @PostMapping("/set-background/{userId}")
    public ResponseEntity<UserInfo> setBackground(@PathVariable Long userId, @ModelAttribute UserInfoRequest userInfoRequest){
        Optional<UserInfo> userInfo = service.findById(userId);
        if (!userInfo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile multipartFile = userInfoRequest.getBackground();
        String backGround = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + backGround));
        } catch (IOException e) {
            e.printStackTrace();
        }
        userInfo.get().setBackground(backGround);
        return new ResponseEntity<>( service.save(userInfo.get()), HttpStatus.OK);
    }

    @PostMapping("/set-profile/{userId}")
    public ResponseEntity<UserInfo> updateProfile(@PathVariable Long userId, @RequestBody UserInfoRequest userInfoRequest) {
        Optional<UserInfo> userInfo = service.findById(userId);
        if (!userInfo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserInfo newUserInfo = userInfo.get();
        newUserInfo.setName(userInfoRequest.getName());
        newUserInfo.setAddress(userInfoRequest.getAddress());
        newUserInfo.setEmail(userInfoRequest.getEmail());
        newUserInfo.setDateOfBirth(userInfoRequest.getDateOfBirth());
        newUserInfo.setPhoneNumber(userInfoRequest.getPhoneNumber());
        service.save(newUserInfo);
        return new ResponseEntity<>(newUserInfo, HttpStatus.OK);
    }

    @GetMapping("/export-list")
    public void exportToExcel(HttpServletResponse response, @RequestParam(name = "q") Optional<String> q) throws IOException {
        List<UserInfo> listUsers;
        if (!q.isPresent()) {
            listUsers = service.findAll();
        } else {
            listUsers = service.findByEmail(q.get());
        }
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=userInfo_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);


        ExportUserInfo excelExporter = new ExportUserInfo(listUsers);

        excelExporter.export(response);
    }



}
