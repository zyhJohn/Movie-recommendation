package com.zucc.zyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.entity.RatingsEntity;

import java.util.Map;

public interface RatingsService extends IService<RatingsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

