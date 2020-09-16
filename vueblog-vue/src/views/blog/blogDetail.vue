<template>
    <div>
        <Header></Header>
        <div class="blogs">
            <h2>{{ blog.title }}</h2>
            <el-divider></el-divider>
            <div class="markdown-body" v-html="blog.description"></div>
        </div>
    </div>
</template>

<script>
    import Header from "../../components/Header";
    import 'github-markdown-css'
    export default {
        name: "blogDetail",
        components: {Header},
        data(){
            return{
                blog:{
                    blogId: '',
                    title: '',
                    description: '',
                }
            }
        },
        created() {
            const ths = this
            const blogId = this.$route.params.blogId
            this.$axios.get('/blog/detail/'+blogId).then(res => {
                const data = res.data.data
                ths.id = data.id
                ths.blog.title = data.title

                var markDown = require('markdown-it')
                var down = new markDown()
                let render = down.render(data.description);
                ths.blog.description = render
            })
        }
    }
</script>

<style scoped>
    .blogs{box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);min-height: 500px;margin: 1% 15%}
    .blogs >h2 {padding-top: 1%}
</style>