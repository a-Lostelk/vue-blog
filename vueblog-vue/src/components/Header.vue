<template>
    <div>
        <h3>欢迎来到sunny的博客</h3>
        <div class="block"><el-avatar :size="50" :src="user.avatar?user.avatar:userLogo"></el-avatar></div>
        <div class="username" v-show="!showUser"><el-link type="primary" href="/login">{{ user.username }}</el-link></div>
        <div class="username" v-if="showUser">{{ user.username }}</div>

      <div class="m-action">
          <span>
              <el-link href="/blogs">主页</el-link>
          </span>
          <el-divider direction="vertical"></el-divider>
          <span>
              <el-link type="success" href="/blog/add">发表文章</el-link>
          </span>
          <el-divider direction="vertical"></el-divider>
          <span>
            <el-link type="warning" v-show="showUser" @click="logout">退出</el-link>
          </span>
          <span>
            <el-link v-show="!showUser" href="/login">登录</el-link>
          </span>
      </div>
    </div>
</template>

<script>
    import userLogo from '../assets/images/user.jpg'

    export default {
        name: "Header",
        data(){
            return {
                user: {
                    username: '请先登陆',
                    avatar: ''
                },
                userLogo: userLogo,
                showUser: false,
            };
        },
        methods: {
            logout() {
                const _this = this
                _this.$axios.get('/logout', {
                    headers: {
                        'Authorization': _this.$store.state.token
                    }
                }).then(data => {
                    _this.$store.commit('REMOVE_INFO')
                    _this.$router.push('/login')
                });
            }
        },

        created() {
            let userInfo = this.$store.getters.getUser
            if (userInfo.username != null) {
                this.user.username = userInfo.username;
                this.user.avatar = userInfo.avatar;
                this.showUrl = false;
                this.showUser = true
            }
        }
    }
</script>

<style scoped>
    .m-action{margin: 10px 0 }
</style>