<template>
    <div>
        <Header class="header"></Header>
        <div class="block">
            <el-timeline class="e-form">
                <el-timeline-item :timestamp="blog.created | formatDate" placement="top" v-for="blog in blogs">
                    <el-card>
                        <h4>
                            <router-link :to="{name: 'blogDetail', params: {blogId: blog.id}}">
                                {{blog.title.substring(0, 20)}}
                            </router-link>
                        </h4>
                        <p>{{blog.description.length > 50 ? blog.description.substring(0,50)+'...': blog.description}}</p>
                    </el-card>
                </el-timeline-item>
                <el-pagination class="pagination"
                                background
                                layout="prev, pager, next"
                               :current-page="currentPage"
                               :page-size="pageSize"
                               @current-change=page
                        :total="total">
                </el-pagination>
            </el-timeline>
        </div>
    </div>
</template>

<script>
    import Header from "../../components/Header"
    import {formatDate} from "../../components/common/data"

    export default {
        name: "blogs",
        components: {Header},
        data(){
            return{
                blogs: {},
                currentPage: 1,
                total: 0,
                pageSize: 5,
            }
        },
        filters: {
            formatDate(time) {
                let date = new Date(time)
                return formatDate(date, 'yyyy-MM-dd hh:mm')
            }
        },
        methods: {
            page(currentPage) {
                const _this = this
                _this.$axios.get('/blog/list?currentPage=' + currentPage).then(res => {
                    _this.blogs = res.data.data.records
                    _this.currentPage = res.data.data.current
                    _this.total = res.data.data.total
                    _this.pageSize = res.data.data.size
                })
            }
        },
        created() {
            this.page(1)
        }

    }
</script>

<style scoped>
    .block{margin: 0 10%}
    .e-form{text-align: left}
    .pagination{text-align: center}
</style>