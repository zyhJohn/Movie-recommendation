<template>
<div class="header-wraper">
    <el-menu theme="dark" default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect">

        <li class="title">
            <i class="el-icon-menu"></i>
            <span>交互式电影推荐平台</span>
        </li>
        <!-- <li class="option">
            <el-select v-model="value" placeholder="请选择">
                <el-option v-for="item in options" :label="item.label" :value="item.value">
                </el-option>
            </el-select>
        </li> -->
        <li class="user">
            <el-dropdown trigger="click">
                <span class="el-dropdown-link">
                {{user_name}}<i class="el-icon-caret-bottom el-icon--right"></i>
              </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item @click.native="login" v-show="login_in">登录</el-dropdown-item>
                    <el-dropdown-item @click.native="backto_login" v-if="!login_in">注销</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </li>
    </el-menu>
</div>
</template>


<script>
import axios from 'axios'
export default {
    data() {
        return {
            login_in : true,
            user_name: null,
            options: [{
                label: "test1",
                value: 1
            }, {
                label: "test2",
                value: 2
            }, {
                label: "test3",
                value: 3
            }, {
                label: "test4",
                value: 4
            }, {
                label: "test5",
                value: 5
            }, {
                label: "test6",
                value: 6
            }, {
                label: "test7",
                value: 7
            }, ],
            value: 1

        }
    },
    mounted(){
        if(sessionStorage.getItem('userId'))
        {
            this.login_in = false
            this.user_name = sessionStorage.getItem('userName')
        }
        else
            this.user_name = null
        // axios.get('http://localhost:8181/user/findbyid/'+this.userId)
		// 		.then(function (response){
		// 			if(response.length!=0)
		// 			{
		// 				_this.$router.push('./index');
		// 			}
		// 		})
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        login(){
			this.$router.replace('/login');
        },
		backto_login(){
            sessionStorage.removeItem('userId')
            sessionStorage.removeItem('userNo')
            sessionStorage.removeItem('userName')
            this.login_in = true
            this.$mount()
		}
    }
}
</script>


<style media="screen">
.header-wraper li {
    float: left;
    margin-right: 20px;
    margin-top: 5px;
}

.title {
    padding: 1em .2em;
}

.title span {
    font-size: 1.4em;
    margin-left: 5px;
}
.el-select-dropdown__list{
  max-height: 187px !important;
}
.option {
    padding-top: 10px;
}

.user .el-dropdown {
    position: absolute;
    right: 20px;
    padding-top: 20px;
    color: #fff !important;
}
</style>
