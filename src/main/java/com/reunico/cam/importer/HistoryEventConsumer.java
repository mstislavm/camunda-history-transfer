package com.reunico.cam.importer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reunico.cam.exporter.HistoryEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutorImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class HistoryEventConsumer {

    private final HistoryDeserializer historyDeserializer;

    public HistoryEventConsumer(HistoryDeserializer historyDeserializer) {
        this.historyDeserializer = historyDeserializer;
    }



    @KafkaListener(id = "camunda.consumer", topics = "#{'${spring.kafka.template.default-topic}'}")
    public void listen(HistoryEvent historyEvent) {
        log.info("Message received={}", historyEvent);
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration();
        CommandExecutor commandExecutor = processEngineConfiguration.getCommandExecutorTxRequired();

        Command<Object> command = commandContext -> {
            DbHistoryEventHandler dbHistoryEventHandler = new DbHistoryEventHandler();
            dbHistoryEventHandler.handleEvent(historyEvent);
            return null;
        };
        commandExecutor.execute(command);

    }

}
