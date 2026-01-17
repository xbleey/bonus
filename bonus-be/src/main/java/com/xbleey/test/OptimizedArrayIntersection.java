package com.xbleey.test;

public class OptimizedArrayIntersection {


    /**
     * 优化版1：使用整数数组作为位图（更节省内存）
     * 8位数最大值99,999,999，需要100,000,000位 ≈ 12.5MB
     * 但我们可以用int数组实现：100,000,000/32 ≈ 3,125,000个int ≈ 12.5MB
     */
    public static int[] intersectionOptimized(int[] arr1, int[] arr2, int[] arr3) {
        if (arr1 == null || arr2 == null || arr3 == null) {
            return new int[0];
        }

        // 找到最大值，避免分配不必要的内存
        int maxValue = Math.max(
                findMax(arr1),
                Math.max(findMax(arr2), findMax(arr3))
        );

        // 计算需要的int数组大小
        int bitmapSize = (maxValue >>> 5) + 1; // 除以32，向上取整
        int[] bitmap1 = new int[bitmapSize];
        int[] bitmap2 = new int[bitmapSize];

        // 处理第一个数组
        for (int num : arr1) {
            int index = num >>> 5;    // 相当于除以32
            int bit = 1 << (num & 31); // 在int中的位置
            bitmap1[index] |= bit;
        }

        // 处理第二个数组，同时检查是否在第一个数组中
        for (int num : arr2) {
            int index = num >>> 5;
            int bit = 1 << (num & 31);
            if ((bitmap1[index] & bit) != 0) {
                bitmap2[index] |= bit;
            }
        }

        // 清空第一个bitmap以释放部分内存
        bitmap1 = null;
        System.gc(); // 建议GC，但不保证立即执行

        // 收集结果
        int[] tempResult = new int[Math.min(arr1.length, Math.min(arr2.length, arr3.length))];
        int count = 0;

        for (int num : arr3) {
            int index = num >>> 5;
            int bit = 1 << (num & 31);
            if ((bitmap2[index] & bit) != 0) {
                tempResult[count++] = num;
                // 清除已找到的位，避免重复
                bitmap2[index] &= ~bit;
            }
        }

        int[] result = new int[count];
        System.arraycopy(tempResult, 0, result, 0, count);
        return result;
    }

    private static int findMax(int[] arr) {
        int max = 0;
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }
}
