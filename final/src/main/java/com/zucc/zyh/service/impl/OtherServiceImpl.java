package com.zucc.zyh.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.utils.Query;

import com.zucc.zyh.dao.OtherDao;
import com.zucc.zyh.entity.OtherEntity;
import com.zucc.zyh.service.OtherService;


@Service("otherService")
public class OtherServiceImpl extends ServiceImpl<OtherDao, OtherEntity> implements OtherService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OtherEntity> page = this.page(
                new Query<OtherEntity>().getPage(params),
                new QueryWrapper<OtherEntity>()
        );

        return new PageUtils(page);
    }

}
