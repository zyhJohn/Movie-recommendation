<template>
	<div class="testProject">
		<div class="function">
			<el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
			<el-form-item>
				<el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
			</el-form-item>
			<el-form-item>
				<el-button @click="getDataList()">查询</el-button>
				<!-- <el-button v-if="isAuth('generator:movie:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button> -->
			</el-form-item>
			</el-form>
		</div>
		<div id="table">
			<el-table :data="tableData" stripe style="width: 100%">
				<el-table-column prop="title" label="电影名">
				</el-table-column>
				<el-table-column prop="country" label="国家地区">
				</el-table-column>
				<el-table-column prop="category" label="类别" width=140>
				</el-table-column>
<!--				<el-table-column prop="downloadUrl" label="下载地址" width=140>
				</el-table-column>
				<el-table-column prop="introduce" label="介绍" width=140>
				</el-table-column>
				<el-table-column prop="language" label="语种" width=140>
				</el-table-column>
				<el-table-column prop="mainActor" label="主演" width=140>
				</el-table-column>
				<el-table-column prop="movieTime" label="电影时长" width=140>
				</el-table-column> -->
				<el-table-column prop="publishDate" label="上映日期" width=140>
				</el-table-column>
				<el-table-column label="操作" width=120>
					<template slot-scope="scope">
						<el-button type="text" size="small" @click="get_detail(scope.row)">查看详情</el-button>
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
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		created(){
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
		mounted(){
			getDataList()
		},
		activated () {
			getDataList()
		},
		methods:{
			getDataList(){
				this.dataListLoading = true
				let _this =this
				axios.get('http://localhost:8181/movie/list?page='+this.pageIndex+'&limit='+this.pageSize).then(({data}) => {
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

