<template>
	<div class="testProject">
		<div class="function">
			<el-row>
				<el-col :span="4">
				</el-col>
<!-- 				<el-col :span="20">
					<el-button type="primary" @click.native="add_user">新建方案测试</el-button>
				</el-col> -->
			</el-row>
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
					user_type:'',
					male:'',
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

