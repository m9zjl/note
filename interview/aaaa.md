面试题

1. String和StringBuffer的区别 ->

   > **Stack** is a memory place where the methods and the local variables are stored. (variable references either **primitive** or **object** references are also stored in the stack). **Heap** is a memory place where the objects and its instance variable are stored.
   >
2. gc的概念，如果A和B对象循环引用，是否可以被GC？ 不行 需要进行可达性分析， 从root树开始
3. Java中的内存溢出是如何造成的  长周期持有短周期，不合理作用域
4. String s = “123”;这个语句有几个对象产生

   ```java
   在 String s = new String("123") 这条语句中当常量池中不存在123时，创建了两个对象。当常量池中存在123时，创建了一个对象。
   ```
5. Error、Exception和RuntimeException的区别，作用又是什么？

   > NoClassDefFoundError和ClassNotFoundException有什么区别？正确答案：NoClassDefFoundError是当Java虚拟机或ClassLoader实例试图加载某个类，但无法找到该类的定义时抛出此异常；ClassNotFoundException是当应用程序试图调用Class.forName(String)通过字符串名加载类，而找不到该类定义时抛出的异常。
   >
6. 列举3个以上的RuntimeException

   > ClassNotFoundException,NullPointerException,ArrayOutOfBoundsException
   >
7. reader和inputstream区别
8. hashCode的作用
9. Object类中有哪些方法，列举3个以上（可以引导）

> 1．clone方法 保护方法，实现对象的浅复制，只有实现了Cloneable接口才可以调用该方法，否则抛出CloneNotSupportedException异常。
> 2．getClass方法 final方法，获得运行时类型。
> 3．toString方法 该方法用得比较多，一般子类都有覆盖。
> 4．finalize方法 该方法用于释放资源。可以进行逃逸
> 5．equals方法 该方法是非常重要的一个方法。一般equals和==是不一样的，但是在Object中两者是一样的。子类一般都要重写这个方法。
> 6．hashCode方法 该方法用于哈希查找，可以减少在查找中使用equals的次数，重写了equals方法一般都要重写hashCode方法。这个方法在一些具有哈希功能的Collection中用到。一般必须满足obj1.equals(obj2)==true。可以推出obj1.hash- Code()==obj2.hashCode()，但是hashCode相等不一定就满足equals。不过为了提高效率，应该尽量使上面两个条件接近等价。如果不重写hashcode(),在HashSet中添加两个equals的对象，会将两个对象都加入进去。
>
> 7．wait方法 wait方法就是使当前线程等待该对象的锁，当前线程必须是该对象的拥有者，也就是具有该对象的锁。wait()方法一直等待，直到获得锁或者被中断。wait(long timeout)设定一个超时间隔，如果在规定时间内没有获得锁就返回。调用该方法后当前线程进入睡眠状态，直到以下事件发生。
> （1）其他线程调用了该对象的notify方法。
> （2）其他线程调用了该对象的notifyAll方法。
> （3）其他线程调用了interrupt中断该线程。
> （4）时间间隔到了。
> 此时该线程就可以被调度了，如果是被中断的话就抛出一个InterruptedException异常。
>
> 8．notify方法 该方法唤醒在该对象上等待的某个线程。
> 9．notifyAll方法该方法唤醒在该对象上等待的所有线程。

11. char型变量中能不能存贮一个中文汉字?为什么? 字符编码
12. 列举几个Java Collection类库中的常用类

    > List Set Map
    >
13. List、Set、Map是否都继承自Collection接口？
14. HashMap和Hashtable的区别
15. HashMap中是否任何对象都可以做为key,用户自定义对象做为key有没有什么要求？
16. interface 和 abstrat class的区别 是否可以继承多个接口，是否可以继承多个抽象类
17. *!*启动一个线程是用run()还是start()? 多线程有几种实现 同步和并发是如何解决的 什么叫守护线程，用什么方法实现守护线程（Thread.setDeamon()的含义） 如何停止一个线程？ 解释是一下什么是线程安全？举例说明一个线程不安全的例子。解释Synchronized关键字的作用。 当一个线程进入一个对象的一个synchronized方法后，其它线程是否可进入此对象的其它方法?
18. 了解过哪些JDK8的新特性，举例描述下相应的特性？
19. 对sql进行优化的原则有哪些？
20. servlet生命周期是生命与cgi的区别？
21. StringBuffer有什么优势？为什么快？ -> 不会重复在常量池中产生对象
22. 谈谈你对HashMap的理解，底层原理的基本实现，HashMap怎么解决碰撞问题的？
    开发中用了那些数据库？回答mysql，储存引擎有哪些？
23. 悲观锁和乐观锁问题使用场景、

> **只要更新数据是依赖读取的数据作为基础条件的，就会有并发更新问题**，**需要乐观锁或者悲观锁取解决，特别实在计数表现明显。**
>
> 又**比如在更新数据不依赖查询的数据的就不会有问题，****例如修改用户的名称，多人同时修改，结果并不依赖于之前的用户名字，这就不会有并发更新问题。**

23. 分布式集群实现的原理。
24. springmvc和mybatis的工作原理，有没有看过底层源码？
25. 熟悉IO么？与NIO的区别，阻塞与非阻塞的区别
26. 微信红包怎么实现。

> https://www.zybuluo.com/yulin718/note/93148

27. 海量数据分析。
28. 线程安全和非线程安全。
29. HTTP2.0、thrift。
30. java反射应用
31. 分布式事务一致性。
32. nio的底层实现。
33. jvm基础是必问的，jvm GC原理，JVM怎么回收内存。
34. API接口与SDI接口的区别
35. dubbo如何一条链接并发多个调用。Dubbo的原理，序列化相关问题。
36. 用过哪些中间件。
37. 做过工作流引擎没有。
38. 以前的工作经历，自己觉得出彩的地方
39. 线程池的一些原理，锁的机制升降级
40. 从系统层面考虑，分布式从哪些纬度考虑
41. Hadoop底层怎么实现
42. threadLocal，线程池，hashMap/hashTable/coccurentHashMap等
43. 秒杀系统的设计
44. 虚拟机，IO相关知识点
45. Linux的命令
46. 一个整形数组，给定一个数，在数组中找出两个数的和等于这个数，并打印出来，我写的时间复杂度高，要
47. 求O(n)。
48. n个整数，找出连续的m个数加和是最大。
49. 更重视开源技术
50. 数据库锁隐原理
51. 1000个线程同时运行，怎么防止不卡
52. 并列的并发消费问题）
53. 高并发量大的话怎么处理热点，数据等
54. 如何获取一个本地服务器上可用的端口
55. 流量控制相关问题
56. 数据库TPS是多少，是否进行测试过
57. 缓存击穿有哪些方案解决
58. Java怎么挖取回收器相关原理
59. Java的集合都有哪些，都有什么特点
60. 分布式锁，redis缓存，spring aop，系统架构图，MySQL的特性
61. 同时给10万个人发工资，怎么样设计并发方案，能确保在1分钟内全部发完 打个比方会提出类似的场景
62. ThreadLocal使用场景
    > 为什么弱引用，实际上是代码中使用的地方强引用，ThreadLocalMap中的key弱引用 这样当线程执行完用户的代码后就可以自动删除相应的key，但是对应的value会泄漏
    >
