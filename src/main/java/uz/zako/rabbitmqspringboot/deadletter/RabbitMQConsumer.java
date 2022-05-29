package uz.zako.rabbitmqspringboot.deadletter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import uz.zako.rabbitmqspringboot.deadletter.constants.RabbitConstants;

@Component("RabbitMQConsumerDeadLetter")
public class RabbitMQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = RabbitConstants.STORES_QUEUE)
    public void recievedMessage(CustomMessage customMessage) throws Exception {
        logger.info("Recieved Message From RabbitMQ: " + customMessage);
        if (customMessage.getMessageId() == null) {
            throw new RuntimeException("somthing went wrong");
        }
    }

}