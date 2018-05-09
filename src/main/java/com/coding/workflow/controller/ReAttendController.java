package com.coding.workflow.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 平凡的世界 on 2018/5/9.
 * avtiviti 工作流演示
 */
@Controller
@RequestMapping("reAttend")
public class ReAttendController {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @RequestMapping("/firstDemo")
    public void firstDemo() {
        //根据bpmn文件部署流程
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/re_attend.bpmn").deploy();
        //获取流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        //启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
        String processId = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID："+processId);

        Task task=taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("第一次执行前，任务名称："+task.getName());
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("第二次执行前，任务名称："+task.getName());
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("task为null，任务执行完毕："+task);
    }
}
