package service;

import DTO.SingUpUser;
import entity.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserImplementeService implements UserService{

    private final AtomicLong seq = new AtomicLong(1);
    private Set<User> listaUsers = new HashSet<>() {
    }
    @Override
    public void saveUser(SingUpUser user) {
        User user1 = new User();
        user1.setNome(user.getName());
        user1.setCpf(user.getCpf());
        user1.setId(seq.getAndIncrement());
        listaUsers.add(user1);
    }

    @Override
    public void updateUser(long id, User user) {

    }

    @Override
    public void findAll() {

    }

    @Override
    public void deleteUser(long id) {

    }
}
