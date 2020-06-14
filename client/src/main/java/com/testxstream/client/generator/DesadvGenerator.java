package com.testxstream.client.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DesadvGenerator implements XmlBodyGenerator {
    @Override
    public String generateContent() {
        return getDesadvRandomContent();
    }

    @Override
    public String getType() {
        return "desadv";
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
