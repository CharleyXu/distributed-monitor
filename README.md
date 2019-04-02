
#### 前言

整合 Eureka,Spring-Admin,Spring Security实现分布式监控

#### 介绍

使用Spring-Admin实现监控自定义通知

集成 Eureka,Spring-Admin会自己拉取 Eureka 上注册的 app 信息，主动去注册.
client 端不需要 admin-client 的依赖，也不需要配置 admin 地址了，一切全部由 admin-server 自己实现

![登录](https://github.com/CharleyXu/distributed-monitor/blob/master/show-picture/%E6%88%AA%E5%9B%BE20190402111221890.jpg)

![主页](https://github.com/CharleyXu/distributed-monitor/blob/master/show-picture/%E6%88%AA%E5%9B%BE20190402111303134.jpg)

![通知](https://github.com/CharleyXu/distributed-monitor/blob/master/show-picture/%E6%88%AA%E5%9B%BE20190402111448712.jpg)

#### 监控缺陷

1.没有时间序列的监控数据

2.只有对孤立节点的监控数据快照

由于在集群化的弹性环境中，应用程序的节点可以增长、扩展，并由非常大量的应用实例所组成。

对于孤立节点的监控可能即费力又没有什么实际效果。所以，使用基于时间序列的数据聚合工具将获得更好的效果

#### 后续尝试

使用Spring Boot Actuator、Jolokia和Grafana实现准实时监控

[使用Spring Boot Actuator、Jolokia和Grafana实现准实时监控]: http://blog.didispace.com/spring-boot-jolokia-grafana-monitor/

