<template>
	<div class="testProject">
		<div class="function">
			<el-row>
				<el-col :span="4">
					<div class="grid-content bg-purple">
					</div>
				</el-col>
			</el-row>
		</div>
		<div id="table">
			<el-table :data="tableData" stripe style="width: 100%">
				<el-table-column prop="title" label="电影名">
				</el-table-column>
				<el-table-column prop="country" label="国家地区">
				</el-table-column>
				<el-table-column prop="category" label="类别" width=140>
				</el-table-column>
				<el-table-column prop="publishDate" label="上映日期" width=140>
				</el-table-column>
				<el-table-column label="操作" width=120>
					<template slot-scope="scope">
						<el-button type="text" size="small" @click="get_detail(scope.row)">查看详情</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		beforeCreate(){
			if(!sessionStorage.getItem('userId'))
				this.$message.error("请先登录")
		},
		created(){
			this.userId = sessionStorage.getItem('userId')
			var _this = this
			axios.get('http://127.0.0.1:5000/predict?userId='+this.userId).then(function (response){
				var recommend_list = JSON.parse(response.data).prediction
				var i=0
				var result = []
				for(i=0;i<recommend_list.length;i++){
					axios.get('http://localhost:8181/movie/findbyid/'+recommend_list[i]).then(function (res){
						console.log(res.data)
						result.push(res.data)
					})
				}
				console.log(result)
				_this.tableData = result
			})
		},
		data() {
			return {
				userId:'',
				input: '',
				dialogTitle: '',
				isDialogShow: false,
				tableData:[],
				recommend_list:[],
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
		mounted(){

		},
		methods:{
			get_detail(selected){
				console.log(selected.movieId)
				this.$router.push({path:'/index/detail',query:{movieId:selected.movieId}})
			}
		},
	}
</script>

<style>
  .el-tabs{
    width: 100%;
  }
</style>

