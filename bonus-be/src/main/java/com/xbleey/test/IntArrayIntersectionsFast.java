package com.xbleey.test;

import java.util.concurrent.CountDownLatch;

public class IntArrayIntersectionsFast {
    private static final int OFFSET = 10_000_000;
    private static final int DOMAIN = 90_000_000; // 10,000,000..99,999,999
    private static final int WORDS = (DOMAIN + 63) >>> 6; // /64 ceiling

    public static int[] intersect3(int[] a, int[] b, int[] c, int threads) throws InterruptedException {
        if (a == null || b == null || c == null) throw new NullPointerException();
        if (a.length == 0 || b.length == 0 || c.length == 0) return new int[0];
        if (threads <= 0) threads = Runtime.getRuntime().availableProcessors();

        // 1) bitsA = set(A)
        long[] bitsA = buildBitsetParallel(a, threads);

        // 2) bitsTmp = set(B); bitsA &= bitsTmp
        long[] bitsTmp = buildBitsetParallel(b, threads);
        andInPlace(bitsA, bitsTmp);

        // 3) bitsTmp = set(C); bitsA &= bitsTmp
        bitsTmp = buildBitsetParallel(c, threads);
        andInPlace(bitsA, bitsTmp);

        // 4) 输出：先计数再解码
        int count = 0;
        for (int i = 0; i < WORDS; i++) count += Long.bitCount(bitsA[i]);
        if (count == 0) return new int[0];

        int[] out = new int[count];
        int p = 0;
        for (int wi = 0; wi < WORDS; wi++) {
            long w = bitsA[wi];
            while (w != 0L) {
                int tz = Long.numberOfTrailingZeros(w);
                int idx = (wi << 6) + tz; // 0..DOMAIN-1
                out[p++] = idx + OFFSET;
                w &= (w - 1);
            }
        }
        return out; // 升序
    }

    private static long[] buildBitsetParallel(int[] arr, int threads) throws InterruptedException {
        // 每线程一个本地位图（避免共享写竞争）
        final long[][] locals = new long[threads][];
        for (int t = 0; t < threads; t++) locals[t] = new long[WORDS];

        final CountDownLatch latch = new CountDownLatch(threads);
        final int n = arr.length;

        for (int t = 0; t < threads; t++) {
            final int tid = t;
            final int from = (int) (((long) n * t) / threads);
            final int to   = (int) (((long) n * (t + 1)) / threads);
            final long[] bits = locals[tid];

            Thread worker = new Thread(() -> {
                // 热路径：不做范围检查（你已保证值域）
                for (int i = from; i < to; i++) {
                    int idx = arr[i] - OFFSET;          // 0..89,999,999
                    bits[idx >>> 6] |= 1L << (idx & 63);
                }
                latch.countDown();
            });
            worker.setDaemon(true);
            worker.start();
        }

        latch.await();

        // 归并：全局位图 = OR(locals[0..threads-1])
        long[] global = locals[0];
        for (int t = 1; t < threads; t++) {
            long[] src = locals[t];
            for (int i = 0; i < WORDS; i++) global[i] |= src[i];
        }
        return global;
    }

    private static void andInPlace(long[] a, long[] b) {
        for (int i = 0; i < WORDS; i++) a[i] &= b[i];
    }
}

