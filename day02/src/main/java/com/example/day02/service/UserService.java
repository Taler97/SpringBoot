package com.example.day02.service;

import com.example.day02.ennity.User;
import java.util.List;

public interface UserService {

    // 新增用户
    boolean addUser(User user);

    // 更新用户
    boolean updateUser(User user);

    // 删除用户
    boolean deleteUser(int id);

    // 根据ID查询用户
    User getUserById(int id);

    // 查询所有用户
    List<User> getAllUsers();


}