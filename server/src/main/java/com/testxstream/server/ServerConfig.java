package com.testxstream.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testxstream.server.dto.DesadvDTO;
import com.testxstream.server.dto.OrderDTO;
import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.service.mapper.AbstractDocumentMapper;
import com.testxstream.server.web.model.DocumentDTO;
import com.thoughtworks.xstream.XStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLengthHeaderSerializer;

import java.util.List;
import java.util.function.Function;

@Configuration
@EnableIntegration
public class ServerConfig {


    @Bean
    public TcpNetServerConnectionFactory serverConnectionFactory() {
        TcpNetServerConnectionFactory connectionFactory = new TcpNetServerConnectionFactory(1234);
        connectionFactory.setSerializer(new ByteArrayLengthHeaderSerializer());
        connectionFactory.setDeserializer(new ByteArrayLengthHeaderSerializer());
        return connectionFactory;
    }

    @Bean
    public IntegrationFlow serverFlow(
            TcpNetServerConnectionFactory serverConnectionFactory,
            ServerSocketService serverSocketService) {
        return IntegrationFlows
                .from(Tcp.inboundGateway(serverConnectionFactory))

                .handle(serverSocketService::processRequest)
                .get();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public XStream xstream(){
        XStream xstream = new XStream();
        xstream.alias("Desadv", DesadvDTO.class);
        xstream.alias("Order", OrderDTO.class);
        return xstream;
    }


}
