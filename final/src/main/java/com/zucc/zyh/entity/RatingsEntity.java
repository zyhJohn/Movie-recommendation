package com.zucc.zyh.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

@Data
@TableName("ratings")
public class RatingsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer userId;
	private Integer movieId;
	private Integer rating;
	private Integer timestamp;

}
