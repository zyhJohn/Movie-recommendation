package com.zucc.zyh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zucc.zyh.entity.UserEntity;
import com.zucc.zyh.service.UserService;
import com.zucc.zyh.utils.PageUtils;
import com.zucc.zyh.utils.R;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ResponseBody
    public List<UserEntity> login(@RequestParam("userNo") String userNo,@RequestParam("userPwd")String userPwd){
        return userService.login(userNo,userPwd);

    }

    @GetMapping("/check")
    @ResponseBody
    public List<UserEntity> check(@RequestParam("userNo") String userNo){
        return userService.check(userNo);

    }

    @PostMapping("/register")
    public boolean add(@RequestBody UserEntity user ){
        return this.userService.save(user);
    }

    @GetMapping("/recommend")
    @ResponseBody
    public List<UserEntity> recommend(@RequestParam("userId") int userId){
        return userService.recommend(userId);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
//    public List<UserEntity> list(){
//        return this.userService.list();
//    };
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId){
		UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }

    @DeleteMapping("/delete/{userId}")
    public boolean delete(@PathVariable("userId") Integer id){
        return this.userService.removeById(id);
    }

    @GetMapping("/findbyid/{userId}")
    public UserEntity find(@PathVariable("userId") Integer id ){
        return this.userService.getById(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody UserEntity user){
        return this.userService.updateById(user);
    }
}
