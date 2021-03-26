# spring boot

* spring boot 实现了自动装配(auto-configuration),actuator监控，cli命令行接口，starter依赖。
* https://shimo.im/mindmaps/jCtRWQvct6wCPdqK/

## config

### 自定义属性

* 将配置写入到application-dev.yml 文件
* 将配置文件的属性赋值给实体类,如Lucy类
    * 需要在Lucy实体类中添加注解 @ConfigurationProperties
    * 同时引入 spring-boot-configuration-processor依赖
    * application需要开启EnableConfigurationProperties注解。

* 注意：bootstrap.yml 文件要想加载进来，需要加入spring-cloud-context的依赖才可以。

### 自定义配置文件

* 自定义配置文件 lucy.properties,测试发现自定义的文件文件格式是yml会加载不了。
* 实体bean自定义引入 @PropertySource(value ="classpath:test.properties") @configuration

## spring boot启动原理

## 自动装配机制

