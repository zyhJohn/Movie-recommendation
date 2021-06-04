<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="账号" prop="userNo">
      <el-input v-model="dataForm.userNo" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="用户名" prop="userName">
      <el-input v-model="dataForm.userName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="userPwd">
      <el-input v-model="dataForm.userPwd" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="userMail">
      <el-input v-model="dataForm.userMail" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="gender">
      <el-input v-model="dataForm.gender" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="年龄" prop="age">
      <el-input v-model="dataForm.age" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="职业" prop="occupation">
      <el-input v-model="dataForm.occupation" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          userId: 0,
          userNo: '',
          userName: '',
          userPwd: '',
          userMail: '',
          gender: '',
          age: '',
          occupation: ''
        },
        dataRule: {
          userNo: [
            { required: false, message: '不能为空', trigger: 'blur' }
          ],
          userName: [
            { required: false, message: '不能为空', trigger: 'blur' }
          ],
          userPwd: [
            { required: false, message: '不能为空', trigger: 'blur' }
          ],
          userMail: [
            { required: false, message: '不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: false, message: '不能为空', trigger: 'blur' }
          ],
          age: [
            { required: false, message: '不能为空', trigger: 'blur' }
          ],
          occupation: [
            { required: false, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/user/info/${this.dataForm.userId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userNo = data.user.userNo
                this.dataForm.userName = data.user.userName
                this.dataForm.userPwd = data.user.userPwd
                this.dataForm.userMail = data.user.userMail
                this.dataForm.gender = data.user.gender
                this.dataForm.age = data.user.age
                this.dataForm.occupation = data.user.occupation
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/user/${!this.dataForm.userId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userId': this.dataForm.userId || undefined,
                'userNo': this.dataForm.userNo,
                'userName': this.dataForm.userName,
                'userPwd': this.dataForm.userPwd,
                'userMail': this.dataForm.userMail,
                'gender': this.dataForm.gender,
                'age': this.dataForm.age,
                'occupation': this.dataForm.occupation
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
