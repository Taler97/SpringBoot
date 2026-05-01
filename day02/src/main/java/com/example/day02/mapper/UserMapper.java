package com.example.day02.mapper;

import com.example.day02.ennity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(username, password, age, create_time) " +
            "VALUES(#{username}, #{password}, #{age}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(User user);

    @Update("UPDATE user SET username=#{username}, password=#{password}, age=#{age} " +
            "WHERE id=#{id}")
    int update(User user);


    @Delete("DELETE FROM user WHERE id = #{id}")
    int delete(@Param("id") int id);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(@Param("id") int id);


    @Select("SELECT * FROM user")
    List<User> selectAll();
}
