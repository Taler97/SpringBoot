package com.example.day02.service;

import com.example.day02.ennity.User;
import com.example.day02.mapper.UserMapper;
import com.example.day02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        int rows = userMapper.insert(user);
        System.out.println("新增用户ID：" + user.getId());
        return rows > 0;
    }

    @Override
    public boolean updateUser(User user) {
        int rows = userMapper.update(user);
        return rows > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        int rows = userMapper.delete(id);
        return rows > 0;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

}