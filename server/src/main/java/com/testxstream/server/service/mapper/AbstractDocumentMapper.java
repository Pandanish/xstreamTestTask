package com.testxstream.server.service.mapper;

import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.web.model.DocumentDTO;


public interface AbstractDocumentMapper<E extends Docs, ResponseDTO extends DocumentDTO<?>> {


     boolean checkType(Docs e);


      String getMapperDocumentType();

     ResponseDTO toDTO(E e);
}
