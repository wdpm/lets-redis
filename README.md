# Lets redis
## 目录
- 百舸争流：[redis 可重入锁](\docs\redis-reentrant-lock.md) => setnx + expire
- [redis 消息队列](\docs\redis-message-queue.md) => list
- 缓兵之计：[redis 延迟队列](\docs\redis-delay-queue.md) => zset
- 节衣缩食：[redis 位图](\docs\redis-bitmap.md) => bit []
- 四两拨千斤：[redis HyperLogLog](\docs\redis-hyperloglog.md) => 通过低位连续0位的最大长度 k估算随机数的数量N
- 层峦叠嶂：[redis 布隆过滤器](\docs\redis-bloom-filter.md) => 多重hash + bit[]
- 断尾求生：[redis 简单限流](\docs\redis-simple-limit-flow.md) => zset 设置标志key，score为当前时间戳，value唯一