package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.exception.UserNotFound;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Log4j
@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping(URLs.REGISTRATION)
    public ResponseEntity<?> registration(@RequestBody User user) throws IOException {
        try {
            userService.registration(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFound userNotFound) {
            userNotFound.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(URLs.UPDATE_PROFILE)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateProfile(user);
        return Views.PROFILE;
    }

}

