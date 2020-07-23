package com.gruuy.test.scheduledtasks.service;

import com.gruuy.test.scheduledtasks.request.AddTaskRequest;

/**
 * 任务服务
 * @author Gruuy
 * @Email: 245746119@qq.com
 * @date: 2020/7/23
 * @Copyright: 2020 锦铭泰软件. All rights reserved.
 */
public interface IJobTaskService {
    /**
     * 添加任务
     * @param request
     * @return
     */
    String addTask(AddTaskRequest request);
}
