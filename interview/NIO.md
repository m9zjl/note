# NIO

## 1. select 模型

[select vs epoll](https://zhuanlan.zhihu.com/p/64746509)

select会将等待线程放到所有的socket中，因此当网卡中断后需要把所有socket的等待队列中的线程引用删除，在while循环中遍历所有线程找到有数据到达的线程，出于性能考虑限制1024个fd

![](https://pic4.zhimg.com/80/v2-a86b203b8d955466fff34211d965d9eb_1440w.jpg)

## 2. epoll 模型

![](https://pic4.zhimg.com/80/v2-40bd5825e27cf49b7fd9a59dfcbe4d6f_1440w.jpg)

epoll模型将两个队列拆开，eventpoll用于放到需要坚挺的socket的waiting列表，当socket相应，将socket添加到rdlist中，并且唤醒eventpoll的等待队列中的线程，该线程直接操作rdlist

## Netty中的粘包和拆包解决方案


1. 固定长度的拆包器 FixedLengthFrameDecoder，每个应用层数据包的都拆分成都是固定长度的大小
2. 行拆包器 LineBasedFrameDecoder，每个应用层数据包，都以换行符作为分隔符，进行分割拆分
3. 分隔符拆包器 DelimiterBasedFrameDecoder，每个应用层数据包，都通过自定义的分隔符，进行分割拆分
4. 基于数据包长度的拆包器 LengthFieldBasedFrameDecoder，将应用层数据包的长度，作为接收端应用层数据包的拆分依据。按照应用层数据包的大小，拆包。这个拆包器，有一个要求，就是应用层协议中包含数据包的长度
