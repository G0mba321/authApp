package repo;

import entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {

     void save(User user);

     void remove(User user);

     List<User> findAll();

     Optional<User> logInByUserNameAndPassword(String name, String password);

     Optional<User> findByUserName(String name);

}





