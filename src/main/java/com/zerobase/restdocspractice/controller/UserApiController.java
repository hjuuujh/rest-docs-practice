package com.zerobase.restdocspractice.controller;

import com.zerobase.restdocspractice.model.Header;
import com.zerobase.restdocspractice.model.User;
import com.zerobase.restdocspractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping
    public Header<User> create(@RequestBody User requestUser){
        User user = userService.create(requestUser);
        if(user!=null){
            return Header.OK(user);
        }
        return Header.ERROR();
    }

    @GetMapping("/{id}")
    public Header<User> read(@PathVariable Long id){
        User user = userService.read(id);
        if(user!=null){
            return Header.OK(user);
        }
        return Header.ERROR();
    }

    @PutMapping
    public Header<User> update(@RequestBody User requestUser){
        User user = userService.update(requestUser);
        if(user!=null){
            return Header.OK(user);
        }
        return Header.ERROR();
    }

    @DeleteMapping("/{id}")
    public Header<User> create(@PathVariable Long id){
        boolean isDelete = userService.delete(id);
        if(isDelete){
            return Header.OK();
        }
        return Header.ERROR();
    }
}
