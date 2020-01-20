package com.wyl.example.actividemo;

import org.activiti.engine.task.Task;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;


public class HelloWorld {
    public static void main(String[] args) {
        //1 获取流程引擎配置
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("/config/activiti.cfg.xml");
        // 2 创建流程引擎
        ProcessEngine engine = cfg.buildProcessEngine();
        //3.获取 RepositoryService
        RepositoryService repositoryService = engine.getRepositoryService();

        //4 部署流程图
        repositoryService.createDeployment().addClasspathResource("processes/Two.bpmn20.xml").deploy();
        //5. 获取运行时候服务RuntimeService
        RuntimeService runtimeService = engine.getRuntimeService();

        //6.获取流程实例
        String processDefinitionKey = "mine";
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        System.out.println(instance.getActivityId());
        //7.获取TaskService
        TaskService taskServer = engine.getTaskService();
        long count = taskServer.createTaskQuery().count();
        System.out.println("count:" + count);
        //8. 查询Task
        Task task = taskServer.createTaskQuery().singleResult();

        //9 处理任务
        System.out.println(task.getName());

    }
}
