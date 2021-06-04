package com.zucc.zyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<UserEntity> {

    List<UserEntity> login(String userNo, String userPwd);
    List<UserEntity> check(String userNo);
    List<UserEntity> recommend(int userId);
    PageUtils queryPage(Map<String, Object> params);
}

