package com.dh.student.event;

import com.dh.student.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MetricStudentCourseProducer {

    private static final Logger log = LoggerFactory.getLogger(MetricStudentCourseProducer.class);

    private final RabbitTemplate rabbitTemplate;


    public MetricStudentCourseProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MetricStudentCourseData data) {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_METRIC_COURSE, data);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MetricStudentCourseData {
        private Long studentId;
        private String operationId;
    }
}
