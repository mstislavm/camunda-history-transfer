package com.reunico.cam.importer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reunico.cam.exporter.HistoryEventDto;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HistoryDeserializer {

    private final ObjectMapper objectMapper;

    public HistoryDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    public<T extends HistoryEvent> T deserialize(String raw) throws JsonProcessingException {
        HistoryEventDto dto = objectMapper.readValue(raw, HistoryEventDto.class);
        return cast(dto);
    }

    public HistoryEvent deserializeRaw(String raw) throws JsonProcessingException, ClassNotFoundException {
        HistoryEventDto dto = objectMapper.readValue(raw, HistoryEventDto.class);
        return castRaw(dto);
    }

    private<T extends HistoryEvent> T cast(HistoryEventDto historyEventDto) {
        Class<T> clazz;
        try {
            clazz = (Class<T>) Class.forName(historyEventDto.getHistoryClassName());
            return clazz.cast(historyEventDto.getHistoryEvent());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private HistoryEvent castRaw(HistoryEventDto historyEventDto) throws ClassNotFoundException {
        var clazz = Class.forName(historyEventDto.getHistoryClassName());
        return (HistoryEvent) clazz.cast(historyEventDto.getHistoryEvent());
    }


}
