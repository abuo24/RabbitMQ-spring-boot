package uz.zako.rabbitmqspringboot.producer.constants;

public interface RabbitMQConstants {
     String QUEUE = "message_queue";
     String QUEUE_TEST = "message_queue.coder24";
     String EXCHANGE = "message_exchange";
     String ROUTING_KEY = "*.message_routingKey.*";

}
