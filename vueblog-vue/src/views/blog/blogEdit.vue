<template>
    <div>
        <Header></Header>
        <el-main>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>
                <el-form-item label="摘要" prop="content">
                    <el-input type="textarea" v-model="ruleForm.content"></el-input>
                </el-form-item>
                <el-form-item label="内容" prop="description">
                    <mavon-editor v-model="ruleForm.description"></mavon-editor>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">取消</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </div>
</template>

<script>
    import Header from "../../components/Header";
    import logo from "../../assets/images/login.png";
    import Element from "element-ui";

    export default {
        name: "blogEdit",
        components: {Header},
        data() {
            return {
                imgLogo: logo,
                n: false,
                m: false,

                //表单验证
                ruleForm: {
                    blogId: '',
                    title: '',
                    description: '',
                    content: ''
                },
                // 验证规则
                rules: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                    ],
                    description: [
                        {required: true, message: '请输入摘要', trigger: 'blur'},
                    ],
                    content: [
                        {required: true, message: '请输入内容', trigger: 'blur'},
                    ]
                },
            }
        },
        methods: {
            submitForm(ruleForm) {
                this.$refs[ruleForm].validate((valid) => {
                    if (valid) {
                        // this指向全局的Vue对象
                        const _this = this;
                        this.$axios.post('/blog/edit', this.ruleForm, {
                            headers: {
                                'Authorization': localStorage.getItem('token')
                            }
                        }).then(res => {
                            let data = res.data;
                            debugger
                            console.log(data)
                            if (data.code === '200') {
                                _this.$alert('发布成功', '提示',{
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/blogs');
                                    }
                                })
                            } else {
                                Element.Message.error(data.msg)
                                return false;
                            }
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
        },
        created() {
            // 如果是编辑 URL会携带一个id参数
            let blogId = this.$route.params.blogId;
            const _this = this
            if (blogId) {
                this.$axios.get('/blog/detail/' + blogId).then(res => {
                    const blog = res.data.data
                    _this.ruleForm.id = blog.id
                    _this.ruleForm.title = blog.title
                    _this.ruleForm.description = blog.description
                    _this.ruleForm.content = blog.content
                });

            }
        }
    }
</script>

<style scoped>
    .demo-ruleForm{margin: 0 20%}
</style>