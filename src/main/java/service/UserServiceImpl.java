package service;

import dto.SignUpUser;
import entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private List<User> listaUsers = new ArrayList<>();

    @Override
    public void saveUser(SignUpUser user) {
        User user1 = new User(user.getNome(),user.getCpf());
        listaUsers.add(user1);
    }

    @Override
    public boolean updateUser(String id, User user) {
        for(int i=0; i<listaUsers.size(); i++){
            if(listaUsers.get(i).getId().equals(id)){
                User atual = listaUsers.get(i);
                atual.setNome(user.getNome());
                atual.setCpf(user.getCpf());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> copyList = new ArrayList<>();
        for(User c: listaUsers){
            copyList.add(c);
        }
        return copyList;
    }

    @Override
    public boolean deleteUser(String id) {
        for(int i=0; i<listaUsers.size(); i++){
            if(listaUsers.get(i).getId().equals(id)){
                listaUsers.remove(i);
                return true;
            }
        }
        return false;
    }
}
