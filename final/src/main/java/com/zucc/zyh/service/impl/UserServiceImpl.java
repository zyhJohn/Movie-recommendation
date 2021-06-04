package com.zucc.zyh.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.utils.Query;

import com.zucc.zyh.dao.UserDao;
import com.zucc.zyh.entity.UserEntity;
import com.zucc.zyh.service.UserService;

import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Resource
    UserDao userDao;
    @Override
    public List<UserEntity> login(String userNo, String userPwd) {
        return userDao.login(userNo,userPwd);
    }
    public List<UserEntity> check(String userNo) {
        return userDao.check(userNo);
    }
    public List<UserEntity> recommend(int userId) {return userDao.recommend(userId); }
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

}
