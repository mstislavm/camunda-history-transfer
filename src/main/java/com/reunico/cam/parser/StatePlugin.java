package com.reunico.cam.parser;

import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

import java.util.ArrayList;

//@Component
public class StatePlugin extends AbstractProcessEnginePlugin {

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        if (processEngineConfiguration.getCustomPostBPMNParseListeners() == null) {
            processEngineConfiguration.setCustomPostBPMNParseListeners(new ArrayList<BpmnParseListener>());
        }
        processEngineConfiguration.getCustomPostBPMNParseListeners().add(new StateParseListener());

    }
}
