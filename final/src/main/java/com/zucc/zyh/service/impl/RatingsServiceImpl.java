package com.zucc.zyh.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.utils.Query;

import com.zucc.zyh.dao.RatingsDao;
import com.zucc.zyh.entity.RatingsEntity;
import com.zucc.zyh.service.RatingsService;


@Service("ratingsService")
public class RatingsServiceImpl extends ServiceImpl<RatingsDao, RatingsEntity> implements RatingsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RatingsEntity> page = this.page(
                new Query<RatingsEntity>().getPage(params),
                new QueryWrapper<RatingsEntity>()
        );

        return new PageUtils(page);
    }

}
