package com.zucc.zyh.dao;

import com.zucc.zyh.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
