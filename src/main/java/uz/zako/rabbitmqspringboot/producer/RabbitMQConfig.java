package uz.zako.rabbitmqspringboot.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.zako.rabbitmqspringboot.producer.constants.RabbitMQConstants;

@Configuration
public class RabbitMQConfig {


    //     bu navbat biz yuborgan malumotlar queue da saqlanadi
    @Bean
    public Queue queue() {
        return new Queue(RabbitMQConstants.QUEUE);
    }

    //     bu navbat biz yuborgan malumotlar queue da saqlanadi
    @Bean
    public Queue queueTest() {
        return new Queue(RabbitMQConstants.QUEUE_TEST);
    }

    //    bu exchange biz qaysi queuega so'rov yuborayotganimizni belgilab turadi
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(RabbitMQConstants.EXCHANGE);
    }

    //     bu Exchange va QUeue orasida turadigan dallol
//    @Bean
//    public Binding binding() {
//        return BindingBuilder
//                .bind(queue())
//                .to(exchange())
//                .with(RabbitMQConstants.ROUTING_KEY);
//    }
//
//    @Bean
//    public Binding bindingTest() {
//        return BindingBuilder
//                .bind(queueTest())
//                .to(exchange())
//                .with(RabbitMQConstants.ROUTING_KEY);
//    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(RabbitMQConstants.ROUTING_KEY);
    }

    @Bean
    public Binding bindingTest(Queue queueTest, TopicExchange exchange) {
        return BindingBuilder
                .bind(queueTest)
                .to(exchange)
                .with(RabbitMQConstants.ROUTING_KEY);
    }

    //    Messagemizni convert qilib beradi
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //    bu bizni connectionni taminlab beradi
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

}