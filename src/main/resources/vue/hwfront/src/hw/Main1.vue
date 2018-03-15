<template>
  <div id="app">

    <el-container>
      <el-header>Header</el-header>
      <el-container>
        <el-aside width="200px">
          <nav-menu></nav-menu>
        </el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>

  </div>
</template>

<script>
import NavMenu from '@/hw/NavMenu'

export default {
  name: 'app',
  components: {
    'nav-menu': NavMenu
  }
}

</script>

<style>

</style>