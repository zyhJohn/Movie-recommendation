<template>
	<div class="testProject">
		<div class="function">
			<el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
			<el-form-item>
				<el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
			</el-form-item>
			<el-form-item>
				<el-button @click="getDataList()">查询</el-button>
				<el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
			</el-form-item>
			</el-form>
		</div>
		<div id="table">
			<el-table :data="tableData"
			border
			v-loading="dataListLoading"
			style="width: 100%;">
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
						<el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.userId)">修改</el-button>
						<el-button type="text" size="small" @click="deleteHandle(scope.row.userId)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				:page-sizes="[10, 20, 50, 100]"
				:page-size="pageSize"
				:total="totalPage"
				layout="total, sizes, prev, pager, next, jumper">
			</el-pagination>
			<!-- 弹窗, 新增 / 修改 -->
    		<add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	import AddOrUpdate from './user-add-or-update.vue'
	export default {
		created(){
		// 	let _this =this
		// 	axios.get('http://localhost:8181/user/list?page='+this.pageIndex+'&limit='+this.pageSize).then(({data}) => {
		// 		if (data && data.code === 0) {
		// 			_this.tableData = data.page.list
		// 			_this.totalPage = data.page.totalCount
		// 		} else {
		// 			_this.tableData = []
		// 			_this.totalPage = 0
		// 		}
		// 		_this.dataListLoading = false
        // }
		// 	 	function (response){
		// 	 	_this.tableData = response.data
		// 	 }
		// )
		},
		data() {
			return {
				dataForm: {
				key: ''
				},
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				addOrUpdateVisible: false,
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
		mounted(){
			getDataList()
		},
		components: {
			AddOrUpdate
		},
		activated () {
			getDataList()
		},
		methods:{
			getDataList(){
				this.dataListLoading = true
				let _this =this
				axios.get('http://localhost:8181/user/list?page='+this.pageIndex+'&limit='+this.pageSize+'&key='+this.dataForm.key).then(({data}) => {
				if (data && data.code === 0) {
					_this.tableData = data.page.list
					_this.totalPage = data.page.totalCount
				} else {
					_this.tableData = []
					_this.totalPage = 0
				}
				_this.dataListLoading = false
			})
			},
			addOrUpdateHandle(id){
				this.addOrUpdateVisible = true
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id)
				})
			},
			deleteHandle(selected){
				var _this = this
				axios.get('http://localhost:8181/user/delete/'+selected).then(function (response){
					_this.$message.succes("删除成功")
			})
			},
			// 每页数
			sizeChangeHandle (val) {
				this.pageSize = val
				this.pageIndex = 1
				this.getDataList()
			},
			// 当前页
			currentChangeHandle (val) {
				this.pageIndex = val
				this.getDataList()
			},
		},
	}
</script>

<style>
  .el-tabs{
    width: 100%;
  }
</style>

