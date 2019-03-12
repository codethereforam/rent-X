# 项目约定

## 描述

*约定优先于配置*

## 编程规约

- 严格按照《阿里巴巴Java开发手册》，使用[p3c插件](https://github.com/alibaba/p3c)检查

## 文档约定

- 全部使用`.md`格式

## 版本控制

- Git提交信息参考[Angular Js Git Commit Guidelines](https://github.com/angular/angular.js/blob/master/DEVELOPERS.md#commits)

## 分层领域模型规约
- DTO(Data Transfer Object) :数据传输对象, Service 或 Manager 向外传输的对象。（数据库查出来的对象，以及service层封装的对象）
- Param: controller接受参数的对象
- VO(View Object) :显示层对象,通常是 Web 向模板渲染引擎层传输的对象。
- Query :数据查询对象,各层接收上层的查询请求。注意超过 2 个参数的查询封装,禁止
使用 Map 类来传输。