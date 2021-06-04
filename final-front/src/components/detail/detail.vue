<template>
	<div class="testProject">
		<div class="function">
			<el-row>
				<el-col :span="4">
					<div class="grid-content bg-purple">
						<el-input placeholder="请输入要查询的电影" icon="search" v-model="input">
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
				<el-table-column prop="movieId" label="电影ID">
				</el-table-column>
				<el-table-column prop="title" label="电影名">
				</el-table-column>
				<el-table-column prop="country" label="国家地区">
				</el-table-column>
				<el-table-column prop="category" label="类别" width=140>
				</el-table-column>
				<el-table-column prop="downloadUrl" label="下载地址" width=140>
				</el-table-column>
				<el-table-column prop="introduce" label="介绍" width=140>
				</el-table-column>
				<el-table-column prop="language" label="语种" width=140>
				</el-table-column>
				<el-table-column prop="mainActor" label="主演" width=140>
				</el-table-column>
				<el-table-column prop="movieTime" label="电影时长" width=140>
				</el-table-column>
				<el-table-column prop="publishDate" label="上映日期" width=140>
				</el-table-column>
				<el-table-column label="操作" width=120>
					<template slot-scope="scope">
						<el-button type="text" size="small" @click="want(scope.row)">想看</el-button>
						<el-button type="text" size="small" @click="collect(scope.row)">收藏</el-button>
						<el-button type="text" size="small" @click="ratings(scope.row)">评分</el-button>
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
			var query = this.$route.query;
			console.log(query);
			if (query) {var movieId = query.movieId;}

			let _this =this
			axios.get('http://localhost:8181/movie/info/'+ movieId).then(function (response){
				_this.tableData = response.data
			})
		},
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
			want(selected){
				console.log('想看')
				console.log(selected)
			},
			collect(selected){
				console.log('收藏')
				console.log(selected)
			},
			ratings(selected){
				console.log('评分')
				console.log(selected)
			}
		},
	}
</script>

<style>
  .el-tabs{
    width: 100%;
  }
</style>

