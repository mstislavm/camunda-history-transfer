package com.reunico.cam.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalTime;

//@Component
@Slf4j
public class GeneralListener {
    @EventListener
    public void onExecutionEvent(DelegateExecution delegateExecution) {
        logger(delegateExecution);
    }


    @Async
    void logger(DelegateExecution delegateExecution) {
        log.debug("{} {}", LocalTime.now(), delegateExecution.getCurrentActivityId());
    }

}
