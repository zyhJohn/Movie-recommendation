package com.zucc.zyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.entity.UserEntity;

import java.util.Map;

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

