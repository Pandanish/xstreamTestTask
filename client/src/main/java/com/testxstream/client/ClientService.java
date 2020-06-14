package com.testxstream.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class ClientService {

    private final ObjectMapper objectMapper;

    public ClientService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public GenericMessage<String> send() {


        log.info("Sending Heartbeat");
        return new GenericMessage<String>(getStringMessage());
    }

    public Object receive(byte[] payload, MessageHeaders messageHeaders) { // LATER: use transformer() to receive String here
        String messageStr = new String(payload);

        if (messageStr.equals("OK")) {
            log.info("Heartbeat OK response received");
        } else {
            log.error("Unexpected message content from server: " + messageStr);
        }
        return null;
    }

    @SneakyThrows
    private String getStringMessage() {
        Message messageSend = new Message();
        messageSend.setFileName("DesadvFile.txt");
        messageSend.setType("desadv");
        messageSend.setBase64Body(Base64.encodeBase64String(getDesadvRandomContent().getBytes()));
        return objectMapper.writeValueAsString(messageSend);
    }

    private String getDesadvRandomContent(){

        Random random =new Random();
        return String.format(getDesadvTemplate(),random.nextInt(5000), RandomStringUtils.randomAlphabetic(10) );
    }

    private String getDesadvTemplate(){
        return "<Desadv>\n" +
                "  <id>%s</id>\n" +
                "  <content>%s</content>\n" +
                "</Desadv>";
    }
}
