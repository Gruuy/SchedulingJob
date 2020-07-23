package com.gruuy.test.scheduledtasks.request;

import java.io.Serializable;

/**
 * 新建任务入参
 * @author Gruuy
 * @Email: 245746119@qq.com
 * @date: 2020/7/23
 * @Copyright: 2020 锦铭泰软件. All rights reserved.
 */
public class AddTaskRequest implements Serializable {

    /** 任务名称 */
    private String taskName;

    /** 时间表达式 */
    private String cron;


    /**
     * 获取 任务名称
     *
     * @return taskName 任务名称
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * 设置 任务名称
     *
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取 时间表达式
     *
     * @return cron 时间表达式
     */
    public String getCron() {
        return this.cron;
    }

    /**
     * 设置 时间表达式
     *
     * @param cron 时间表达式
     */
    public void setCron(String cron) {
        this.cron = cron;
    }
}
