package com.reunico.cam.exporter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoryEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(String processInstanceId, HistoryEventDto historyEventDto) throws JsonProcessingException {
        kafkaTemplate.sendDefault(processInstanceId, objectMapper.writeValueAsString(historyEventDto));

    }
}
