package org.nicolas.thread;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zorth
 */
@Component
public class ThreadPoolExecutorConfig {
    /**
     * 线程池核心线程数量
     */
    private static final int CORE_POOL_SIZE = 8;
    /**
     * 线程池最大容量
     */
    private static final int MAX_POOL_SIZE = 12;
    /**
     * 任务队列
     */
    private static final int QUEUE_CAPACITY = 100;
    /**
     * 当线程数大于核心线程数时，多余的空闲线程最多存活时间
     */
    private static final long KEEP_ALIVE_TIME = 1L;

    /**
     * 创建新线程当方法
     *
     * @return ThreadPoolExecutor
     */
    public ThreadPoolExecutor createThreadPool() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return threadPool;
    }
}
