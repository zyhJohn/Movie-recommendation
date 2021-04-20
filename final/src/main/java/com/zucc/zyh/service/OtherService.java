package com.zucc.zyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.entity.OtherEntity;

import java.util.Map;

public interface OtherService extends IService<OtherEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

