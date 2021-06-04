package com.zucc.zyh.dao;

import com.zucc.zyh.entity.MovieEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieDao extends BaseMapper<MovieEntity> {

}
