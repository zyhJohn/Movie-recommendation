package com.zucc.zyh.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

@Data
@TableName("admin")
public class AdminEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private String admin;
	private String pwd;

}
