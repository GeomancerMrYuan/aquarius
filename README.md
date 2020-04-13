

## aquarius

### 一.swagger2

#### 1.swagger简介

Swagger 是一套基于 OpenAPI 规范构建的开源工具，可以帮助我们设计、构建、记录以及使用 Rest API。Swagger 主要包含了以下三个部分：

- Swagger Editor：基于浏览器的编辑器，我们可以使用它编写我们 OpenAPI 规范。
- Swagger UI：它会将我们编写的 OpenAPI 规范呈现为交互式的 API 文档，后文我将使用浏览器来查看并且操作我们的 Rest API。
- Swagger Codegen：它可以通过为 OpenAPI（以前称为 Swagger）规范定义的任何 API 生成服务器存根和客户端 SDK 来简化构建过程。

#### 2.springBoot整合swagger2

```java
//1.添加swagger2相关依赖
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>

//2.swagger Java配置
@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "swagger.enable", havingValue = "true")
public class SwaggerConfig{

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("acquarius")
                        .description("水瓶座的接口文档")
                        .version("v2020.04")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ziroom.aquarius.system.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}

//3.填写测试代码,验证:http://localhost:8080/swagger-ui.html
@Controller
@RequestMapping("/user")
@ResponseBody
@Slf4j
@Api(tags="用户信息")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    @ApiOperation(value="测试", notes="测试")
    public String getIndex(Long userId) {
        return "测试成功";
    }
}
//4.更多注解见:https://segmentfault.com/a/1190000010465989

```

###  二.MyBatis-Plus

#### 1.简介

[MyBatis-Plus](https://github.com/baomidou/mybatis-plus)（简称 MP）是一个 [MyBatis](http://www.mybatis.org/mybatis-3/) 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。[https://mp.baomidou.com/guide/#%E7%89%B9%E6%80%A7](https://mp.baomidou.com/guide/#特性)

#### 2.SpringBoot整合MyBatis-Plus

**添加依赖**

```xml
<!--1.数据库连接-->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.16</version>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>druid-spring-boot-starter</artifactId>
  <version>1.1.10</version>
</dependency>

<!--2.添加mybatis-plus依赖-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.1.tmp</version>
</dependency>

<!--3.添加代码生成器依赖-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.3.1.tmp</version>
</dependency>
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-engine-core</artifactId>
    <version>2.2</version>
</dependency>
```

**添加配置文件**

```yml
#数据库及连接池的配置
spring:
  profiles:
    active: dev
  datasource:
    #数据源基本配置
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      initial-size: 5
      min-idle: 5
      max-active: 20
      max-wait: 60000
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 300000
      validation-query: SELECT 1 FROM DUAL
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      pool-prepared-statements: true
      filters: stat,wall,logback
      max-pool-prepared-statement-per-connection-size: 20
      use-global-data-source-stat: true
      connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
  messages:
    encoding: UTF-8
    basename: i18n.login
  thymeleaf:
    cache: false
#mybatis plus日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl

```

**Java代码**

```java
//1.配置 MapperScan 注解
@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);
    }

}

//2.Java代码
public class CodeGenerator {
   
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("/Users/yuanpeng/IdeaProjects/aquarius/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setAuthor("yuanpeng");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://localhost:3306/aquarius?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.ziroom.aquarius");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);

        //代码执行
        mpg.execute();
        System.out.println("生成成功");
    }

}
```



#### 3.测试执行

![image-20200408165426278](/Users/yuanpeng/Library/Application Support/typora-user-images/image-20200408165426278.png)





### 三.通过Validator + 自动抛出异常来完成了方便的参数校验

首先在入参里需要校验的字段加上注解,每个注解(各个注解作用百度)对应不同的校验规则，并可制定校验失败后的信息,示例:

![image-20200411110621080](/Users/yuanpeng/Library/Application Support/typora-user-images/image-20200411110621080.png)

接下来只需要在接口需要校验的参数上加上@Valid注解,示例:

![image-20200411112455269](/Users/yuanpeng/Library/Application Support/typora-user-images/image-20200411112455269.png)

最后定义一个全局异常,处理MethodArgumentNotValidException异常,示例:

![image-20200411114420084](/Users/yuanpeng/Library/Application Support/typora-user-images/image-20200411114420084.png)

测试效果展示:

![image-20200411173838262](/Users/yuanpeng/Library/Application Support/typora-user-images/image-20200411173838262.png)



### 四.通过全局异常处理 + 自定义异常完成了异常操作的规范

编写一个自定义异常类CustomerException,示例:

![image-20200411175107471](/Users/yuanpeng/Library/Application Support/typora-user-images/image-20200411175107471.png)

全局异常处理类,处理自定义异常:

![image-20200411175617603](/Users/yuanpeng/Library/Application Support/typora-user-images/image-20200411175617603.png)

测试:

![image-20200411180022972](/Users/yuanpeng/Library/Application Support/typora-user-images/image-20200411180022972.png)

### 五.通过数据统一响应完成了响应数据的规范

相应数据格式如下:

![image-20200411215146420](/Users/yuanpeng/Library/Application Support/typora-user-images/image-20200411215146420.png)

状态码枚举:

