package service;

import DTO.SingUpUser;
import entity.User;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserImplementeService implements UserService{

    private final AssertTrueValidator assertTrueValidator;
    private List<User> listaUsers = new ArrayList<>();

    public UserImplementeService(AssertTrueValidator assertTrueValidator) {
        this.assertTrueValidator = assertTrueValidator;
    }

    @Override
    public void saveUser(SingUpUser user) {
        User user1 = new User();
        user1.setNome(user.getName());
        user1.setCpf(user.getCpf());
        listaUsers.add(user1);
    }

    @Override
    public boolean updateUser(String id, User user) {
        boolean aux = false;
        for(int i=0; i<=listaUsers.size(); i++){
            if(listaUsers.get(i).getId().equals(user.getId())){
                listaUsers.add(i, user);
                aux = true;
                return aux;
            }
        }
        return aux;
    }

    @Override
    public List<User> findAll() {
       return listaUsers;
    }

    @Override
    public boolean deleteUser(String id) {
        boolean aux = false;
        for(int i=0; i<=listaUsers.size(); i++){
            if(listaUsers.get(i).getId().equals(id)){
                listaUsers.remove(i);
                aux = true;
                return aux;
            }
        }
        return aux;
    }
}
