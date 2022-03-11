package com.phonebook.backend.service;

import com.phonebook.backend.model.UserEntity;
import com.phonebook.backend.model.UserOperation;
import com.phonebook.backend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserOperation postUser(UserEntity userEntity) {

        try {
            UserEntity user = userRepository.save(userEntity);
            return UserOperation.builder().user_id(user.getUser_id()).operation_type("success")
                    .operation_status("add").build();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return UserOperation.builder().user_id(userEntity.getUser_id()).operation_type("fail-" + e.getClass())
                    .operation_status("add").build();
        }
    }

    public UserOperation editUser(UserEntity userEntity) {
        if (userEntity.getUser_id() == null) {
            return UserOperation.builder().operation_status("fail").operation_type("edit")
                    .user_id(userEntity.getUser_id()).build();
        }
        try {
            Optional<UserEntity> user = userRepository.findById(userEntity.getUser_id());
            if (user.isPresent()) {
                userRepository.save(userEntity);
                return UserOperation.builder().operation_status("success").operation_type("edit")
                        .user_id(userEntity.getUser_id()).build();
            } else {
                return UserOperation.builder().operation_status("fail-user does not exist").operation_type("edit")
                        .user_id(userEntity.getUser_id()).build();

            }
        } catch (Exception ex) {
            return UserOperation.builder().operation_status("fail-" + ex.getClass()).operation_type("edit")
                    .user_id(userEntity.getUser_id()).build();

        }

    }

    public UserOperation deleteUser(UserEntity user) {
        try {
            if (user.getUser_id() != null) {
                UserEntity userEntity = userRepository.findById(user.getUser_id()).get();
                userRepository.delete(userEntity);
            } else {
                return UserOperation.builder().operation_status("fail-user id is null").operation_type("delete")
                        .user_id(user.getUser_id()).build();
            }
            return UserOperation.builder().operation_status("success").operation_type("delete")
                    .user_id(user.getUser_id()).build();
        } catch (Exception e) {
            return UserOperation.builder().operation_status("fail-" + e.getClass()).operation_type("delete")
                    .user_id(user.getUser_id()).build();
        }
    }

    private List<UserEntity> listUsers() {
        return Arrays.asList(UserEntity.builder().name("test").phone("testphone").user_id("testId").build());
    }

    private UserOperation returnOperation() {
        return UserOperation.builder().operation_status("success").operation_type("add").user_id("newId").build();
    }
}
