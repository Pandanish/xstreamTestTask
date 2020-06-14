package com.testxstream.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testxstream.server.dao.DocumentRepository;
import com.testxstream.server.service.DocumentService;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

@Service
public class ServerSocketService {

    private final ObjectMapper objectMapper;
    private final Logger log = LogManager.getLogger(ServerSocketService.class);
    private final DocumentRepository documentRepository;
    private final DocumentService documentService;

    public ServerSocketService(ObjectMapper objectMapper, DocumentRepository documentRepository, XStream xStream, DocumentService documentService) {
        this.objectMapper = objectMapper;
        this.documentRepository = documentRepository;
        this.documentService = documentService;
    }


    public Message<String> processRequest(byte[] payload, MessageHeaders messageHeaders) {
        String messageStr = new String(payload);
        try {
            com.testxstream.server.model.tcp.Message message = objectMapper.readValue(messageStr, com.testxstream.server.model.tcp.Message.class);

            String xmlDocumentBody = documentService.getXmlDocumentBodyFromBase64(message.getBase64Body());

            documentService.saveDocument(messageHeaders, message, xmlDocumentBody);
            log.info("message server received connection id {}, sender {}, recipient {}, filename {}.",
                    messageHeaders.get("ip_connectionId"),
                    messageHeaders.get("ip_address"),
                    messageHeaders.get("ip_localInetAddress"),
                    message.getFileName());
            return new GenericMessage<>("{\"code\" :200, \"message\" : \"OK\"}");
        } catch (JsonProcessingException e) {
            log.error("Unexpected message content from client: {}", messageStr);
            return null;
        }

    }


}
