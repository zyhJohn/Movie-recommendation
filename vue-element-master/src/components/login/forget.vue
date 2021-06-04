<template>
	<div id="loginPage">
		<!-- 忘记密码界面 -->
		<div id="forgetpwd" class="forgetpwd_container">
			<div style="text-align: left;">
				<h1 class="title">设置密码</h1>
				<router-link class="close" to="./login">返回</router-link>
			</div>
			<div class="split"></div>
			<div id="forgetpwd-form">
				<div style="display: flex;flex-direction: row;vertical-align: middle ;">
					<label style="margin-left: 30px;text-align: left;" class="field_label">手机号码</label>
					<input style="margin-top: 10px;text-align: left;" class="ipt" type="text" v-model="phone" placeholder="请输入手机号码" />
				</div>
				<div style="display: flex;flex-direction: row;">
					<label style="margin-left: 30px;" class="field_label">新密码</label>
					<input style="margin-top: 10px;text-align: left;" class="ipt" type="password" v-model="newPwd" placeholder="请输入新密码" />
				</div>
				<div style="display: flex;flex-direction: row;">
					<label style="margin-left: 30px;" class="field_label">确认密码</label>
					<input style="margin-top: 10px;text-align: left;" class="ipt" type="password" v-model="newPwd2" placeholder="请输入确认密码" />
				</div>
				<div id="forgetMsg" class="error"></div>
				<div>
					<button class="btn" @click="setPwd">确 &nbsp;&nbsp;&nbsp;&nbsp;定</button>
				</div>
			</div>
			<!-- <view id="success">
				<view style="height: 300px;line-height: 300px;">
					新密码设置成功！
				</view>
				<button class="btn" onclick="backToLogin()">返回登录</button>
			</view> -->
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				phone: '',
				checkCode:'',
				newPwd: '',
				newPwd2:'',
				buttonMsg:'发送验证码',
				state:0,
				sec:120
			};
		},
		methods: {
			setPwd(){
				var _this=this;
				if(this.phone=="" || this.phone.length!=11){
					this.$message.warning("请输入有效的手机号码");
					return;
				}
				if(this.newPwd==""){
					this.$message.warning("请输入新密码");
					return;
				}
				if(this.newPwd2==""){
					this.$message.warning("请再输入一次新密码");
					return;
				}
				if(this.newPwd!=this.newPwd2){
					this.$message.warning("两次输入的密码不一致");
					return;
				}
				this.$message.success("找回成功");
				this.$router.push('./login')
			}
		}
	}
</script>

<style scoped>
	#loginPage {
		height: 100%;
		width: 100%;
		background: url(../../../static/img/background.png) no-repeat;
		background-size: 100% 100%;
		background-attachment: fixed;
		display: flex;
		display: -webkit-flex;
		justify-content: center;
		/*侧轴居中对齐*/
		align-items: center;
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
		display: inline-block;
	}

	.forgetpwd-form {
		margin: 15px;
	}

	.forgetpwd_container .close {
		width: 50px;
		height: 22px;
		opacity: 1;
		cursor: pointer;
		float: right;
		position: relative;
		margin-top: 20px;
		font-size: 14px;
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
		line-height: 61px;
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
		padding-left:10px;
	}

	.forgetpwd_container .btn_send {
		width: 139px;
		height: 41px;
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

	.forgetpwd_container .disabled {
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

	.forgetpwd_container .success {
		width: 144px;
		height: 24px;
		font-size: 18px;
		font-family: Microsoft YaHei;
		font-weight: 400;
		line-height: 24px;
		color: rgba(0, 0, 0, 1);
		opacity: 0.6;
	}
</style>
