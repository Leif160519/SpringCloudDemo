# 效果图(请忽略浏览器中的URL地址)
## 1. 注册中心
![](/doc/images/eureka-server.png)

## 2. 微服务
### 2.1 微服务接口调用
![](/doc/images/eureka-client.png)
### 2.2 服务状态监控
![](/doc/images/actuator.png)
### 2.3 服务健康状态
![](/doc/images/health.png)
### 2.3 熔断器主界面
![](/doc/images/hystrix.png)
### 2.4 熔断器检测界面
![](/doc/images/hystrix.stream.png)

## 3. Spring Boot Admin
### 3.1 Login
![](/doc/images/login.png)
### 3.2 Wallboard
![](/doc/images/wallboard.png)
### 3.3 Applications
![](/doc/images/applications.png)
### 3.4 Journal
![](/doc/images/journal.png)
### 3.5 Details
![](/doc/images/details.png)
### 3.6 Metrics
![](/doc/images/metrics.png)
### 3.7 Environment
![](/doc/images/environment.png)
### 3.8 Logfile
![](/doc/images/logfile.png)
### 3.9 Loggers
![](/doc/images/loggers.png)
### 3.10 JMX
![](/doc/images/jmx.png)
### 3.11 Threads
![](/doc/images/threads.png)
### 3.12 Logout
![](/doc/images/logout.png)
### 3.13 服务上下线邮件通知
![](/doc/images/email.png)

## 4.Spring Cloud Config
### 4.1 config-server
#### *application.yml*
```
server:
  port: 8767
spring:
  cloud:
    config:
      server:
        git:
          uri: http://192.168.3.233/nanjing-springcloud/applicationconfig.git #仓库地址
          search-paths: dev #仓库下的子目录
          username: root #gitlab用户名
          password: MECT888! #gitlab密码
```

> 通过/{application}/{profile}就能访问配置文件:application表示配置文件的名字，对应我们上面的配置文件就是application(配置文件统一前缀),profile表示环境(配置文件后面的文件名)。举例：启动配置中心后通过访问： `http://192.168.81.110:8767/application/test/` 即可访问配置文件内容

### 4.2 config-client
#### *bootstrap.yml*
```
spring:
  application:
    name: config-client
  cloud:
    config:
      #service-id: config-server #需要注册中心的支持
      uri: http://192.168.81.110:8767 #不需要注册中心支持
      name: application #代表文件前缀
      label: master #分支
      profile: test #如果是其他yml，写全名即可，这里的test代表application-test.yml(也代表test.yml)
```

#### *application.yml*
```
server:
  port: 8766
```

#### gitlab上配置文件 *application-test.yml*
```
server:
  port: 9999
```

>分别启动eureka-server、config-server、config-client，访问 `192.168.81.110:8767/application/test/` 得到以下显示结果：

```
{
    "name": "application",
    "profiles": [
        "test"
    ],
    "label": null,
    "version": "c5356fe61e0f2bb8eab7b90323be75ffc1b192db",
    "state": null,
    "propertySources": [
        {
            "name": "http://192.168.3.233/nanjing-springcloud/applicationconfig.git/dev/application-test.yml",
            "source": {
                "server.port": 9999
            }
        }
    ]
}
```
> 当显示正确的git地址过后即表明配置中心生效，这时访问 `http://192.168.81.110:9999/actuator` 即可访问

```
{
    "_links": {
        "self": {
            "href": "http://192.168.81.110:9999/actuator",
            "templated": false
        },
        "archaius": {
            "href": "http://192.168.81.110:9999/actuator/archaius",
            "templated": false
        },
       
       ......
       }
}
```
