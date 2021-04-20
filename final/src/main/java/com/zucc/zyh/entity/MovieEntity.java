package com.zucc.zyh.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

@Data
@TableName("movie")
public class MovieEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer movieId;
	private String title;
	private String country;
	private String category;
	private String downloadUrl;
	private String introduce;
	private String language;
	private String mainActor;
	private String movieTime;
	private String publishDate;

}
