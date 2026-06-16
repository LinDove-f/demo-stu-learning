# 学生管理系统后端

## 1. 项目简介

本项目是一个基于 Spring Boot 的学生管理系统后端项目，主要用于学习和实践 Java 后端开发中的常见功能，包括学生信息管理、用户登录、token 登录态、接口拦截、统一返回结果、统一异常处理、MyBatis-Plus 分页查询等内容。

项目当前以学习和实战为主，适合作为 Java 后端入门阶段的练习项目。

## 2. 技术栈

```text
Java
Spring Boot
Spring MVC
MyBatis-Plus
MySQL
Maven
Apifox
Git / GitHub
```

## 3. 已实现功能

```text
1. 学生列表查询
2. 学生新增
3. 学生修改
4. 学生删除
5. 学生条件查询
6. 学生分页查询
7. 用户登录
8. token 登录态
9. 登录拦截器
10. 统一返回结果 Result
11. 统一异常处理 GlobalExceptionHandler
```

## 4. 项目结构说明

```text
src/main/java
└── com.attust.mp
    ├── common
    │   ├── Result.java
    │   └── TokenStore.java
    ├── config
    │   ├── MybatisPlusConfig.java
    │   └── WebMvcConfig.java
    ├── controller
    │   ├── LoginController.java
    │   └── StudentController.java
    ├── dto
    │   └── LoginDTO.java
    ├── entity
    │   ├── StudentEntity.java
    │   └── SysUserEntity.java
    ├── exception
    │   └── BusinessException.java
    ├── handler
    │   └── GlobalExceptionHandler.java
    ├── interceptor
    │   └── LoginInterceptor.java
    ├── mapper
    │   ├── StudentMapper.java
    │   └── SysUserMapper.java
    ├── service
    │   ├── StudentService.java
    │   └── SysUserService.java
    └── service.impl
        ├── StudentServiceImpl.java
        └── SysUserServiceImpl.java
```

具体结构以项目实际代码为准。

## 5. 数据库准备

创建数据库：

```sql
CREATE DATABASE demo_stu DEFAULT CHARACTER SET utf8mb4;
```

创建用户表：

```sql
CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    role VARCHAR(30) DEFAULT 'admin',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_username (username)
);
```

插入管理员账号：

```sql
INSERT INTO sys_user(username, password, nickname, role)
VALUES('admin', '123456', '管理员', 'admin');
```

学生表请根据项目实际字段创建。

## 6. 配置文件说明

在 `application.yml` 或 `application.properties` 中配置数据库连接。

示例：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/demo_stu?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: 你的数据库密码
    driver-class-name: com.mysql.cj.jdbc.Driver
```

注意：

```text
数据库名、用户名、密码需要改成自己的本地配置。
```

## 7. 启动方式

第一步，启动 MySQL。

第二步，确认数据库和数据表已经创建。

第三步，在 IDEA 中启动 Spring Boot 主启动类。

第四步，打开 Apifox 测试接口。

默认访问地址：

```text
http://localhost:8081
```

端口号以项目实际配置为准。

## 8. 测试账号

```text
用户名：admin
密码：123456
```

## 9. 主要接口说明

### 9.1 登录接口

```text
POST /login
```

请求体：

```json
{
  "username": "admin",
  "password": "123456"
}
```

返回示例：

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "token": "一串 token",
    "username": "admin",
    "nickname": "管理员",
    "role": "admin"
  }
}
```

### 9.2 学生列表查询

```text
GET /student/list
```

请求头：

```text
token: 登录接口返回的 token
```

### 9.3 学生分页查询

```text
GET /student/page?page=1&limit=10
```

请求头：

```text
token: 登录接口返回的 token
```

### 9.4 新增学生

```text
POST /student/add
```

请求头：

```text
token: 登录接口返回的 token
```

请求体示例：

```json
{
  "name": "张三",
  "age": 20,
  "major": "软件工程"
}
```

### 9.5 修改学生

```text
POST /student/update
```

请求体示例：

```json
{
  "id": 1,
  "name": "张三",
  "age": 21,
  "major": "软件工程"
}
```

### 9.6 删除学生

```text
DELETE /student/delete?id=1
```

具体接口路径以项目实际 Controller 为准。

## 10. 登录态说明

用户登录成功后，后端会生成 token 并返回给前端。

前端后续访问学生管理接口时，需要在请求头中携带 token：

```text
token: 登录接口返回的 token
```

后端通过 `LoginInterceptor` 校验 token。

如果 token 缺失或无效，会返回：

```json
{
  "code": 401,
  "message": "未登录，请先登录",
  "data": null
}
```

## 11. 当前项目不足

```text
1. token 当前保存在内存中，项目重启后会失效。
2. 密码目前是明文保存，后续需要加密。
3. 暂未接入 Spring Security。
4. 暂未接入 Redis。
5. 暂未完成前端页面。
6. 暂未实现角色权限控制。
```

这些问题会在后续学习中逐步优化。

## 12. 后续计划

```text
1. 前端登录页
2. 学生列表页面
3. 新增、修改、删除页面
4. JWT 登录态
5. Redis 保存 token
6. 密码加密
7. 角色权限控制
8. 项目部署
```

---