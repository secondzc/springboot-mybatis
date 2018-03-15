import Vue from 'vue'
import Router from 'vue-router'
import Element from 'element-ui'

import HelloWorld from '@/components/HelloWorld'
import UserInfo from '@/hw/UserInfo'
import Main from '@/hw/Main1'
import Note from '@/hw/Note'

Vue.use(Router)
Vue.use(Element)

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    {
    	path: '/',
    	name: 'Main',
    	component: Main,
    	children:[
    	{
    	   path: '/UserInfo',
    	   name: 'UserInfo',
    	   component: UserInfo	
    	},
    	{
    		path: '/Note',
    		name: '/Note',
    		component: Note
    	}
    	]
    }
  ],
})
