# RabbitMQ

![79c3f2ad461558298780c4cbe3bfdda6.png](https://s2.51cto.com/oss/201710/30/79c3f2ad461558298780c4cbe3bfdda6.png)

## 三种投递

1. fanout 广播
2. direct 直接 根据key
3. topic 正则


## RabbitMQ 事务


提供了事务的功能。

通过将 channel 设置为 confirm（确认）模式。



**rabbitmq 怎么避免消息丢失？**

1. **消息持久化**
2. **ACK确认机制**
3. **设置集群镜像模式**
4. **消息补偿机制**

**rabbitmq 怎么避免消息丢失？**

1. **消息持久化**
2. **ACK确认机制**
3. **设置集群镜像模式**
4. **消息补偿机制**
