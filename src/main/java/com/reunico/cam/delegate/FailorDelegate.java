package com.reunico.cam.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class FailorDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        throw new Exception("Pretend to be an exception");
    }
}
