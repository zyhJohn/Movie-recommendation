<template>
	<div id="loginPage">
		<div id="loginform" class="login_container">
			<h1 class="title">用户登录</h1>
			<div class="split"></div>
			<div style="display: flex;position: relative;">
				<img src="../../../static/img/user.png" class="ipt-icon" />
				<input class="ipt" style="text-align: left;" focus type="text" placeholder="请输入账号" v-model="admin"/>
			</div>
			<div style="margin-top: 26px;display: flex;position: relative;">
				<img src="../../../static/img/lock.png" class="ipt-icon" />
				<input class="ipt" style="text-align: left;" type="password"  placeholder="请输入密码" v-model="password"/>
			</div>
			<!-- <view style="text-align: left;margin-top: 30px;">
				<input id="remberPwd" class="checkbox" type="checkbox" />
				<span class="remberPwd">记住密码</span>
				<span id="loginMsg" class="error"></span>
			</view> -->
			<div>
				<button class="btn" type="button" @click="login" style="margin-top: 80px;">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				phone: '',
				password: '',
				from: ''
			};
		},
		
		methods: {
			login() {
				if (this.admin != 'admin') {
					this.$message.success("用户名不正确！");
					return;
				}
				if (this.password != 'admin') {
					this.$message.success("密码不正确！");
					return;
				}
				this.$router.push('./index')

			}
		}
	}
</script>

<style scoped>
	#loginPage {
		height: 100%;
		width: 100%;
		background: url(../../../static/img/background.jpg) no-repeat;
		background-size: 100% 100%;
		background-attachment: fixed;
		display: flex;
		display: -webkit-flex;
		justify-content: center;
		/*侧轴居中对齐*/
		align-items: center;
	}
	
	.head {
		position: absolute;
		top: 36px;
		left: 36px;
		color: #ffffff;
	}
	
	.head img {
		height: 72px;
		width: 72px;
	}
	
	.head label {
		height: 31px;
		font-size: 22px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		line-height: 31px;
		color: rgba(255, 255, 255, 1);
		text-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
		opacity: 1;
		position: relative;
		top: -26px;
	}
	
	.login_container {
		display: inline-block;
		height: 400px;
		width: 360px;
		background-color: #FFFFFF;
		box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
		opacity: 1;
		text-align: center;
	}
	
	.login_container .title {
		font-size: 18px;
		font-family: Microsoft YaHei;
		font-weight: 300;
		line-height: 29px;
		color: rgba(23, 105, 228, 1);
		opacity: 1;
		text-align: left;
		padding: 15px 0px 12px 30px;
		margin: 0px;
	}
	
	.login_container .split {
		width: 91%;
		height: 0px;
		border: 1px solid rgba(169, 169, 169, 1);
		opacity: 1;
		display: inline-block;
		margin-bottom: 34px;
	}
	
	.login_container .ipt {
		margin-left: -10px;
		width: 253px;
		height: 40px;
		border: 1px solid rgba(169, 169, 169, 1);
		opacity: 1;
		border-radius: 5px;
		font-size: 14px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		position: relative;
		left: 28px;
		z-index: 200;
		padding-left: 38px;
	}
	
	.ipt-icon {
		width: 16px;
		height: 16px;
		position: relative;
		left: 50px;
		top: 13px;
		z-index: 9999;
	}
	
	.login_container .checkbox {
		width: 17px;
		height: 17px;
		background: rgba(255, 255, 255, 1);
		border: 1px solid rgba(169, 169, 169, 1);
		opacity: 1;
		border-radius: 6px;
		margin-left: 40px;
	}
	
	.login_container .remberPwd {
		width: 64px;
		height: 21px;
		font-size: 14px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		line-height: 21px;
		color: rgba(169, 169, 169, 1);
		opacity: 1;
		display: inline-block;
		position: relative;
		top: -5px;
	}
	
	.login_container .error {
		width: 212px;
		height: 19px;
		font-size: 14px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		line-height: 19px;
		color: rgba(255, 0, 0, 1);
		opacity: 1;
		display: inline-block;
		float: right;
		margin-right: 38px;
		text-align: right;
	}
	
	.login_container .btn {
		width: 280px;
		height: 42px;
		background: rgba(23, 105, 228, 1);
		opacity: 1;
		border-radius: 5px;
		color: #FFFFFF;
		font-size: 16px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		margin-top: 12px;
		cursor: pointer;
		border: 0px;
	}
	
	.login_container .forget {
		width: 80px;
		height: 21px;
		font-size: 14px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		line-height: 21px;
		color: rgba(23, 105, 228, 1);
		opacity: 1;
		float: right;
		margin-top: 20px;
		margin-right: 32px;
		cursor: pointer;
	}
	.button{
		display: flex;
		flex-direction: row;
	}
	.footer {
		position: absolute;
		bottom: 25px;
	}
	
	.footer label {
		width: 616px;
		height: 19px;
		font-size: 14px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		line-height: 19px;
		color: rgba(255, 255, 255, 1);
		text-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
		opacity: 1;
	}
	
	.forgetpwd_container {
		display: inline-block;
		width: 500px;
		height: 500px;
		background-color: #FFFFFF;
		box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
		opacity: 1;
		text-align: center;
	}
	
	.forgetpwd_container .title {
		font-size: 18px;
		font-family: Microsoft YaHei;
		font-weight: 300;
		line-height: 29px;
		color: rgba(23, 105, 228, 1);
		opacity: 1;
		text-align: left;
		padding: 15px 0px 12px 30px;
		margin: 0px;
	}
	
	.forgetpwd_container .close {
		width: 22px;
		height: 22px;
		opacity: 1;
		cursor: pointer;
		float: right;
		position: relative;
		top: -33px;
		right: 30px;
		font-size: 22px;
		font-family: -webkit-pictograph;
	}
	
	.forgetpwd_container .split {
		width: 91%;
		height: 0px;
		border: 1px solid rgba(169, 169, 169, 1);
		opacity: 1;
		display: inline-block;
		margin-bottom: 20px;
	}
	
	.forgetpwd_container .field_label {
		width: 56px;
		font-size: 14px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		line-height: 72px;
		color: rgba(0, 0, 0, 1);
		opacity: 1;
		text-align: right;
		display: inline-block;
		margin-right: 8px;
	}
	
	.forgetpwd_container .ipt {
		width: 313px;
		height: 40px;
		border: 1px solid rgba(169, 169, 169, 1);
		opacity: 1;
		border-radius: 5px;
		padding-left: 5px;
		font-size: 14px;
		font-family: Microsoft YaHei;
		font-weight: 400;
	}
	
	.forgetpwd_container .btn_send {
		width: 139px;
		height: 44px;
		border: 1px solid rgba(169, 169, 169, 1);
		opacity: 1;
		border-radius: 5px;
		cursor: pointer;
		font-size: 14px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		color: rgba(0, 0, 0, 1);
		opacity: 1;
		border: 0px;
	}
	.forgetpwd_container .disabled{
		color: gray;
	}
	.forgetpwd_container .error {
		height: 19px;
		font-size: 14px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		line-height: 19px;
		color: rgba(255, 0, 0, 1);
		opacity: 1;
		text-align: left;
		padding-left: 130px;
		margin-bottom: 10px;
	}
	
	.forgetpwd_container .btn {
		width: 254px;
		height: 40px;
		background: rgba(23, 105, 228, 1);
		opacity: 1;
		border-radius: 5px;
		color: #FFFFFF;
		font-size: 16px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		cursor: pointer;
		border: 0px;
	}
	.forgetpwd_container .success{
		width:144px;
		height:24px;
		font-size:18px;
		font-family:Microsoft YaHei;
		font-weight:400;
		line-height:24px;
		color:rgba(0,0,0,1);
		opacity:0.6;
	}
</style>
