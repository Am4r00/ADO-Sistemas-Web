package service;

import dto.SingUpUser;
import entity.User;

import java.util.List;

public interface UserService {

    void saveUser(SingUpUser user);
    boolean updateUser(String id,User user);
    List<User> findAll();
    boolean deleteUser(String id);
}
