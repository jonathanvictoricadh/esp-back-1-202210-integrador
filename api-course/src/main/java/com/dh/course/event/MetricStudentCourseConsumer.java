package com.dh.course.event;

import com.dh.course.config.RabbitMQConfig;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MetricStudentCourseConsumer {
    private static final Logger log = LoggerFactory.getLogger(MetricStudentCourseConsumer.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_COURSE)
    public void receiveMessage(final MetricStudentCourseData message) {
        log.info("Received message as a generic AMQP 'Message' wrapper: {}", message.operationId);
    }

    @Getter
    @Setter
    public static class MetricStudentCourseData {
        private Long studentId;
        private String operationId;
    }
}
