<template>
	<div class="testProject">
		<div class="function">
			<el-row>
				<el-col :span="4">
					<div class="grid-content bg-purple">
						<el-input placeholder="请输入要查询的收藏" icon="search" v-model="input">
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
				<el-table-column prop="user_id" label="用户ID">
				</el-table-column>
				<el-table-column prop="movie_id" label="电影ID">
				</el-table-column>
				<el-table-column prop="want" label="收藏">
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
	export default {
		data() {
			return {
				input: '',
				dialogTitle: '',
				isDialogShow: false,
				tableData:[],
				form: {
					user_id:'',
					user_name:'',
					distance:'',
					create_time:'',
					end_time:'',
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

