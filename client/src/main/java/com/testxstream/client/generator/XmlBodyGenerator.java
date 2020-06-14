package com.testxstream.client.generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public interface XmlBodyGenerator {

    String generateContent();

    String getType();
}
