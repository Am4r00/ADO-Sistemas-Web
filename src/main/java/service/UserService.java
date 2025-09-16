package service;

import DTO.SingUpUser;
import entity.User;

import java.util.List;

public interface UserService {

    void saveUser(SingUpUser user);
    SingUpUser updateUser(long id,User user);
    List<SingUpUser> findAll();
    void deleteUser(long id);
}
