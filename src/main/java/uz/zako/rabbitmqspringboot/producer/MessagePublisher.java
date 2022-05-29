package uz.zako.rabbitmqspringboot.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zako.rabbitmqspringboot.producer.constants.RabbitMQConstants;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(RabbitMQConstants.EXCHANGE,
                RabbitMQConstants.ROUTING_KEY, message);
//        template.convertAndSend(RabbitMQConstants.ROUTING_KEY, message);
        return "Message Published";
    }
}
