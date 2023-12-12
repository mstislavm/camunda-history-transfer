package com.reunico.cam;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.calendar.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@SpringBootTest
class ApplicationTests {

    @Autowired
    RuntimeService runtimeService;


    @Test
    void startTest() {
        Long count = 1000000L;
        Date dueDate = DateTimeUtil.parseDate("2023-12-11T19:00:00+0300");
        System.out.println(dueDate);
        startProcess(dueDate, count);
    }

    void startProcess(Date dueDate, Long count) {
        for (long i = 0L; i <=count; i++) {
            String repeatKey = "handler-process";
            runtimeService.createProcessInstanceByKey(repeatKey)
                    .businessKey(String.valueOf(i))
                    .setVariable("test", i)
                    .setVariable("dueDate", dueDate)
                    .execute();
        }
    }

}

