<template>
    <div class="blogs">
        <el-container>
            <el-header>
                <img class="logo-bg" :src="imgLogo" alt="">
                <div class="register">
                    没有账号？<router-link :to="{name: 'register'}">注册</router-link>
                </div>
            </el-header>
            <el-main class="blog-main">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="ruleForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="ruleForm.password"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import logo from '../../assets/images/login.png'

    export default {
        name: "login",
        data() {
            return {
                imgLogo: logo,
                n: false,
                m: false,

                //表单验证
                ruleForm: {
                    username: 'sunny',
                    password: '111111'
                },
                // 验证规则
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},

                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 6, max: 16, message: '请输入6至16位的密码', trigger: 'blur'}
                    ],
                },
            }
        },
        methods: {
            submitForm(ruleForm) {
                this.$refs[ruleForm].validate((valid) => {
                    if (valid) {
                        // this指向全局的Vue对象
                        const _this = this;
                        this.$axios.post('/login', this.ruleForm).then(res => {
                            // this 指向的是当前$axios
                            const jwt = res.headers['authorization'];
                            const userInfo = res.data.data;
                            _this.$store.commit("SET_TOKEN", jwt);
                            _this.$store.commit("SET_USER_INFO", userInfo);
                            // 测试获取全局共享数据
                            console.log(_this.$store.getters.getUser);
                            _this.$router.push('/blogs');
                        });
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
        background-color: #E9EEF3;
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
    .blog-main{height: 350px}
    .register{height: 50px;margin: 0 auto;width: 150px}
</style>