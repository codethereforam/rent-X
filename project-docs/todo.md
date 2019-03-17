# todo

## technology
### primary
- 权限
    - 出租人有查看开始租用的权限
    - 无权限报错
    - 按钮显示
    - 用户、角色、资源、菜单管理页面
- 统一异常处理：404，无权限
- 文档
    - 代码生成器
- 基础业务
    - 后台管理系统
        - 类别
            - 修改校验重复
            - parentId level 降级校验
            - 优化删除
            - 存到redis
        - 用户管理
        - 物品管理
    - 用户租赁系统
        - 我的出租 - 编辑、租用历史
- 登录、注册
    - 后台管理系统登录
    - session
    - 忘记密码 找回密码
    - 从后台单点登录到用户租赁系统
- 实时通信（初步）
    - XSS防御
- 告警（初步）
- 集群部署
- 图片上传
### secondary
- spring security 用户信息存session
- redirectattribute web和admin 序列化
- websocket权限
- mybatis-plus逻辑删除
- 统一异常处理
- 事务配置
- 角色权限: PasswordEncoder BCryptPasswordEncoder
- session
- 统一trim
- 解决maven重复依赖
- 优化测试用例
- XSSAspect缓存
- XSSAspect和th:text不兼容
- VO的get被重复调用场景（考虑dubbo）
- spring bean生命周期
### knowledge
- webjars
- mbp CRUD原理
- datetime和timestamp
- devtool
- Actuator
- fluent-validation原理
- spring boot
    - ApplicationContextAware
- 泛型（response）
- java assist & 
- assertj & google truth
- lombok原理
- cglib和jdk proxy
- spring security
    - frameOptions
    - csrf
- controller测试
- spring social
- recaptcha 
- 2fa
- base64图片
- spring session和tomcat（jetty） session
- proxy-target-class

## learnt
- log
- mbp generator
- thymeleaf
- logback
- redis template
- jmh
- jackson
- dubbo
- Spring Boot
- elasticsearch
- spring security
- 角色权限设计
- Java SPI

## experience