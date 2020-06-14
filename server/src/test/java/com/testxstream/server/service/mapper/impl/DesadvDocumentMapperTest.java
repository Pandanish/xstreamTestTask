package com.testxstream.server.service.mapper.impl;

import com.testxstream.server.dto.DesadvDTO;
import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.service.mapper.MapperService;
import com.testxstream.server.web.model.DocumentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DesadvDocumentMapperTest {

    @Autowired
    private MapperService mapperService;

    @Test
    public void getDocsWebDTOFromMapper(){

        Docs doc = new Docs();
        doc.setId(UUID.randomUUID());
        doc.setDocId("1");
        doc.setRecipient("dd");
        doc.setSender("ss");
        doc.setFile_name("sds.xml");
        doc.setDocBody("<Desadv>\n" +
                "  <id>1</id>\n" +
                "  <content>12</content>\n" +
                "</Desadv>");

        DocumentDTO<DesadvDTO> desadvDTODocumentDTO =(DocumentDTO<DesadvDTO>)mapperService.getMapperByDoc(doc).toDTO(doc);
        System.out.println(desadvDTODocumentDTO);
    }

}