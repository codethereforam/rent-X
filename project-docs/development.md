# 开发文档

1. 项目运行
    1. 配置系统环境变量，数据库密码(MYSQL_PASSWD)
    2. 生产环境配置dubbo注册中心
    3. cd rentx-web & mvn spring-boot:run; cd rentx-admin & mvn spring-boot:run
2. rentx库中add_user_id和update_user_id若是负数，则代表用户是rentx_admin库中的用户id
3. 使用AOP防御XSS攻击
    - 所有接口必须返回BaseVO的子类或者包装的BaseVO子类
    - 所有BaseVO子类种的'String getXxx()'或'String[] getXxx()'被调用时拦截，做html转义处理
    - 如果方法有@MuteXss注解，则不处理
    - VO不要再非接口返回值以外的地方使用
4. 本项目XSS防御思路
    ```
    一些前端技术可以通过特殊符号转义的方式防御XSS攻击，本项目使用的模板引擎thymeleaf本身也可以防御XSS攻击，
    因为这些原因后端就不用做XSS防御了吗？
    后端不应该依赖于前端，因为
        1. 前端使用的框架可能有bug
        2. 前端技术复杂多样，一个后端接口前端可能多次调用并用不同的技术以不同的方式展现，这就加大了前端存在漏洞的风险，而且前端每种技术都防御一下，也很麻烦
        3. 后端接口有的会封装为服务，被第三方调用，XSS漏洞风险应该又后端自己负责，而不是别人
     使用AOP方式做防御，可在一处做统一处理，减小问题发生点
    ```
5. 初始用户角色权限关系
    - 角色 - 用户（用户名/密码，admin/admin用户拥有出租人和承租人角色）
        - ROOT用户：root/root
        - 出租人：u1/p1, u2/p2
        - 承租人：u3/p3, u4/p4
        - 游客用户：guest/guest
    - 角色 - 资源
        - ROOT用户：任何请求，比如查看url + .json，方便快速开发，供开发人员使用
        - 出租人：开始出租，我的出租，聊天，搜索，开始租用
        - 承租人：开始租用，我的租用，聊天，搜索
        - 游客用户：首页, 开始租用页面
6. RoleEnum需要和role表保持一致