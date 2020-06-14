package com.testxstream.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLengthHeaderSerializer;

import java.time.Duration;

@Configuration
@EnableIntegration
public class ClientConfig {

    @Bean
    public TcpNetClientConnectionFactory clientConnectionFactory() {
        TcpNetClientConnectionFactory connectionFactory = new TcpNetClientConnectionFactory("localhost", 1234);
        connectionFactory.setSerializer(new ByteArrayLengthHeaderSerializer());
        connectionFactory.setDeserializer(new ByteArrayLengthHeaderSerializer());
        return connectionFactory;
    }



    @Bean
    public IntegrationFlow heartbeatClientFlow(
            TcpNetClientConnectionFactory clientConnectionFactory,
            ClientService clientService) {
        return IntegrationFlows.from(clientService::send, e -> e.poller(Pollers.fixedDelay(Duration.ofSeconds(5))))
                .handle(Tcp.outboundGateway(clientConnectionFactory))
                .handle(clientService::receive)
                .get();
    }



    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
