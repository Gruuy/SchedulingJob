package com.gruuy.test.scheduledtasks.controller;

import com.gruuy.test.scheduledtasks.request.AddTaskRequest;
import com.gruuy.test.scheduledtasks.service.IJobTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新增作业
 * @author Gruuy
 * @Email: 245746119@qq.com
 * @date: 2020/7/23
 * @Copyright: 2020 锦铭泰软件. All rights reserved.
 */
@RestController
@RequestMapping(value = "/job/task")
public class AddTaskController {

    @Autowired
    private IJobTaskService jobTaskService;

    @PostMapping(value = "/add")
    public String addTask(@RequestBody AddTaskRequest request){
        return jobTaskService.addTask(request);
    }
}
