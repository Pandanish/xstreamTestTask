package com.testxstream.server.service.mapper.impl;

import com.testxstream.server.dto.DesadvDTO;
import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.service.DocumentService;
import com.testxstream.server.web.model.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class DesadvDocumentMapperDecorator extends DesadvDocumentMapper {

    @Autowired
    private DesadvDocumentMapper delegate;

    @Autowired
    private DocumentService documentService;


    @Override
    public DocumentDTO<DesadvDTO> toDTO(Docs docs) {
        DocumentDTO<DesadvDTO> documentDTO = delegate.toDTO(docs);

        documentDTO.setDocument(documentService.getDocumentDTOFromBody(docs.getDocBody(), DesadvDTO.class));
        return documentDTO;
    }

    @Override
    public boolean checkType(Docs e) {
        Class clazz = documentService.documentType(e.getDocBody());
        return clazz.getSimpleName().equalsIgnoreCase(getMapperDocumentType());
    }


}
