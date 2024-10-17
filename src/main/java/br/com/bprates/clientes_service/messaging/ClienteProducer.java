package br.com.bprates.clientes_service.messaging;

import br.com.bprates.clientes_service.configs.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClienteProducer {

    private static final Logger logger = LoggerFactory.getLogger(ClienteProducer.class);

    private static final String QUEUE_NAME = RabbitMQConfig.QUEUE_NAME;

    private final RabbitTemplate rabbitTemplate;

    public ClienteProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        logger.info("ClienteProducer foi instanciado.");
    }

    public void enviarMensagem(String mensagem) {
        try {
            rabbitTemplate.convertAndSend(QUEUE_NAME, mensagem);
            logger.info("Mensagem enviada: {}", mensagem);
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem para o RabbitMQ: {}", e.getMessage(), e);
        }
    }
}
