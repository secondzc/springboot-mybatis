<template>
<div class="main">
      <!--头部-->
      <el-row>
          <!--头部左边-->
          <el-col :span="4">
            <div class="main-top main-top-left">
              <h1>xx管理系统</h1>
            </div>
            <!--头部右边-->
          </el-col>
          <el-col :span="20">
            <div class="main-top main-top-right">
              <h2>{{ username }},欢迎您!!!</h2>
            </div>
          </el-col>
      </el-row>
      <!--中部-->
      <el-row>
         <!--中部左边-->
        <el-col :span="4">
          <div class="main-middle-left">
            <el-menu default-active="2" class="main-middle-left-menu"  theme="dark" router>
              <el-menu-item index="/UserInfo">我的信息</el-menu-item>
              <el-menu-item index="/Note">我的留言</el-menu-item>
            </el-menu>
            <el-menu default-active="2" class="el-menu-vertical-demo"  theme="dark" >   
             <el-menu-item index="1">
              退出系统
               </el-menu-item>
               
             </el-menu>
          </div><!-- 中部左边部分结束-->
        </el-col>

         <!--中部右边-->
      <div class="main-middle-right">
      <transition name="fade" mode="out-in">
							<router-view></router-view>
			</transition>
      </div>
      
      </el-row>
     
</div>
</template>
<script>
export default {
  data () {
    return {
      // isrouter:true,
      username:'zcy'
    }
  },
  methods:{
      // end(){
        
      //   this.$confirm('确定退出系统吗？','提示',{})
      //   .then(() =>{
      //     this.$store.state.username='游客';
      //      this.$router.push('/');
      //   }).catch(() =>{

      //   });
      // },
      //  isUser(){
      //    if(this.$store.state.username=='游客'){
      //     this.$alert('对不起，请先登录', '提示', {
      //     confirmButtonText: '去登录',
      //     type: 'warning'
      //   }).then(() => {
      //     this.$router.push('/')
      //   }).catch(() => {    
      //   });
      //     }

      //     }
  },
  mounted(){
      //this.isUser();
      //this.$router.push({ path: '/UserInfo' });
  }

}
</script>

<style>
 
  .main-top {
    height: 60px;
    background:#20A0FF;
  }

  .main-top-left,.main-top-right {
    border-left:1px dotted #e5e9f2;
    border-radius: 1px;
    min-height: 36px;
  }
  h1{
    font-family:Georgia;
    color: black;
  }
  h2{
    color:saddlebrown
  }
  .main-middle-left{
    background: #48576A;
    height: 590px;
    
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }

  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

 
</style>