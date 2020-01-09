# aquarius

## 功能点介绍

1.日志邮件报警
2.durid后台监控 
3.mock数据



## 1.日志邮件报警
记得修改自己的邮件账户和密码

## 2.durid后台监控 

> url:http://localhost:8080/druid/
> 用户名:admin
> 密码:123456

## 3.mock数据

```sql
CREATE TABLE `t_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` char(1) NOT NULL COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近访问时间',
  `sex` char(1) DEFAULT NULL COMMENT '性别 0男 1女',
  `theme` varchar(10) DEFAULT NULL COMMENT '主题',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `t_request` (
  `request_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '请求id',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
  `req_url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '请求url',
  `reponse` varchar(20000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '请求返回参数',
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
```

请求url域名替换为localhost:8080/api/
req_url:填写域名后的uri
reponse:填写返回json字串

例如:http://hire.t.ziroom.com/annualPayment/findOwnerContractInfo
请求:localhost:8080:/api/annualPayment/findOwnerContractInfo
req_url:/annualPayment/findOwnerContractInfo
reponse:{"error_code":0,"error_message":"成功","status":"success","data":{"bankCardBrname":"中国农业银行股份有限公司"}}

## 4.部署
nohup java -jar aquarius-0.0.1-SNAPSHOT.jar --server.port=8086  > log.file  2>&1 &



## 5.对接swagger2

### 1.添加 Swagger 依赖

```xml

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
```

### 2.Swagger Java 配置

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
//验证: http://localhost:8080/v2/api-docs
```

### 3.添加 Swagger UI 依赖

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
<!--验证:http://localhost:8080/swagger-ui.html-->
```

