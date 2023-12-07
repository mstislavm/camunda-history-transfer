package com.reunico.cam.exporter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessEnginePlugin extends AbstractProcessEnginePlugin {

    private final ExternalHistoryEventHandler historyEventHandler;

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        log.info("Default History backend disabled");
       // processEngineConfiguration.setEnableDefaultDbHistoryEventHandler(false);
        processEngineConfiguration.setHistoryEventHandler(historyEventHandler);
    }
}
