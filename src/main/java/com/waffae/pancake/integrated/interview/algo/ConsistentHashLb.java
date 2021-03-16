package com.waffae.pancake.integrated.interview.algo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 一致性hash算法:https://zhuanlan.zhihu.com/p/95683526
 * 1.单线程中,推荐尽量使用TreeMap,并发度较低可以使用Collections.synchronizedSortedMap进行封装
 * 2.高并发场景下,可以使用ConcurrentSkipListMap
 * 3.hash算法,不建议使用String.hashcode方法,会节点分布不均匀。推荐使用 KETAMA_HASH/FNV1_32_HASH
 *
 * @author yixiaoshuang
 * @date 2020/10/27 16:12
 */
@Slf4j
public class ConsistentHashLb {

    private List<String> nodes;

    // 存储虚拟节点的hash值到实际节点的映射关系
    private TreeMap<Long, String> circle = Maps.newTreeMap();

    // 节点的复制因子,虚拟节点个数 = 实际节点个数 *
    private int numberOfReplicas = 0;

    public ConsistentHashLb() {

    }

    public ConsistentHashLb(List<String> nodes, int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
        this.nodes = nodes;
        init(nodes);
//        init2(nodes);
    }

    /**
     * 初始化机器节点到环上-使用MD5计算hash值
     */
    private void init(List<String> nodes) {
        for (String node : nodes) {
            for (int i = 0; i < numberOfReplicas / 4; i++) {
                String virtualNodeName = getNodeNameByIndex(node, i);
                for (int j = 0; j < 4; j++) {
                    virtualNodeName += j;
                    long hash = hash(virtualNodeName, j);
                    circle.put(hash, node);
                }
            }
        }
    }

    /**
     * 初始化机器节点到环上-使用FNV1_32计算hash值
     */
    private void init2(List<String> nodes) {
        for (String node : nodes) {
            for (int j = 0; j < numberOfReplicas; j++) {
                String virtualNodeName = getNodeNameByIndex(node, j);
                long hash = fnvHash(virtualNodeName);
                circle.put(hash, node);
            }
        }
    }

    /**
     * 使用FNV1_32_HASH算法计算hash值
     *
     * @param str
     * @return
     */
    private static int fnvHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }


    private Long hash(String nodeName, int number) {
        byte[] digest = md5(nodeName);
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }

    public byte[] md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(str.getBytes("UTF-8"));
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String findNode(String key) {
        long hash = hash(key,0);
        if (circle.containsKey(hash)) {
            return circle.get(hash);
        } else {
            // treeMap用于返回大于或等于给定键的最小键 关联的键值映射;如果没有则返回null
            Map.Entry<Long, String> entry = circle.ceilingEntry(hash);
            if (Objects.isNull(entry)) {
                return nodes.get(0);
            } else {
                return entry.getValue();
            }
        }
    }

    private String getNodeNameByIndex(String node, int index) {
        return new StringBuilder().append(node).append("&").append(index).toString();
    }

    public static void main(String[] args) {
        List<String> nodes = Lists.newArrayList("s1", "s2", "s3", "s4", "s5");

        long count = 3000000;
        int numberOfCircle = count > 400 ? (int) (count / 400) : 32;
        ConsistentHashLb hashLb = new ConsistentHashLb(nodes, numberOfCircle);

        Map<Long, String> map = hashLb.circle;
        Map<String, Long> countMap = Maps.newHashMap();
        log.info("总节点个数:{}", map.size());
        for (Map.Entry<Long, String> entry : map.entrySet()) {
//            log.info("节点名称:{},哈希值:{}", entry.getValue(), entry.getKey());
        }


        for (int i = 0; i < count; i++) {
            StringBuilder sb = new StringBuilder("随机字符串");
            String randomStr = sb.append("&").append(i).toString();
            String nodeName = hashLb.findNode(randomStr);
            if (Objects.isNull(countMap.get(nodeName))) {
                countMap.put(nodeName, 1L);
            } else {
                Long tmpCount = countMap.get(nodeName);
                countMap.put(nodeName, tmpCount + 1);
            }
        }

        for (Map.Entry<String, Long> entry : countMap.entrySet()) {
            long tmpCount = entry.getValue();
            BigDecimal bg = new BigDecimal(tmpCount).divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_UP);

            log.info("节点名称:{},节点元素个数:{},分配占比:{}", entry.getKey(), entry.getValue(), bg.toString());
        }


    }


}
