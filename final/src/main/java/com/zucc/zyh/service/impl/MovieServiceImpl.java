package com.zucc.zyh.service.impl;

import com.zucc.zyh.dao.UserDao;
import com.zucc.zyh.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.utils.Query;

import com.zucc.zyh.dao.MovieDao;
import com.zucc.zyh.entity.MovieEntity;
import com.zucc.zyh.service.MovieService;

import javax.annotation.Resource;


@Service("movieService")
public class MovieServiceImpl extends ServiceImpl<MovieDao, MovieEntity> implements MovieService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MovieEntity> page = this.page(
                new Query<MovieEntity>().getPage(params),
                new QueryWrapper<MovieEntity>()
        );

        return new PageUtils(page);
    }

}
