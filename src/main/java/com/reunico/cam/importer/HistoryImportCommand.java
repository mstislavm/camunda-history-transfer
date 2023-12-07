package com.reunico.cam.importer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@RequiredArgsConstructor
@Slf4j
@Component
public class HistoryImportCommand implements Command<Object>, Serializable {

    private DbHistoryEventHandler eventHandler;
    private HistoryEvent historyEvent;


    @Override
    public Object execute(CommandContext commandContext) {
        eventHandler.handleEvent(historyEvent);
        return null;
    }

    public void execute() {
        eventHandler.handleEvent(historyEvent);
    }

}
