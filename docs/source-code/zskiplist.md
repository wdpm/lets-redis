# zskiplist

```c
/* ZSETs use a specialized version of Skiplists */
typedef struct zskiplistNode {
    sds ele; // 字符串sds对象
    double score; // 分数
    struct zskiplistNode *backward; //后退指针，一次只能退一格
    struct zskiplistLevel {
        struct zskiplistNode *forward;//前进指针
        unsigned long span;//跨度
    } level[];
} zskiplistNode;

typedef struct zskiplist {
    struct zskiplistNode *header, *tail;// 头部，尾部
    unsigned long length;//表中节点数量
    int level;//表中层数最大的节点的层数
} zskiplist;

typedef struct zset {
    dict *dict;
    zskiplist *zsl;
} zset;
```