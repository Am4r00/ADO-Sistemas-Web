package service;

import entity.User;

public interface UserService {

    void saveUser(User user);
    void updateUser(long id,User user);
    void findAll();
    void deleteUser(long id);
}
