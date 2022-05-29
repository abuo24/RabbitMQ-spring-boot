package uz.zako.rabbitmqspringboot.deadletter;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.zako.rabbitmqspringboot.deadletter.constants.RabbitConstants;

@Configuration("RabbitMQConfigDeadLetter")
public class RabbitMQConfig {

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(RabbitConstants.DLE);
    }

    @Bean
    DirectExchange exchangeStores() {
        return new DirectExchange(RabbitConstants.STORES_EXCHANGE);
    }

    @Bean
    Queue dlq() {
        return QueueBuilder.durable(RabbitConstants.DLQ).build();
    }

    @Bean
    Queue queueStores() {
        return QueueBuilder.durable(RabbitConstants.STORES_QUEUE).withArgument("x-dead-letter-exchange", RabbitConstants.DLE)
                .withArgument("x-dead-letter-routing-key", RabbitConstants.RK_DS).build();
    }

    @Bean
    Binding DLQbinding() {
        return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with(RabbitConstants.RK_DS);
    }

    @Bean
    Binding bindingDeadLetter() {
        return BindingBuilder.bind(queueStores()).to(exchangeStores()).with(RabbitConstants.RK_S);
    }

//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//        return rabbitTemplate;
//    }
}