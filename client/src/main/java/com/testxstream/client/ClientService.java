package com.testxstream.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testxstream.client.generator.XmlBodyGenerator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class ClientService {

    private final ObjectMapper objectMapper;
    private final List<XmlBodyGenerator> xmlBodyGenerators;

    public ClientService(ObjectMapper objectMapper, List<XmlBodyGenerator> xmlBodyGenerators) {
        this.objectMapper = objectMapper;
        this.xmlBodyGenerators = xmlBodyGenerators;
    }

    public GenericMessage<String> send() {

        Random random = new Random();
        Integer index = random.nextInt(xmlBodyGenerators.size());
        log.info("Sending Heartbeat");
        return new GenericMessage<String>(getStringMessage(xmlBodyGenerators.get(index)));
    }

    @SneakyThrows
    private String getStringMessage(XmlBodyGenerator xmlBodyGenerator) {
        Message messageSend = new Message();
        messageSend.setFileName(RandomStringUtils.randomAlphabetic(10) + ".xml");
        messageSend.setType(xmlBodyGenerator.getType());
        messageSend.setBase64Body(Base64.encodeBase64String(xmlBodyGenerator.generateContent().getBytes(StandardCharsets.UTF_8)));
        return objectMapper.writeValueAsString(messageSend);
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


}
