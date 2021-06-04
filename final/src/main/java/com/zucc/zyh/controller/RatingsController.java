package com.zucc.zyh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zucc.zyh.entity.RatingsEntity;
import com.zucc.zyh.service.RatingsService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.utils.R;



@RestController
@RequestMapping("ratings")
public class RatingsController {
    @Autowired
    private RatingsService ratingsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ratingsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId){
		RatingsEntity ratings = ratingsService.getById(userId);

        return R.ok().put("ratings", ratings);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RatingsEntity ratings){
		ratingsService.save(ratings);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RatingsEntity ratings){
		ratingsService.updateById(ratings);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] userIds) {
        ratingsService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
