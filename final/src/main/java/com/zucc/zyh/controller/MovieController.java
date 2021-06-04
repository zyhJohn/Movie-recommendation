package com.zucc.zyh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zucc.zyh.entity.MovieEntity;
import com.zucc.zyh.service.MovieService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.utils.R;

@RestController
@RequestMapping("movie")
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

    @DeleteMapping("/delete/{movieId}")
    public boolean delete(@PathVariable("movieId") Integer id){
        return this.movieService.removeById(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody MovieEntity movie){
        return this.movieService.updateById(movie);
    }

    @GetMapping("/findbyid/{movieId}")
    public MovieEntity find(@PathVariable("movieId") Integer id ){
        return this.movieService.getById(id);
    }

}
