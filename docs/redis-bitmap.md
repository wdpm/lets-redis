# redis 位图
位图本质就是bit 数组。
- bitcount 用来统计指定位置范围内 1 的个数。
- bitpos 用来查找指定范围内出现的第一个 0 或 1。
- bitfield 支持一次操作多个位。bitfield 有三个子指令，get/set/incrby，都可以对指定位片段读写，最多能处理 64 个连续的位。

## 应用
用户签到记录的功能实现。
- 通过 bitcount 统计用户一共签到了多少天。
- 通过 bitpos 指令查找用户从哪一天开始第一次签到。