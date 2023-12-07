package com.reunico.cam.exporter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryEventDto {
    private String  historyClassName;
    private HistoryEvent historyEvent;
}
