package com.dh.course.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "digitalHouseExchange";
    public static final String QUEUE_STUDENT = "studentsMetricsQueue";
    public static final String QUEUE_COURSE = "courseMetricsQueue";
    public static final String ROUTING_KEY_METRIC_COURSE_RESPONSE = "com.digitalhouse.metriccourseresponse";


    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }



    @Bean
    public Queue studentQueue() {
        return new Queue(QUEUE_STUDENT);
    }


    @Bean
    public Binding declareBindingSpecific() {
        return BindingBuilder.bind(studentQueue()).to(appExchange()).with(ROUTING_KEY_METRIC_COURSE_RESPONSE);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
