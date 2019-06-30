package com.example.oauthresource.controller;

import com.starter.user.entity.Role;
import com.starter.user.entity.RoleSecured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/read")
//    @PreAuthorize("hasRole('ROLE_READ')")
    @RoleSecured(Role.ROLE_READ)
    public String helloJwt() {

        return "hello jwt!";
    }

    @GetMapping("/write")
//    @PreAuthorize("hasRole('ROLE_WRITE')")
    @RoleSecured(Role.ROLE_WRITE)
    public String helloJwtWrite() {

        return "Write secured hello jwt!";
    }

}