package com.example.day02.controller;

import com.example.day02.ennity.User;
import com.example.day02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Map<String, Object> add(@RequestBody User user) {
        boolean success = userService.addUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", success ? "添加成功" : "添加失败");
        result.put("data", user);
        return result;
    }

    @PutMapping
    public Map<String, Object> update(@RequestBody User user) {
        boolean success = userService.updateUser(user);
        return Map.of("success", success,
                "message", success ? "更新成功" : "更新失败");
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable int id) {
        boolean success = userService.deleteUser(id);
        return Map.of("success", success,
                "message", success ? "删除成功" : "删除失败");
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> list() {
        return userService.getAllUsers();
    }
}