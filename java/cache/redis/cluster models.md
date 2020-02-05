---
title: redis cluster modes 
category: redis cluster sentinel master/slave 
---

## redis cluster patterns [links to cnblogs](https://www.cnblogs.com/renpingsheng/tag/redis/)

### 1. master/slave

`master/slave` has one master and multi slaves, master is writable and slaves are readonly, single write, multi read;

### 2. sentinel

add snetinel cluster or thread to guarantee high availability for redis elect new master when master goes done

### 3. cluster

cluster use 160000+ slot to store data