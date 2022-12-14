package exercise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMqConfig {

    @Bean
    Queue queue() {
        // Задаём имя очереди
        return new Queue("queue", false);
    }

    @Bean
    TopicExchange exchange() {
        // Задаём имя "обменника". Как и имя очереди, оно может быть любым
        return new TopicExchange("exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        // Сообщения с ключом "key" будут направлены в очередь "queue"
        return BindingBuilder.bind(queue).to(exchange).with("key");
    }
}
