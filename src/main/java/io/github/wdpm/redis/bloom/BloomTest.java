package io.github.wdpm.redis.bloom;

/**
 * Use lettuce or Jedis 3.x
 *
 * @author evan
 * @date 2020/6/7
 */
public class BloomTest {
    public static void main(String[] args) {
        Client client = new Client();
        client.delete("bloom");
        // 布隆过滤器对已经见过的元素不会误判，只会误判没见过的元素。
        for (int i = 0; i < 100000; i++) {
            client.add("bloom", "user" + i);
            boolean ret = client.exists("bloom", "user" + i);
            if (!ret) {
                System.out.println(i);
                break;
            }
        }

        // 使用 bf.exists 去查找没见过的元素
        client.delete("bloom");
        for (int i = 0; i < 100000; i++) {
            client.add("bloom", "user" + i);
            boolean ret = client.exists("bloom", "user" + (i + 1));
            // i+1 必然不存在，如果为true，就是误判
            if (ret) {
                System.out.println(i);
                break;
            }
        }

        client.delete("bloom");
        client.close();
    }
}
