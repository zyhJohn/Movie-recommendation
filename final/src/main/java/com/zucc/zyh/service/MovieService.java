package com.zucc.zyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.entity.MovieEntity;

import java.util.Map;

public interface MovieService extends IService<MovieEntity> {


    PageUtils queryPage(Map<String, Object> params);
}

