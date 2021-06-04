package com.zucc.zyh.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zucc.zyh.entity.OtherEntity;
import com.zucc.zyh.service.OtherService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.utils.R;

@RestController
@RequestMapping("other")
public class OtherController {
    @Autowired
    private OtherService otherService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = otherService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId){
		OtherEntity other = otherService.getById(userId);

        return R.ok().put("other", other);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OtherEntity other){
		otherService.save(other);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OtherEntity other){
		otherService.updateById(other);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] userIds){
		otherService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
