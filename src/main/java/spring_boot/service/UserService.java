package spring_boot.service;

import spring_boot.model.User;

import java.util.List;

public interface UserService {

    List<User> getListUsers();

    void addUser(User user);
    void deleteUser(int id);
    User getUser(int id);
    User findByEmail(String email);
    void updateUser(User user);

}
