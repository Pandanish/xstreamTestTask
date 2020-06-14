package com.testxstream.server.service.mapper.impl;

import com.testxstream.server.dto.OrderDTO;
import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.service.mapper.MapperService;
import com.testxstream.server.web.model.DocumentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderDocumentMapperTest {

    @Autowired
    private MapperService mapperService;

    @Test
    public void getOrderMapper_whenIdNotNull(){

        Docs doc = new Docs();
        doc.setId(UUID.randomUUID());
        doc.setDocId("1");
        doc.setRecipient("dd");
        doc.setSender("ss");
        doc.setFile_name("sds.xml");
        doc.setDocBody("<Order>\n" +
                "  <id>1</id>\n" +
                "  <message>12</message>\n" +
                "</Order>");

        DocumentDTO<OrderDTO> desadvDTODocumentDTO =(DocumentDTO<OrderDTO>)mapperService.getMapperByDoc(doc).toDTO(doc);
        System.out.println(desadvDTODocumentDTO);
        Assert.notNull(desadvDTODocumentDTO.getDocument().getId(), "id is null");
    }
}