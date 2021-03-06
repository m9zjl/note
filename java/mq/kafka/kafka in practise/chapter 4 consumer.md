---
title: kafka consumer
category: mq kafka
---
- [building kafka consumer](#building-kafka-consumer)
  - [1. creating a *kafka consumer*](#1-creating-a-kafka-consumer)
  - [2. substribing to a topic](#2-substribing-to-a-topic)
  - [3. poll messages from topic](#3-poll-messages-from-topic)
  - [4. commit and offset](#4-commit-and-offset)
    - [4.1 auto commit](#41-auto-commit)
    - [4.2 commit synchronou](#42-commit-synchronou)
    - [4.3 commit asynchronous](#43-commit-asynchronous)
      - [4.3.1 commit sepecified offset](#431-commit-sepecified-offset)

## building kafka consumer

### 1. creating a *kafka consumer*

the mandatory properties is `bootstrap.servers`, `key.deserializer`,  `value.deserializer`
the floowing code shows how to create a consumer with minimum properties

```java
Properties props = new Properties();
props.put("bootstrap.servers", "broker1:9092,broker2:9092");
props.put("group.id", "CountryCounter");
props.put("key.deserializer",
    "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer",
    "org.apache.kafka.common.serialization.StringDeserializer");
KafkaConsumer<String, String> consumer = new KafkaConsumer<String,
String>(props);
```

### 2. substribing to a topic

the subscribe() method takes `List<String>` as topics
>consumer.subscribe(Collections.singletonList("sample-topic"));

subscribing to topics also support regex expression like:
>consumer.subscribe("test.*")

which could mathc test1 test2 ....

### 3. poll messages from topic

the poll loop handles all details of coordination, partition rebalances, heartbeats, and data fetching. the simplest polling :

```java
try {
    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records) {
            log.debug("topic = %s, partition = %s, offset = %d,
                customer = %s, country = %s\n",
                record.topic(), record.partition(), record.offset(),
                record.key(), record.value());
            int updatedCount = 1;
            if (custCountryMap.countainsValue(record.value())) {
                updatedCount = custCountryMap.get(record.value()) + 1;
            }
            custCountryMap.put(record.value(), updatedCount)
            JSONObject json = new JSONObject(custCountryMap);
            System.out.println(json.toString(4))
        }
    }
} finally {
    consumer.close();
}
```

## 4. commit and offset

### 4.1 auto commit

enable.auto.commit=true then every five seconds the consumer will commit the latest offset your client reviived from poll(), you can change the default value by changing `auto.commit.interval.ms`

### 4.2 commit synchronou

```java
    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records)
        {
            System.out.printf("topic = %s, partition = %s, offset =
              %d, customer = %s, country = %s\n",
                 record.topic(), record.partition(), record.offset(), record.key(), record.value());
} try {
        consumer.commitSync();
    } catch (CommitFailedException e) {
        log.error("commit failed", e)
    }
}
```

### 4.3 commit asynchronous

commit asynchronous with call backs

```java
while (true) {
    ConsumerRecords<String, String> records = consumer.poll(100);
    for (ConsumerRecord<String, String> record : records)
    {
        System.out.printf("topic = %s, partition = %s,
        offset = %d, customer = %s, country = %s\n",
        record.topic(), record.partition(), record.offset(),
        record.key(), record.value());
}
    consumer.commitAsync(new OffsetCommitCallback() {
        public void onComplete(Map<TopicPartition,
        OffsetAndMetadata> offsets, Exception exception) {
            if (e != null)
                log.error("Commit failed for offsets {}", offsets, e);
        }
    }););
}
```

#### 4.3.1 commit sepecified offset

```java
while (true) {
    ConsumerRecords<String, String> records = consumer.poll(100);
    for (ConsumerRecord<String, String> record : records) {
        System.out.printf("topic = %s, partition = %s, offset = %d,
        customer = %s, country = %s\n",
        record.topic(), record.partition(), record.offset(),
        record.key(), record.value());
        currentOffsets.put(new TopicPartition(record.topic(),
        record.partition()), new
        OffsetAndMetadata(record.offset()+1, "no metadata"));
        if (count % 1000 == 0)
            consumer.commitAsync(currentOffsets, null);
        count++;
    }
}
```

## 5. rebance

> <font color=error size=7>TODO</font>

## 6. spring kafka auto config

application.yml for consumer wait for 1s or fetch size = 10k

```yaml
spring:
  kafka:
    # 消费者
    consumer:
      group-id: groupId
      auto-offset-reset: earliest
      bootstrap-servers: localhost:9092
      fetch.min.bytes: 10240
      fetch.max.wait.ms: 1_000
    # 生产者
    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
    properties:
      concurrency: 2
```

```java
@Component
public class SimpleListener {
    ## set batch fetch in consumerFacotry
    @KafkaListener(topics = {"topic1", "topic2"}, consumeFacotry="consumerFactory")
    public void listen(ConsumerRecord<String,String> data) {
    }
}
```

---

notes:

1. we could define several consumer and each with a unique thread, but we can\`t have mutiple threads safely use the same consuemr, one consumer per thread is the rule. **Note that `consumer.wakeup()` is the only consumer method that is safe to call from a different thread**
2. always close consumer befor existing this will close connection and cause reblance on kafka cluster rather than wating for the cordianator to discover

|properties|default value|description|
|:-:|:-:|:-:|
|fetch.min.bytes|1|1 stands for send or resceive immediate; when value > 1 wait until cached bytes > value  to return|
|fetch.max.wait.ms|500ms|wait until 500ms befor send to consumer|
|max.partition.fetch.bytes|1MB|max fetch bytes for each partition, the total fetch `maxmum size` = `max` * `partition size`|
|session.timeout.ms|3s|The amount of time a consumer can be out of contact with the brokers while still considered alive defaults to `3` seconds.|
|heatbeat.interval.ms|1s|heartbeat.interval.ms con‐ trols how frequently the KafkaConsumer `poll() method will send a heartbeat` to the group coordinator, whereas session.timeout.ms controls how long a consumer can `go without sending a heartbeat`. this value is usually set 1/3 of the session timeout ms|
|auto.oset.reset|`latest`/`earliest`|poll from latest or earliest|
|partition.assignment.strategy|Range|assigned by `topics`|
|partition.assignment.strategy|RoundRobin|assigned by `topics * partitions`|
|client.id|any unique string|used to identify messages send from the client|
|max.poll.records||controlls maximum |
|receive.buffer.bytes / send.buffer.bytes|-1|sizes of the TCP send and receive buffers,-1 means take system default as bytes|

---

```sequence
participant brokerA as a
participant kafka Cluster as s
participant brokerB as b

a->s: join the consumer group
s->a: leader with consumer list
b->s: join the consumer group
a->s: assigning partitions to consumers
s->b: assignment
s->a: assignment
```

---

```mermaid
graph TD;
    A-->B;
    A-->C;
    B-->D;
    C-->D;
```

```flow
//s
st=>start: 开始
e=>end: 结束
op=>operation: 我的操作
cond=>condition: 判断确认？
  
st->op->cond
cond(yes)->e
cond(no)->op
```
