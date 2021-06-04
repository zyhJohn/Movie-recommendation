package com.zucc.zyh.dao;

import com.zucc.zyh.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    @Select("select * from user where user_no= #{userNo} and user_pwd= #{userPwd};")
    List<UserEntity> login(@Param("userNo") String userNo, @Param("userPwd") String userPwd);

    @Select("select * from user where user_no= #{userNo}")
    List<UserEntity> check(@Param("userNo") String userNo);

    @Select("select * from movie where movie_id= #{movieId}")
    List<UserEntity> recommend(@Param("movieId") int movieId);
}
