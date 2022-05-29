package uz.zako.rabbitmqspringboot.deadletter.constants;

public interface RabbitConstants {
    String DLE = "DEAD_LETTER_EXCHANGE";
    String DLQ = "dead-letter.queue";
    String STORES_EXCHANGE = "STORES_EXCHANGE";
    String STORES_QUEUE = "stores.queue";
    String RK_S = "stores";
    String RK_DS = "dead-letter";
}
