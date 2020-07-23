package com.gruuy.test.scheduledtasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程池配置类
 * 配置此接口表示spring使用多线程来进行任务调度
 * @author Gruuy
 * @Email: 245746119@qq.com
 * @date: 2020/7/23
 * @Copyright: 2020 锦铭泰软件. All rights reserved.
 */
@Configuration
public class ThreadPoolSelectorConfig implements SchedulingConfigurer {

    /** 读取超时时间，单位毫秒 */
    private int readTimeout = 5000;

    /** 连接超时时间，单位毫秒 */
    private int connectTimeout = 5000;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        // 初始化线程池
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        return threadPoolTaskScheduler;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        // 设置生成连接工厂  job调用超过50s会自动断开
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);// 单位为ms
        factory.setReadTimeout(readTimeout);// 单位为ms
        return factory;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        // 绑定线程池
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    // 销毁时，自动执行清理线程池  避免内存泄漏
    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }
}
