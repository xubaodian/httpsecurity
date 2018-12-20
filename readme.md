## Spring boot，Spring Seesion，redis实现登录验证及共享session及接口权限管理
项目git地址：https://github.com/xubaodian/httpsecurity.git<br>
代码的细节就不展示了，需要的可以直接去git下载该项目，介绍下项目的创建过程，中间穿插一些代码和思路讲解，
使大家可根据这个可以自己一步一步创建工程。<br>
项目创建过程如下：<br>
**1、首先创建spring boot项目，添加thymeleaf模板支持。**<br>
现在，spring boot就是web工程了，支持rest接口，html页面处理，
如果不知道怎么创建spring boot，先学习一下。<br>

**2、安装redis**<br>
linux安装就不解释了，windows的官方网站没有可用包，下面是resdis windows版下载地址：<br>
https://github.com/ServiceStack/redis-windows<br>
下载后启动redis-server.exe，即可启动redis，没有配置的话，默认启动端口是6379。<br>

**3、添加spring-session 和redis的依赖, 和redis配置。**
```
       <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
            <version>5.1.3.RELEASE</version>
        </dependency>
```
在application.properties文件加入如下redis配置：
```
# Redis服务地址.
spring.redis.host=localhost
# Redis服务端口号.
spring.redis.port=6379

# Session存储类型.
spring.session.store-type=redis
# session有效期设置
server.servlet.session.timeout=600s
# Sessions flush mode.
spring.session.redis.flush-mode=ON_SAVE
# 存储session key的命名空间
spring.session.redis.namespace=spring:session
```
修改服务端口号，启动两个该项目，两个项目之间session都存储在redis中，是共享的。<br>

**4、登录验证和权限管理**<br>
可以通过验证token的方式，判断用户是否登录。加入我们有登录接口如下(这是一段伪代码)：
```
    @PostMapping(value = "/login")
    public @ResponseBody AjaxReult login(HttpServletRequest req,  HttpServletResponse res, 
           @Param("username") String username, @Param("password") String password) {
    //验证username和password,通过则发放token
       if(通过) {
          //token写入session
          req.getSession().setAttribute("token", "这是token字符串");
       } else {
          //返回失败信息
       }
    }
```
添加spring boot过滤器（filter），此过滤验证所有请求，在过滤器中获取token，判断用户是否登录。
当然有一部分请求不需要登录的，这部分直接放行。<br>
项目中的：LogonFilter类就是filter，验证登录和权限的。

**5、权限认证**
可以发放token，判断是否登录，当然也可以发放一个权限字符串，判断用户是否登录，工程里有详细的注释，建议大家把工程下载下来，
看下代码，并启动工程。整个流程自然就清楚了。<br>
如有疑问，可留言给我，或发邮件至472784995@qq.com。欢迎大家一起来讨论学习。