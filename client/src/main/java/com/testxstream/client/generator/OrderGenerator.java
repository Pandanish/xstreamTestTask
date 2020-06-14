package com.testxstream.client.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderGenerator implements XmlBodyGenerator {
    @Override
    public String generateContent() {
        return getOrderRandomContent();
    }

    @Override
    public String getType() {
        return "order";
    }

    private String getOrderRandomContent(){

        Random random =new Random();
        return String.format(getOrderTemplate(),random.nextInt(5000), RandomStringUtils.randomAlphabetic(10) );
    }
    private String getOrderTemplate(){
        return "<Order>\n" +
                "  <id>%s</id>\n" +
                "  <message>%s</message>\n" +
                "</Order>";
    }


}
