package com.zucc.zyh.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zucc.zyh.entity.MovieEntity;
import com.zucc.zyh.service.MovieService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.utils.R;

@RestController
@RequestMapping("generator/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = movieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{movieId}")
    public R info(@PathVariable("movieId") Integer movieId){
		MovieEntity movie = movieService.getById(movieId);

        return R.ok().put("movie", movie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MovieEntity movie){
		movieService.save(movie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MovieEntity movie){
		movieService.updateById(movie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] movieIds){
		movieService.removeByIds(Arrays.asList(movieIds));

        return R.ok();
    }

}
