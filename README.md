# 最新
## 注意：关于部署，现在改为使用docker部署，但是使用docker部署时，如果你用的是docker中的es和mysql，他们的地址绝对不能用localhost和127.0.0.1,这样会找不到的，使用内网分配的地址才行。
# blog_vue_server

<h2>关于分支<h2/>
<br/>
<h2> 
master:最新内容

springcloud_netflix:springcloud netflix版本，目前已经基本完善

springcloud_alibaba:springcloud alibaba版本，目前已经完善

springbootVersion:单机版springboot版本
<h2/>

博客系统的后端
<h2>使用springcloud Finchley.SR1、springboot2.0.1、mybatis、elasticsearch 6.4.2、druid、mysql5.7<h2/>

本系统的前端地址 <a href="https://github.com/sustly/blog_vue_web">前端传送门</a>


springcloud版本

我的云服务器1G运行内存，运行不起来整个springcloud项目，所以在云服务器上运行的是以前的版本，
有兴趣的同学可以搞个运行内存大一些的云服务器

一、整体框架介绍

1.blog_eureka

    使用eureka作为注册中心，注册了网关、服务提供者、服务消费者、服务发现等组件
    
2.blog_admin_provider_hystrix

    用户登录注册服务
    
3.blog_article_provider_hystrix

    文章管理、获取、查找等功能服务
    
4.blog_api

    一些共用的实体类、api等
    
5.blog_consumer_hystrix_dashboard

    服务监控
    
6.blog_zuul

    网关
    
7.blog_consumer_feign

    基于feign负载均衡的消费者服务
    
