# redis object
> redis-5.0.7/src/server.h:464
```c
/* A redis object, that is a type able to hold a string / list / set */

/* The actual Redis Object */
#define OBJ_STRING 0    /* String object. */
#define OBJ_LIST 1      /* List object. */
#define OBJ_SET 2       /* Set object. */
#define OBJ_ZSET 3      /* Sorted set object. */
#define OBJ_HASH 4      /* Hash object. */
```
## 查看value编码
使用OBJECT ENCODING 可以查看key对应的值得编码方式
```
>SET msg "hello"
>OBJECT ENCODING msg
"embstr"
```
TODO：列出不同编码的对象对应的OBJECT ENCODING 汇总表

## String的编码
## List的编码
ziplist或linkedlist

### value长度过大而发生编码转换
```
> RPUSH list1 "hello" "world" "123"
> OBJECT ENCODING list1
"ziplist"

# 超过64字节长即可
> RPUSH list1 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
> OBJECT ENCODING list1
“linkedlist"
```
### 元素过多而发生编码转换
```
> EVAL "for i=1,512 do redis.call('RPUSH',KEYS[1],i) end" 1 "integers"
> LLEN integers
512
> OBJECT ENCODING integers
"ziplist"

> RPUSH integers 513
> OBJECT ENCODING integers
"linkedlist"
```
### 参数配置
- list-max-ziplist-value
- list-max-ziplist-entries

## Hash的编码
满足以下条件时使用ziplist编码
- 所有键值对中的key和value都小于64 bytes
- 键值对数量小于 512个。

否则，使用hashtable编码
### ？
### ？

### 参数配置
- hash-max-ziplist-value
- hash-max-ziplist-entries

## Set的编码
集合的编码可以是inset或者hashtable。

满足以下条件时，使用intset编码
- 所有元素都是整数值
- 元素数量少于512个

否则，使用hashtable编码。
### ？
### ？

### 参数配置

## ZSet的编码
有序集合的编码可以是ziplist或者zskiplist。

满足以下条件时，使用ziplist编码
- 有序集合元素数量少于128个
- 元素成员长度都低于64 bytes

否则，使用skiplist编码

### ？
### ？
### 参数配置
- zset-max-ziplist-value
- zset-max-ziplist-entries

## 引用计数
```
> SET A 100
> OBJECT ENCODING A
2

> SET B 100
> OBJECT ENCODING A
3
> OBJECT ENCODING B
3
```
服务器程序和键A引用，为2。然后设置B，此时A，B值的引用次数都为3。

## 小结
- Redis 有string、list、hash、set、zset五种类型的对象，每种类型的对象有多种编码方式。
- 服务器执行某些命令之前，先检查给定key对应的value类型能否执行该命令。
- Redis采用引用计数来GC
- Redis共享0-9999的字符串的对象。
- 对象会记录自身最后一次访问的时间，用于计算对象的idle时间。
