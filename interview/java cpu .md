# handle overlapoverlap

1. ps -ef 查看对应的进程/ jps也可以
2. top -Hp 进程号 查看线程
3. 线程转化为16进制
4. jstack pid | grep 16进制 -A90
