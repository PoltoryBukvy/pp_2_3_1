package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public User save(User user);

    public User find(long id);

    public List<User> getAll();

    public User update(User user);

    public User delete(User user);
}
