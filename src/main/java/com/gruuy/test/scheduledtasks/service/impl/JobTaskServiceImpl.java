package com.gruuy.test.scheduledtasks.service.impl;

import com.gruuy.test.scheduledtasks.request.AddTaskRequest;
import com.gruuy.test.scheduledtasks.service.IJobTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 任务服务类
 *
 * @author Gruuy
 * @Email: 245746119@qq.com
 * @date: 2020/7/23
 * @Copyright: 2020 锦铭泰软件. All rights reserved.
 */
@Service
public class JobTaskServiceImpl implements IJobTaskService {

    // 线程池任务调度类，能够开启线程池进行任务调度
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    // 所有调度任务
    private Map<String, ScheduledFuture<?>> allcheduledFutureMap = new ConcurrentHashMap<String, ScheduledFuture<?>>( );

    @Override
    public String addTask(AddTaskRequest request) {
        // 作业编号
        String jobId = request.getTaskName( );
        // cron表达式
        String cronStr = request.getCron( );

        // 判断是否已启动过
        ScheduledFuture<?> scheduledFuture = allcheduledFutureMap.get(jobId);
        if (scheduledFuture != null) {
            // 再次确认此调度任务是否被取消过
            if (!scheduledFuture.isCancelled( )) {
                System.out.println("【创建调度任务】-操作失败，此调度任务早已被启动，请勿重复操作");
                return "true";
            }
        }

        // 线程接口类
        Runnable runnable = this.getRunnable(jobId);

        // 频率类
        Trigger trigger = this.getTrigger(cronStr);

        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(runnable, trigger);

        // 所有调度任务
        allcheduledFutureMap.put(jobId, future);

        return "true";
    }

    /**
     * 时间对象
     *
     * @param cronStr
     * @return
     */
    private Trigger getTrigger(String cronStr) {
        return triggerContext -> {
            CronTrigger trigger = new CronTrigger(cronStr);
            return trigger.nextExecutionTime(triggerContext);
        };
    }

    /**
     * 创建实例
     *
     * @param jobId
     * @return
     */
    private Runnable getRunnable(String jobId) {
        return () -> {
            System.out.println(jobId + "-作业调度开始");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace( );
            } finally {
                System.out.println(jobId + "-作业调度结束");
            }
        };
    }
}
