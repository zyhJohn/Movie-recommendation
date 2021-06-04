<template>


    <el-menu default-active="/testManagement" class="el-menu-vertical-demo" router>
    <el-submenu index="1">
      <template slot="title"><i class="el-icon-message"></i>用户相关管理</template>
      <el-menu-item-group>
		    <el-menu-item index="/index/user">用户管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
	<el-submenu index="2">
	  <template slot="title"><i class="el-icon-message"></i>电影相关管理</template>
	  <el-menu-item-group>
	    <el-menu-item index="/index/movie">电影管理</el-menu-item>
	    <el-menu-item index="/index/want">电影收藏管理</el-menu-item>
      <el-menu-item index="/index/ratings">电影评分管理</el-menu-item>
	  </el-menu-item-group>
	</el-submenu>
  </el-menu>


</template>
