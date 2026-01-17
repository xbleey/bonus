package com.xbleey.test;

import java.util.Arrays;
import java.util.BitSet;

public class FastArrayIntersection {

    /**
     * 针对海量数据(1亿级)且数值范围有限(8位数)的极速交集方案
     * 时间复杂度：O(N) - 仅需线性遍历三次
     * 空间复杂度：< 50MB (BitSet占用) + 结果数组占用
     */
    public static int[] getIntersectionHuge(int[] arr1, int[] arr2, int[] arr3) {
        // 8位数最大值 < 1亿。分配 1亿个 bit，约占用 12MB 内存。
        // 为了安全覆盖 0-99999999，这里给 1亿大小
        int range = 100_000_000;

        // 1. 第一步：将 arr1 映射到 map1
        BitSet map1 = new BitSet(range);
        for (int num : arr1) {
            map1.set(num);
        }

        // 2. 第二步：遍历 arr2，如果元素在 map1 中存在，则标记到 map2
        // map2 存储的是 "既在 arr1 又在 arr2" 的元素
        BitSet map2 = new BitSet(range);
        for (int num : arr2) {
            if (map1.get(num)) {
                map2.set(num);
            }
        }

        // 释放 map1 的内存（虽然只有12MB，但在极限场景下也是优化）
        map1 = null;

        // 3. 第三步：遍历 arr3，如果在 map2 中存在，则是三者交集
        // 预估结果集大小，避免频繁扩容。如果是求极少的交集，可以给小一点。
        // 这里为了绝对安全，暂时复用 arr3 的长度作为最大可能 buffer
        int[] tempResult = new int[Math.min(arr3.length, map2.cardinality())];
        int count = 0;

        for (int num : arr3) {
            // 检查 num 是否在 arr1 和 arr2 的交集(map2)中
            if (map2.get(num)) {
                tempResult[count++] = num;
                // 【重要】找到一个就清除一个位标记，防止 arr3 内部有重复数字导致结果重复
                // 如果业务允许结果有重复，去掉下面这行
                map2.clear(num);
            }
        }

        // 4. 返回结果
        return Arrays.copyOf(tempResult, count);
    }

    // 模拟测试（注意：JVM启动需要分配足够堆内存，如 -Xmx1G）
    public static void main(String[] args) {
        // 构造模拟数据（此处仅作演示，不构造真实的1亿数组，以免本地运行卡顿）
        int[] a1 = {12345678, 55555555, 87654321};
        int[] a2 = {12345678, 55555555, 99999999};
        int[] a3 = {12345678, 11111111, 55555555};

        long start = System.currentTimeMillis();
        int[] res = getIntersectionHuge(a1, a2, a3);
        long end = System.currentTimeMillis();

        System.out.println("耗时: " + (end - start) + "ms");
        System.out.println("交集: " + Arrays.toString(res));
    }
}
