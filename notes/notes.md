# 该文件为本项目的笔记
## 前后端的连接
在 Spring Boot 中配置 CORS
如果你决定在 Spring Boot 中配置 CORS，下面是一个简单的例子，允许来自任何来源的请求（仅适用于开发阶段，请根据生产需求调整）：

```java


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // 允许所有来源
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // 允许的方法
            }
        };
    }
}
```
在 Vue CLI 中设置代理
如果你使用的是 Vue CLI 构建工具，那么可以在项目的根目录下创建或编辑 vue.config.js 文件，添加如下代码：

```javascript

module.exports = {
devServer: {
proxy: {
'/api': {
target: 'http://localhost:8081', // 后端地址
changeOrigin: true,
pathRewrite: {
'^/api': '' // 重写路径，去掉 /api 前缀
}
}
}
}
};
```
现在，当你从 Vue 发起 /api 开头的请求时，这些请求会被代理到 http://localhost:8081 上对应的路径。


user表的sql语句：
-- 创建数据库 boke
CREATE DATABASE IF NOT EXISTS boke;

-- 使用新创建的数据库
USE boke;

-- 创建 users 表
CREATE TABLE IF NOT EXISTS users (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) UNIQUE NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,
password_hash VARCHAR(255) NOT NULL,
role ENUM('user', 'admin', 'moderator') DEFAULT 'user',
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
last_login TIMESTAMP
);

-- 可选：插入一条管理员用户的记录（仅用于初始化）
INSERT INTO users (username, email, password_hash, role)
VALUES ('admin', 'admin@example.com', '$2a$10$DvnUJpX74f9Q6Z3tH7z7Ee8nL5bYzZ5WqKwRjPmOyMvBZGzg9xTqS', 'admin')
ON DUPLICATE KEY UPDATE id=id; -- 如果已经存在则不插入

-- 注意：上面的密码哈希值是示例，实际使用时应替换为通过安全哈希算法生成的真实密码哈希。