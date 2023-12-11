package com.reunico.cam.exporter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HistorySerializer<T>  implements HistoryEventHandler {

    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    /* Не использовать Async - проблема с извлечением связанных данных,
    напр. entity.HistoricJobLogEventEntity["exceptionStacktrace"] */
    public void handleEvent(HistoryEvent historyEvent) {
        kafkaTemplate.sendDefault(
                historyEvent.getProcessInstanceId(),
                (T) new HistoryEventDto(
                        historyEvent.getClass().getCanonicalName(),
                        historyEvent)
        );
    }

    @Override
    public void handleEvents(List<HistoryEvent> historyEvents) {
        historyEvents.forEach(this::handleEvent);
    }


}
