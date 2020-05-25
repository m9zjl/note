# jvm

---

1. difference between `synchronized` and `reentrantlock`
   >synchronized using monitor to  
   

2. java Object Layout
   >markword+classpointer+instance+padding
AllRight

3. markword  锁 gc identity hashcode
   
   lock cmpxchg
4. synchronized内部是这样的：

先是偏向锁，然后从自旋锁过度到轻量级锁，最后才是重量级锁

一方面，syn的使用相对简单，另一方面，官方都这么建议了，那么，我们什么时候使用ren呢？就是我们需要使用到ren的特有功能的时候：

ReenTrantLock独有的能力：

1.      ReenTrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。

2.      ReenTrantLock提供了一个Condition（条件）类，用来实现分组唤醒需要唤醒的线程们，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。

3.      ReenTrantLock提供了一种能够中断等待锁的线程的机制，通过lock.lockInterruptibly()来实现这个机制。

---

1. volatile
2. 高可用，高性能，高扩展