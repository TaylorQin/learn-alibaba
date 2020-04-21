## 笔记
#### 接入sentinel步骤：
- 添加sentinel的pom依赖
```
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-sentinel</artifactId>
        <version>2.1.1.RELEASE</version> <!-- 2.1.1.RELEASE适用于springboot的2.2.1.RELEASE版本 -->
    </dependency>
```
- 添加sentinel的dashboard配置
```
spring:
  cloud:
    sentinel:
      transport:
        dashboard: localhost:8080
```
#### 两种方式实现流控
- 基于url （RequestMapping的值）
- 基于sentinel资源 （在RequestMapping方法上使用@Sentinel声明资源）
- 然后在sentinel的dashboard的流控规则菜单下配置， sentinel对资源是懒加载，先请求一下资源，然后dashboard中就会出现相应的资源了
#### 配置自定义UrlblockHandler的方式
- 创建class实现接口UrlBlockHandler
- 用WebCallbackManager的接口setUrlBlockHandler配置自定义的UrlBlockHandler
```
@Configuration
public class SentinelConfig {

     @PostConstruct
     public void init() {
        WebCallbackManager.setUrlBlockHandler(new DefaultUrlBlockHandler());
        WebCallbackManager.setRequestOriginParser(new IpRequestOriginParser());
    }
}
```

#### 黑白名单控制

- 创建IpRequestOriginParser实现接口RequestOriginParser
- 用WebCallbackManager的接口setRequestOriginParser配置自定义的RequestOriginParser
```
    WebCallbackManager.setUrlBlockHandler(new DefaultUrlBlockHandler());
    WebCallbackManager.setRequestOriginParser(new IpRequestOriginParser());
```
- 然后在sentinel的dashboard的授权规则菜单下配置


#### 基于参数的热点限流
在热点规则中对指定的资源添加相应的规则， 热点限流必须通过sentinel资源来配置，就是请求Controller的方法上必须有@SentinelResource

#### 自适应限流规则


#### 规则持久化
- file
- nacos
- zk
- apollo
- redis


