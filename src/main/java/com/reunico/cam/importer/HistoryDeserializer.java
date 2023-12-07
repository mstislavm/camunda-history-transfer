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

    public HistoryEvent deserializeRaw(String raw) {
        try {
            HistoryEventDto dto = objectMapper.readValue(raw, HistoryEventDto.class);
            return castRaw(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    private <T extends HistoryEvent> T castRaw(HistoryEventDto historyEventDto) throws ClassNotFoundException {

       //  Class clazz = Thread.currentThread().getContextClassLoader().loadClass(historyEventDto.getHistoryClassName());
        // Class clazz = ClassLoader.getSystemClassLoader().loadClass(historyEventDto.getHistoryClassName());

        var clazz =  (Class<T>) Class.forName(historyEventDto.getHistoryClassName());

        return clazz.cast(historyEventDto.getHistoryEvent());
    }

}
