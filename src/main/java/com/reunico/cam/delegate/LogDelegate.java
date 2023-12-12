package com.reunico.cam.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Job;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@Slf4j
@RequiredArgsConstructor
public class LogDelegate implements JavaDelegate {

    private final ManagementService managementService;
    private final RepositoryService repositoryService;
    private final RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Variable is: {} {}",
                execution.getVariable("dueDate"),
                execution.getVariable("test"));
        execution.setVariable("demo", UUID.randomUUID().toString());

        // managementService.createJobQuery().list(); //  проблемы
        runtimeService.createProcessInstanceQuery().list(); // проблемы

        // managementService.createJobQuery().count(); // нет проблем
        // repositoryService.createDeploymentQuery().list(); нет проблем
        // Thread.sleep(15000L); // тут нет проблем
    }
}
