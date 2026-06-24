# 学生管理系统后端

## 1. 项目简介

本项目是一个基于 **Spring Boot** 的学生管理系统后端项目，主要用于学习和实践 Java 后端开发中的常见功能，包括学生信息管理、用户登录、数据库版 token 登录态、接口拦截、退出登录、统一返回结果、统一异常处理、MyBatis-Plus 分页查询和接口测试等内容。

项目当前以学习和实战为主，适合作为 Java 后端入门阶段的练习项目。

---

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

---

## 3. 已实现功能

```text
1. 学生列表查询
2. 学生新增
3. 学生修改
4. 学生删除
5. 学生条件查询
6. 学生分页查询
7. 用户登录
8. 数据库版 token 登录态
9. 登录拦截器 LoginInterceptor
10. 退出登录 /logout
11. 统一返回结果 Result
12. 统一异常处理 GlobalExceptionHandler
13. CORS 跨域配置
14. 后端关键流程日志输出
```

---

## 4. 项目结构说明

```text
src/main/java
└── com.attust.mp
    ├── common
    │   ├── Result.java
    │   └── PageResult.java
    ├── config
    │   ├── MybatisPlusConfig.java
    │   └── WebMvcConfig.java
    ├── dto
    │   └── LoginDTO.java
    ├── exception
    │   └── BusinessException.java
    ├── handler
    │   └── GlobalExceptionHandler.java
    ├── interceptor
    │   └── LoginInterceptor.java
    ├── module
    │   ├── controller
    │   │   ├── LoginController.java
    │   │   └── StudentController.java
    │   ├── entity
    │   │   ├── StudentEntity.java
    │   │   ├── SysUserEntity.java
    │   │   └── SysUserTokenEntity.java
    │   ├── mapper
    │   │   ├── StudentMapper.java
    │   │   ├── SysUserMapper.java
    │   │   └── SysUserTokenMapper.java
    │   ├── service
    │   │   ├── StudentService.java
    │   │   └── SysUserService.java
    │   └── service.impl
    │       ├── StudentServiceImpl.java
    │       └── SysUserServiceImpl.java
    └── MpApplication.java
```

> 注：具体结构以项目实际代码为准。

---

## 5. 数据库准备

### 5.1 创建数据库

```sql
CREATE DATABASE demo_stu DEFAULT CHARACTER SET utf8mb4;
```

---

### 5.2 创建用户表 sys_user

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

初始化管理员账号：

```sql
INSERT INTO sys_user(username, password, nickname, role)
VALUES('admin', '123456', '管理员', 'admin');
```

---

### 5.3 创建 token 表 sys_user_token

```sql
CREATE TABLE IF NOT EXISTS sys_user_token (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    token VARCHAR(128) NOT NULL,
    expire_time DATETIME NOT NULL,
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_token (token),
    KEY idx_user_id (user_id),
    KEY idx_expire_time (expire_time)
);
```

字段说明：

```text
id：
主键，自增。

user_id：
登录用户 id，对应 sys_user 表中的 id。

token：
登录成功后由后端生成的登录凭证。

expire_time：
token 过期时间。

status：
token 状态。
1 表示有效。
0 表示失效。

create_time：
创建时间。

update_time：
更新时间。
```

---

### 5.4 创建学生表 student

学生表字段以项目实际 `StudentEntity` 和本地数据库为准。

示例：

```sql
CREATE TABLE IF NOT EXISTS student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT,
    major VARCHAR(100)
);
```

---

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
不要把真实数据库密码、服务器 IP、密钥等敏感信息提交到 GitHub。
```

---

## 7. 启动方式

```text
1. 启动 MySQL。
2. 创建 demo_stu 数据库。
3. 创建 student、sys_user、sys_user_token 数据表。
4. 修改 application.yml 中的数据库连接信息。
5. 在 IDEA 中启动 Spring Boot 主启动类 MpApplication。
6. 使用 Apifox 测试接口。
```

默认访问地址：

```text
http://localhost:8081
```

端口号以项目实际配置为准。

---

## 8. 测试账号

```text
用户名：admin
密码：123456
```

---

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

说明：

```text
登录成功后，后端生成 token，并保存到 sys_user_token 表。
```

---

### 9.2 退出登录接口

```text
POST /logout
```

请求头：

```text
token: 登录接口返回的 token
```

返回示例：

```json
{
  "code": 200,
  "message": "success",
  "data": null
}
```

说明：

```text
退出登录时，后端会将当前 token 的 status 改为 0。
status=0 的 token 不能继续访问学生管理接口。
```

---

### 9.3 学生列表查询

```text
GET /student/list
```

请求头：

```text
token: 登录接口返回的 token
```

---

### 9.4 学生分页查询

```text
GET /student/page?page=1&limit=10
```

请求头：

```text
token: 登录接口返回的 token
```

返回内容包含：

```text
records：当前页数据
total：总条数
size：每页条数
current：当前页
pages：总页数
```

---

### 9.5 新增学生

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

---

### 9.6 修改学生

```text
POST /student/update
```

请求头：

```text
token: 登录接口返回的 token
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

---

### 9.7 删除学生

```text
DELETE /student/delete?id=1
```

请求头：

```text
token: 登录接口返回的 token
```

> 注：具体接口路径以项目实际 `StudentController` 为准。

---

## 10. 登录态说明

本项目当前使用 **数据库版 token 登录态**。

登录流程：

```text
1. 前端请求 POST /login。
2. 后端校验用户名和密码。
3. 校验成功后，后端生成 token。
4. 后端将 token 保存到 sys_user_token 表。
5. 后端将 token 返回给前端。
6. 前端后续访问学生接口时，需要在请求头中携带 token。
```

接口校验流程：

```text
1. 前端访问 /student/** 接口。
2. LoginInterceptor 从请求头中获取 token。
3. 后端查询 sys_user_token 表。
4. 校验 token 是否存在。
5. 校验 token 的 status 是否为 1。
6. 校验 expire_time 是否大于当前时间。
7. 校验通过后放行请求。
8. 校验失败则返回 401。
```

核心判断条件：

```text
token 存在
status = 1
expire_time > 当前时间
```

---

## 11. 异常处理说明

项目中使用 `BusinessException` 表示业务异常，使用 `GlobalExceptionHandler` 统一处理异常返回。

常见异常包括：

```text
用户名不存在
密码错误
token 缺失
token 无效
token 已过期
```

返回示例：

```json
{
  "code": 401,
  "message": "登录已过期或 token 无效",
  "data": null
}
```

---

## 12. 跨域配置说明

项目在 `WebMvcConfig` 中配置了 CORS 跨域，用于后续前端页面调用后端接口。

学习阶段配置示例：

```java
@Override
public void addCorsMappings(CorsRegistry registry) {

    registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(false)
            .maxAge(3600);
}
```

说明：

```text
跨域主要是浏览器限制。
Apifox 测试接口时通常不会被跨域限制。
后续前端页面访问后端接口时，需要后端允许跨域。
```

---

## 13. 当前项目不足

```text
1. 密码目前为明文保存，后续需要加密处理。
2. token 当前保存到 MySQL，后续可使用 Redis 优化。
3. 暂未接入 JWT。
4. 暂未接入 Spring Security。
5. 暂未完成前端页面。
6. 暂未实现角色权限控制。
7. 暂未实现过期 token 自动清理。
```

---

## 14. 后续计划

```text
1. 完善 README 和接口文档。
2. 完成前端登录页。
3. 前端登录成功后保存 token。
4. 前端访问学生接口时携带 token。
5. 完成学生列表页面。
6. 完成学生新增、修改、删除页面。
7. 实现密码加密。
8. 后续学习 JWT、Redis 和权限控制。
```

---