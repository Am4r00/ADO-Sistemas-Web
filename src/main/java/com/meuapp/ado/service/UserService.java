package com.meuapp.ado.service;

import com.meuapp.ado.dto.SignUpUser;
import com.meuapp.ado.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(SignUpUser user);
    boolean updateUser(String id,User user);
    List<User> findAll();
    boolean deleteUser(String id);
    User findById(String id);
}
