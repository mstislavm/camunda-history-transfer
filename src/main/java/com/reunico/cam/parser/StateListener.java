package com.reunico.cam.parser;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class StateListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) {
        System.out.println(delegateExecution.getCurrentActivityId());
        System.out.println(delegateExecution.getVariables().size());
    }
}
