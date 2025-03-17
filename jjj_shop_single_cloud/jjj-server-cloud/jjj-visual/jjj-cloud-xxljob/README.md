- 修改数据库连接

  jjj-cloud-xxljob\src\main\resources\application.yml
  
- 启动项目

  jjj-cloud-xxljob\src\main\java\com\xxl\job\admin\XxlJobAdminApplication.java
  
 - 访问项目
  http://127.0.0.1:7004/xxl-job-admin/toLogin
  admin/123456
   
   
   
 概念说明
 1、手工创建执行器，AppName对应服务名字  比如： jjjsingle-system
 2、手工创建定时任务，选择执行器（服务）、JobHandler对应XxlJob的值
