<template>
    <div class="blogs">
        <el-container>
            <el-main class="blog-main">
                <h3>用户注册</h3>
                <div class="register">
                    已有账号？<router-link :to="{name: 'login'}">登录</router-link>
                </div>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="ruleForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="输入邮箱" prop="email">
                        <el-input type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "login",
        data() {
            // 验证密码
            let validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            let validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            }
            let checkEmail = (rule, value, callback) => {
                const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
                if (!value) {
                    return callback(new Error('邮箱不能为空'))
                }
                setTimeout(() => {
                    if (mailReg.test(value)) {
                        callback()
                    } else {
                        callback(new Error('请输入正确的邮箱格式'))
                    }
                }, 100)
            }
            return {
                n: false,
                m: false,

                //表单验证
                ruleForm: {
                    username: '222',
                    password: '222',
                    checkPass: '222',
                    email: '1635644462@qq.com'
                },
                // 验证规则
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                    ],
                    password: [
                        {validator: validatePass, required: true, trigger: 'blur'}
                    ],
                    checkPass: [
                        {validator: validatePass2, required: true, trigger: 'blur'}
                    ],
                    email: [
                        { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                    ],
                },
            }
        },
        methods: {
            submitForm(ruleForm) {
                this.$refs[ruleForm].validate((valid) => {
                    if (valid) {
                        const ths = this
                        ths.$confirm('确认提交?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(()=>{
                            this.$axios.post('/user/save', this.ruleForm).then(res => {

                                // this 指向的是当前$axios
                                console.log(res)
                                ths.$router.push('/login');
                            });
                        })

                        // this指向全局的Vue对象
                        // const _this = this;

                    } else {
                        console.log('error submit!!')
                        return false
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields()
            }
        }
    }
</script>

<style scoped>
    .el-header, .el-footer {
        background-color: #D3DCE6;
        height: 150px !important;
        color: #333;
        text-align: center;
    }
    .el-aside {
        color: #333;
        text-align: center;
    }

    .el-main {
        color: #333;
        text-align: center;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
    }

    .el-container:nth-child(7) .el-aside {
    }

    .logo-bg{height: 100px;margin: -1px auto 0 1%;}
    .demo-ruleForm{max-width: 500px;margin: 0 auto}
    .el-form-item{padding-right: 12%;}
    .blogs{box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);min-height: 500px;margin: 1% 20%}
    .blog-main{height: 500px}
    .register{height: 50px;margin: 0 auto;width: 150px}
</style>