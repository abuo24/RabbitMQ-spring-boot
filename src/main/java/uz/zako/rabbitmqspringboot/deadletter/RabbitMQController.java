package uz.zako.rabbitmqspringboot.deadletter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zako.rabbitmqspringboot.deadletter.constants.RabbitConstants;
import uz.zako.rabbitmqspringboot.producer.constants.RabbitMQConstants;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/rabbit")
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/producer")
    public String producer(@RequestBody CustomMessage message) {
        if (message.getMessage() == null) {
            message.setMessageId(null);
        } else {
            message.setMessageId(UUID.randomUUID().toString());
        }
        message.setMessageDate(new Date());

        rabbitTemplate.convertAndSend(RabbitConstants.STORES_EXCHANGE,
                RabbitConstants.RK_S, message);

        return "Message sent to the RabbitMQ Successfully";
    }


}