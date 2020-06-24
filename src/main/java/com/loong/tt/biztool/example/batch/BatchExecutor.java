package com.loong.tt.biztool.example.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Albert on 2020/6/24.
 */
public class BatchExecutor {

    public static final int DEFAULT_SIZE = 10;

    public static final int DEFAULT_AWAIT = 8;

    /**
     * @param size       线程池大小
     * @param keyFunc    每个线程返回值的key
     * @param handleFunc 线程处理函数
     * @param data       待处理数据列表
     * @param isPartly   是否允许部分返回
     * @param <T>
     * @param <V>
     * @param <R>
     * @return
     */
    public static <T, V, R> Map<V, R> batchExecuteAndGet(int size, List<T> data, Function<T, V> keyFunc, Function<T, R> handleFunc,
                                                         boolean isPartly) {
        ExecutorService pool = Executors.newFixedThreadPool(size);
        Map<V, Future<R>> rMap = new HashMap<>();
        for (T t : data) {
            Future future = pool.submit(() -> handleFunc.apply(t));
            rMap.put(keyFunc.apply(t), future);
        }
        pool.shutdown();
        try {
            Map<V, R> result = new HashMap<>();
            boolean isTerminated = pool.awaitTermination(DEFAULT_AWAIT, TimeUnit.SECONDS);
            if (isTerminated) {
                for (Map.Entry<V, Future<R>> entry : rMap.entrySet()) {
                    if (entry.getValue().get() != null) {
                        result.put(entry.getKey(), entry.getValue().get());
                    }
                }
                return result;
            } else {
                //如果允许部分完成
                if (isPartly) {
                    for (Map.Entry<V, Future<R>> entry : rMap.entrySet()) {
                        if (entry.getValue().get() != null) {
                            result.put(entry.getKey(), entry.getValue().get());
                        }
                    }
                    return result;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println("batch execute failed for data: {}");
        } finally {
            pool.shutdownNow();
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
