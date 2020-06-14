package com.testxstream.server.service.mapper.impl;

import com.testxstream.server.dto.DesadvDTO;
import com.testxstream.server.dto.OrderDTO;
import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.service.DocumentService;
import com.testxstream.server.web.model.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;


public  abstract class OrderDocumentMapperDecorator extends OrderDocumentMapper {

    @Autowired
    private OrderDocumentMapper delegate;

    @Autowired
    private DocumentService documentService;


    @Override
    public DocumentDTO<OrderDTO> toDTO(Docs docs) {
        DocumentDTO<OrderDTO> documentDTO = delegate.toDTO(docs);

        documentDTO.setDocument(documentService.getDocumentDTOFromBody(docs.getDocBody(), OrderDTO.class));
        return documentDTO;
    }

    @Override
    public boolean checkType(Docs e){
      Class clazz =  documentService.documentType(e.getDocBody());
      if (clazz.getSimpleName().equalsIgnoreCase(getMapperDocumentType())){
          return true;
      }
      return false;
    }


}
