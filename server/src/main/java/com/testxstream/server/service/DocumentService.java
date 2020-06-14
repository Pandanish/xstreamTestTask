package com.testxstream.server.service;

import com.testxstream.server.dao.DocumentRepository;
import com.testxstream.server.dto.DesadvDTO;
import com.testxstream.server.dto.OrderDTO;
import com.testxstream.server.model.tcp.documents.Docs;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final XStream xStream;


    public DocumentService(DocumentRepository documentRepository, XStream xStream) {
        this.documentRepository = documentRepository;
        this.xStream = xStream;

    }

    public Docs getDocument(UUID documentId) {

        Optional<Docs> doc = documentRepository.findById(documentId);
        log.info("doc type {}", doc.get().getFile_name());
        return doc.get();
    }

    public String getXmlDocumentBodyFromBase64(String base64XmlBodyDocument) {

        return decodeBase64(base64XmlBodyDocument);
    }

    private String decodeBase64(String encodedString) {

        return new String(Base64.decodeBase64(encodedString), StandardCharsets.UTF_8);
    }

    public <T> T getDocumentDTOFromBody(String xmlBody, Class<T> clazz) {
        Object document = xStream.fromXML(xmlBody);

        return convertInstanceOfObject(document, clazz);
    }

    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {

        return clazz.cast(o);

    }

    public void saveDocument(MessageHeaders messageHeaders, com.testxstream.server.model.tcp.Message message, String xmlDocumentBody) {
        Docs docs = new Docs();
        docs.setDocId(this.getDocumentID(xmlDocumentBody));

        docs.setDocBody(xmlDocumentBody);
        docs.setFile_name(message.getFileName());
        docs.setSender(messageHeaders.get("ip_address").toString());
        docs.setRecipient(messageHeaders.get("ip_localInetAddress").toString());
        documentRepository.save(docs);
    }

    public String getDocumentID(String xmlBodyDocument) {

        Object document = xStream.fromXML(xmlBodyDocument);

        if (document instanceof DesadvDTO) {
            return convertInstanceOfObject(document, DesadvDTO.class).getId();
        }
        if (document instanceof OrderDTO) {
            return convertInstanceOfObject(document, OrderDTO.class).getId();
        }
        return null;
    }

    public Class documentType(String xmlBodyDocument) {
        Object document = xStream.fromXML(xmlBodyDocument);

        if (document instanceof DesadvDTO) {
            return DesadvDTO.class;
        }
        if (document instanceof OrderDTO) {
            return OrderDTO.class;
        }
        return null;
    }


}
