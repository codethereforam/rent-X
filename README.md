# rent-X
分布式校园租赁系统


## 项目概览

### 背景
大学生刚进入学校，不免会尝试一些新的东西，但并不是每样都是适合自己，租一件物品尝试一下可能好多人都想过。因此，一个可靠的校园租赁系统可以解决很多同学这个问题。
 
### 概述
项目基于Java Web，前端用网页展现。该系统包括两个子系统：一是后台管理系统，供管理员使用。二是用户租赁系统，供出租人和承租人使用。非注册用户可以浏览可租赁物品信息。

### 特点
1. 分布式：为了解藕和提高扩展性，将系统拆分，普通用户租赁系统和后台管理系统分开部署，用户租赁系统封装出基础服务接口，使用Dubbo供后台管理系统远程方法调用，提高代码复用性。为保证可用性，普通用户租赁系统和后台管理系统都分别使用Docker集群部署（项目中暂无此内容）。
2. 高安全性：考虑到系统可能遭到黑客攻击，从而导致系统宕机和用户隐私泄漏，系统对常见Web攻击做出防范。使用AOP技术进行XSS防御和敏感词过滤；使用验证码防暴力破解；使用预编译SQL防SQL注入；数据库存储经过bcrypt算法处理过的用户密码的哈希值防密码泄漏；使用SpringSecurity做角色权限控制。

## 系统分析

### 用例图
1. 用户租赁系统用例图

    ![用户租赁系统用例图](/project-docs/images/用户租赁系统用例图.png)

2. 后台管理系统用例图

    ![后台管理系统用例图](/project-docs/images/后台管理系统用例图.png)

### 功能需求
#### 公共模块
基于AOP的安全防御模块：使用AOP进行XSS防御和敏感词过滤。考虑用户上传租赁物品信息等场景，用户可能上传攻击脚本和敏感词，系统对敏感词和XSS攻击做出防范。使用面向切面编程技术，将安全防御的代码从业务代码中分离，对于需要防御的地方只需要加上注解，使用切面拦截做安全处理，从而减少代码侵入性。
#### 用户租赁系统
1. 登录注册模块：
    - 用户注册需要填写基本信息，使用邮箱注册。数据库存储经过bcrypt算法处理过的用户密码的哈希值，从而减少系统被拖库后用户密码暴露风险。
    - 用户登录使用验证码防止用户暴力破解。
2. 租用模块 - 承租人：
    - 开始租用：用户可查看可租物品信息。用户对未被租用物品可执行租赁操作。
    - 我的租用：用户可查看自己已租赁的物品清单，可执行取消申请租赁操作。
3. 出租模块 - 出租人：
    - 开始出租：出租人可以在相应的类别添加、修改自己要出租的物品信息。
    - 我的出租：出租人可以查看自己的出租物品，还可以查看单个物品租用历史以及当前租用情况。
4. 近实时的全文搜索模块：为提高大量物品信息搜索速度，解决数据库查询瓶颈，使用搜索引擎Elasticsearch强化搜索功能，对物品信息提供全文搜索功能，并可按类别筛选。
5. 在线支付模块：对于租金、押金支持支付宝在线支付和查看支付状态，通过调用支付宝开放接口实现。
6. 服务接口封装：封装用户租赁系统中通用接口，供后台管理系统RPC调用。
#### 后台管理系统
1. 登录注册模块：后台管理员帐号密码由运维人员使用脚本手动存入数据库，登录功能复用用户租赁系统的部分代码。
2. 角色权限管理：角色分为三类，分别管理员、出租人、承租人。管理员只能使用“后台管理系统”，出租人、承租人只能使用“用户租赁系统”。
    系统使用Spring Security权限框架，把每个单独的权限和功能（URL+HTTP方法）挂钩。用户访问系统的某一个URL的时候，系统首先检查用目标URL是否和功能权限绑定，判断绑定了在校验用户的角色是否包含指定权限。
3. 用户管理模块：管理员可以查看、修改用户信息。（暂未实现）
4. 类别管理模块：管理员可以查看已有类别、修改类别、添加新类别和删除类别等。
5. 物品管理模块：管理员可以在相应的类别下添加或修改物品信息，还可以查看物品信息和执行删除物品的操作。（暂未实现）
6. 租用管理模块：管理员可以查看用户提交的租用申请信息，可批准和否定用户的租赁申请。当用户归还物品后，管理员可以执行归还操作。
7. 统计报表模块：管理员可查看按类别时间查看物品租用情况的统计报表。
8. 实时通信模块：基于Websocket实现实时通讯，用户可以和客服沟通租赁、支付等操作中出现的问题，出租人和承租人之间可在线沟通。（简单实现）

## 系统设计
### 架构图
![架构图](/project-docs/images/架构图.png)
### 功能模块图
![功能模块图](/project-docs/images/功能模块图.png)
### 数据库ER图
1. 用户、物品、类别、物品ER图

![用户、物品、类别、物品ER图](/project-docs/images/用户、物品、类别、物品ER图.png)

2. 用户、角色、资源、菜单ER图

![用户、角色、资源、菜单ER图](/project-docs/images/用户、角色、资源、菜单ER图.png)
    
## 技术选型
### 后端技术
Spring Framework、SpringMVC、SpringBoot、MyBatis、Dubbo、Redis、Elasticsearch、Spring Security、Thymeleaf、Mysql、Websocket
### 前端技术    
Bootstrap、jQuery

## 项目结构
```
├── project-database -- 数据库脚本
├── project-docs -- 项目文档
│   ├── application-private.yml.template -- 配置文件模板
│   ├── businessLogicAnalysis.md -- 业务逻辑分析文档
│   ├── businessLogicDesign.md -- 业务逻辑设计文档
│   ├── convention.md -- 项目约定文档
│   ├── development.md -- 开发文档
├── project-tools
│   └── mysql-es-sync -- mysql和elasticsearch数据同步工具
├── rentx-admin* -- 后台管理系统代码
├── rentx-common -- 后台管理系统和用户租赁系统公共代码
├── rentx-generator -- 代码生成器
├── rentx-web* -- 用户租赁系统代码
```
    
## 项目运行
1. 安装Jdk1.8、redis、Elasticsearch、Logstash，并使用默认端口
1. 配置系统环境变量，数据库密码(MYSQL_PASSWD)
1. 将"project-docs"目录下"application-private.yml.template"文件复制到"rentx-web/src/main/resources"目录下，重命名"application-private.yml"，并修改其配置
1. 运行"project-database"目录下rentx-web.sql和rentx-admin.sql文件
1. 生产环境配置dubbo注册中心
1. 启动项目 
    - 开发：注释掉根pom文件中的"<goal>compile</goal>"一行，然后可以以任何方式启动
    - 非开发：只能用maven方式启动，cd rentx-web & mvn spring-boot:run; cd rentx-admin & mvn spring-boot:run
    
## 使用说明
### 注册

![注册](/project-docs/images/register.png)

### 出租人使用
1. 查看已租物品
2. 开始出租（出租人可以在相应的类别添加、修改自己要出租的物品信息）

    ![开始出租](/project-docs/images/start-rent-out.png)

3. 我的出租（出租人可以查看自己的出租物品，还可以查看单个物品的当前租用情况）

    ![我的出租](/project-docs/images/my-rent-out.png)

4. 搜索

    ![搜索](/project-docs/images/search.png)

5. 聊天

    ![聊天](/project-docs/images/chat.png)

### 承租人使用
1. 开始租用

    ![开始租用](/project-docs/images/start-rent-in.png)

2. 我的租用

    ![我的租用](/project-docs/images/my-rent-in.png)

### 管理员使用
1. 类别管理

    ![类别管理](/project-docs/images/category.png)

2. 租用管理

    ![租用管理](/project-docs/images/rent-manage.png)

3. 租用日统计

    ![租用日统计](/project-docs/images/daily-stats.png)

### 游客使用
游客用户输入用户名guset，密码guest即可登录，登录后可以查看可租用的物品列表，但不能执行租用操作
    
## 许可证
[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)