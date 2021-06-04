<template>
	<div class="testProject">
		<div class="function">
			<el-row>
				<el-col :span="4">
					<div class="grid-content bg-purple">
						<el-input placeholder="请输入要查询的用户" icon="search" v-model="input">
						</el-input>
					</div>
				</el-col>
<!-- 				<el-col :span="20">
					<el-button type="primary" @click.native="add_user">新建方案测试</el-button>
				</el-col> -->
			</el-row>
		</div>
		<div id="table">
			<el-table :data="tableData" stripe style="width: 100%">
				<el-table-column prop="userId" label="用户ID">
				</el-table-column>
				<el-table-column prop="userNo" label="用户账号">
				</el-table-column>
				<el-table-column prop="userName" label="用户名">
				</el-table-column>
				<el-table-column prop="userPwd" label="密码">
				</el-table-column>
				<el-table-column prop="userMail" label="邮箱" width=140>
				</el-table-column>
				<el-table-column prop="gender" label="性别" width=140>
				</el-table-column>
				<el-table-column prop="age" label="年龄" width=140>
				</el-table-column>
				<el-table-column prop="occupation" label="职业" width=140>
				</el-table-column>
				<el-table-column label="操作" width=120>
					<template slot-scope="scope">
						<el-button type="text" size="small" @click="del_item(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		created(){
			let _this =this
			axios.get('http://localhost:8181/user/list').then(function (response){
				_this.tableData = response.data
			})
		},
		data() {
			return {
				input: '',
				dialogTitle: '',
				isDialogShow: false,
				tableData:null,
				form: {
					user_id:'',
					user_no:'',
					user_name:'',
					user_pwd:'',
					user_mail:'',
					gender:'',
					age:'',
					occupation:'',
				},
				formLabelWidth: '120px'

			}
		},
		methods:{
			input_grade(){
				this.isDialogShow=true
			}
		},
	}
</script>

<style>
  .el-tabs{
    width: 100%;
  }
</style>

