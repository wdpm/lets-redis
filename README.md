# Lets redis
## 目录
### 应用篇
- 百舸争流：[redis 可重入锁](\docs\redis-reentrant-lock.md) => setnx + expire
- [redis 消息队列](\docs\redis-message-queue.md) => list
- 缓兵之计：[redis 延迟队列](\docs\redis-delay-queue.md) => zset
- 节衣缩食：[redis 位图](\docs\redis-bitmap.md) => bit []
- 四两拨千斤：[redis HyperLogLog](\docs\redis-hyperloglog.md) => 通过低位连续0位的最大长度 k估算随机数的数量N
- 层峦叠嶂：[redis 布隆过滤器](\docs\redis-bloom-filter.md) => 多重hash + bit[]
- 断尾求生：[redis 简单限流](\docs\redis-simple-limit-flow.md) => zset 设置标志key，score为当前时间戳，value唯一
- [redis 漏斗限流](\docs\redis-funnel-limit-flow.md) => 类似于漏斗，有一定的缓冲空间
- 近水楼台：[redis GeoHash](\docs\redis-geo-hash.md) => GeoHash 算法将二维坐标映射为一维整数，zset排序后，还原二维坐标
- 大海捞针：[redis scan](\docs\redis-scan.md)

### 原理篇
- [redis 线程I/O模型](\docs\redis-thread-io-model.md) => 多路复用+ 非阻塞IO
- [redis 通信协议](\docs\redis-RESP.md) => RESP 简单文本协议
- [redis 持久化](\docs\redis-persistence.md) => RDB + AOF
- [redis 管道](\docs\redis-pipeline.md) => use pipeline
- [redis 事务](\docs\redis-transaction.md) => multi/exec/discard
- [redis 5.0+ Stream](\docs\redis-stream.md) => 支持多播的可持久化的消息队列

### 高可用
- 主从同步+读写分离
- sentinel
- [redis 集群](\docs\redis-cluster.md)

### 拓展篇
- [redis info指令](\docs\redis-info.md)
- redis 集群下的分布式锁 => redlock
- 朝生暮死：[redis 过期策略](\docs\redis-expire-strategy.md) => 定时删除+惰性删除
- 优胜劣汰：[redis LRU策略](\docs\redis-lru.md) => 传统LRU + 随机采样
- 妙手仁心：[优雅的jedis](.\src\main\java\io\github\wdpm\redis\jedis\RedisPool.java)