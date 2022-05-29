package uz.zako.rabbitmqspringboot.producer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import uz.zako.rabbitmqspringboot.producer.constants.RabbitMQConstants;

@Component("rabbit-mq-consumer")
public class RabbitMQConsumer {

//    @RabbitListener(queues = {RabbitMQConstants.QUEUE, RabbitMQConstants.QUEUE_TEST})
//    public void listener(CustomMessage message) {
//        System.out.println(message);
//    }


}
