package com.xbleey.test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CalTimeTotal {

    /**
     * 快速生成一亿个打乱顺序的8位数的整形数组
     *
     * @return 包含1亿个8位数的整形数组
     */
    public static int[] generateShuffledArray() {
        final int SIZE = 100_000_000;  // 一亿
        final int MIN_8_DIGIT = 10_000_000;  // 最小8位数
        final int MAX_8_DIGIT = 99_999_999;  // 最大8位数

        int[] array = new int[SIZE];
        Random random = ThreadLocalRandom.current();

        // 快速生成随机数并填充数组
        for (int i = 0; i < SIZE; i++) {
            // 使用nextInt的范围参数，生成[MIN_8_DIGIT, MAX_8_DIGIT]范围内的随机数
            array[i] = MIN_8_DIGIT + random.nextInt(MAX_8_DIGIT - MIN_8_DIGIT + 1);
        }

        return array;
    }

    public static void main(String[] args) throws InterruptedException {
        // 获取 Runtime 实例用于内存统计
        Runtime runtime = Runtime.getRuntime();

        System.out.println("=== 生成测试数据 ===");

        // 生成三个一亿级8位数数组
        long startGen = System.nanoTime();
        int[] arr1 = generateShuffledArray();
        int[] arr2 = generateShuffledArray();
        int[] arr3 = generateShuffledArray();
        long endGen = System.nanoTime();

        System.out.println("生成3个数组，每个长度: " + arr1.length);
        System.out.println("生成耗时: " + (endGen - startGen) / 1_000_000.0 + " ms\n");

        // 测试 FastArrayIntersection.getIntersectionHuge
        System.out.println("=== 测试 FastArrayIntersection.getIntersectionHuge ===");
        System.gc();
        long memBefore2 = runtime.totalMemory() - runtime.freeMemory();
        long start2 = System.nanoTime();
        int[] result2 = FastArrayIntersection.getIntersectionHuge(arr1, arr2, arr3);
        long end2 = System.nanoTime();
        long memAfter2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("交集元素数量: " + result2.length);
        System.out.println("耗时: " + (end2 - start2) / 1_000_000.0 + " ms");
        System.out.println("内存增量: " + (memAfter2 - memBefore2) / 1024.0 / 1024.0 + " MB\n");

        // 测试 IntArrayIntersectionsFast.intersect3
        System.out.println("=== 测试 IntArrayIntersectionsFast.intersect3 ===");
        System.gc();
        long memBefore3 = runtime.totalMemory() - runtime.freeMemory();
        long start3 = System.nanoTime();
        int[] result3 = IntArrayIntersectionsFast.intersect3(arr1, arr2, arr3, 1);
        long end3 = System.nanoTime();
        long memAfter3 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("交集元素数量: " + result3.length);
        System.out.println("耗时: " + (end3 - start3) / 1_000_000.0 + " ms");
        System.out.println("内存增量: " + (memAfter3 - memBefore3) / 1024.0 / 1024.0 + " MB\n");

        // 测试 ArrayIntersectionDeepSeek.intersectionOptimized
        System.out.println("=== 测试 ArrayIntersectionDeepSeek.intersectionOptimized ===");
        System.gc();
        long memBefore4 = runtime.totalMemory() - runtime.freeMemory();
        long start4 = System.nanoTime();
        int[] result4 = OptimizedArrayIntersection.intersectionOptimized(arr1, arr2, arr3);
        long end4 = System.nanoTime();
        long memAfter4 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("交集元素数量: " + result4.length);
        System.out.println("耗时: " + (end4 - start4) / 1_000_000.0 + " ms");
        System.out.println("内存增量: " + (memAfter4 - memBefore4) / 1024.0 / 1024.0 + " MB\n");

        // 汇总对比
        System.out.println("=== 性能对比汇总 ===");
        System.out.printf("%-35s 时间: %8.2f ms  内存: %8.2f MB%n",
                "FastArrayIntersection -- Gemini:",
                (end2 - start2) / 1_000_000.0,
                (memAfter2 - memBefore2) / 1024.0 / 1024.0);
        System.out.printf("%-35s 时间: %8.2f ms  内存: %8.2f MB%n",
                "IntArrayIntersectionsFast -- GPT 5.2:",
                (end3 - start3) / 1_000_000.0,
                (memAfter3 - memBefore3) / 1024.0 / 1024.0);
        System.out.printf("%-35s 时间: %8.2f ms  内存: %8.2f MB%n",
                "IntersectionOptimized -- DeepSeek:",
                (end4 - start4) / 1_000_000.0,
                (memAfter4 - memBefore4) / 1024.0 / 1024.0);
    }
}
