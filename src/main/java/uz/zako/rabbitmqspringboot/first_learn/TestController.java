package uz.zako.rabbitmqspringboot.first_learn;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name) {

        Person person = new Person(1L, "Azamat");
        rabbitTemplate.convertAndSend("Mobile", person);
//        rabbitTemplate.convertAndSend("Direct-Exchange","Mobile",person);
//        rabbitTemplate.convertAndSend("Fanout-Exchange","",person);
//        rabbitTemplate.convertAndSend("Topic-Exchange","tv.mobile.ac",person);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutput output = null;
        try {
            output = new ObjectOutputStream(outputStream);
            output.writeObject(person);
            output.flush();
            output.close();

            byte[] byteMessage = outputStream.toByteArray();
            outputStream.close();
            Message message = MessageBuilder.withBody(byteMessage)
                    .setHeader("item1","mobile")
                    .setHeader("item2","television")
                    .build();
            rabbitTemplate.send("Headers-Exchange","",message);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Success";
    }

}
