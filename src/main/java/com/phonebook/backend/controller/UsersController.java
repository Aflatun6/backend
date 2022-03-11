package com.phonebook.backend.controller;

import com.phonebook.backend.model.UserEntity;
import com.phonebook.backend.model.UserOperation;
import com.phonebook.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/status")
    public ResponseEntity<List<UserEntity>> status() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<UserEntity>> listUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/user/add")
    public ResponseEntity<UserOperation> addUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.postUser(user));
    }

    @PutMapping("/user/edit")
    public ResponseEntity<UserOperation> editUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.editUser(user));
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<UserOperation> deleteUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.deleteUser(user));
    }


}
