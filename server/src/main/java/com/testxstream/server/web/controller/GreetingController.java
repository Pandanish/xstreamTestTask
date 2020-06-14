package com.testxstream.server.web.controller;

import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.service.DocumentService;
import com.testxstream.server.service.mapper.MapperService;
import com.testxstream.server.web.model.DocumentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.testxstream.server.web.model.HelloMessage;

@Controller
@Slf4j
public class GreetingController {

    private final DocumentService documentService;

    private final MapperService mapperService;
    public GreetingController(DocumentService documentService, MapperService mapperService) {
        this.documentService = documentService;
        this.mapperService = mapperService;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public DocumentDTO<?> greeting(HelloMessage message) throws Exception {

       Docs doc =  documentService.getDocument(message.getDocumentKey());
        log.info("return docId {}",doc.getId());
       return mapperService.getMapperByDoc(doc).toDTO(doc);
    }
}
