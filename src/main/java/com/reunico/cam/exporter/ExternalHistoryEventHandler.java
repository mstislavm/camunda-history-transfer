package com.reunico.cam.exporter;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ExternalHistoryEventHandler  implements HistoryEventHandler {

    private final HistoryEventProducer producer;

    public ExternalHistoryEventHandler(HistoryEventProducer producer) {
        this.producer = producer;
    }

    ExternalHistoryEventHandler externalHistoryEventHandler(HistoryEventProducer producer) {
        return new ExternalHistoryEventHandler(producer);
    }

    @Override
    public void handleEvent(HistoryEvent historyEvent) {
        try {
            producer.send(
                    historyEvent.getProcessInstanceId(),
                    new HistoryEventDto(historyEvent.getClass().toString(), historyEvent)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleEvents(List<HistoryEvent> historyEvents) {
        historyEvents.forEach(this::handleEvent);
    }
}
