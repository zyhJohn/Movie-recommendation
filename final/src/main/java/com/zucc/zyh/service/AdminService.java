package com.zucc.zyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.entity.AdminEntity;

import java.util.Map;

public interface AdminService extends IService<AdminEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

