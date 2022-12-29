package spring_boot.service;

import spring_boot.model.User;

import java.util.List;

public interface UserService {

    List<User> getListUsers();

    void addUser(User user);
    void deleteUser(int id);
    void updateUser(int id, User user);
    Object getUser(int id);
    public User findByUsername(String username);

}
