package com.zucc.zyh.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer userId;
	private String userNo;
	private String userName;
	private String userPwd;
	private String userMail;
	private String gender;
	private Integer age;
	private Integer occupation;

}
