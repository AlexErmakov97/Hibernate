package dao;

import java.util.List;

import entity.User;

public interface UserDao {
    List<User> getAll() ;
    User getById(int id);
    void insert(User user);
    void update(User user);
    void delete(User user);
}
