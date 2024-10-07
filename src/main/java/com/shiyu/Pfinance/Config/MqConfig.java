package com.shiyu.Pfinance.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    public static String topicExchangeName = "pfinance-notification";

    public static String queueName = "pfinance-queue";

    @Bean
    public TopicExchange topic(){
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topic){
        return BindingBuilder.bind(queue).to(topic).with("pfinance.notification.#");
    }

}
