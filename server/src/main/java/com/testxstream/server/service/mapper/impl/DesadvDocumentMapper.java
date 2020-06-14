package com.testxstream.server.service.mapper.impl;

import com.testxstream.server.constant.DocumentType;
import com.testxstream.server.dto.DesadvDTO;
import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.service.mapper.AbstractDocumentMapper;
import com.testxstream.server.web.model.DocumentDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(DesadvDocumentMapperDecorator.class)
public abstract class DesadvDocumentMapper implements AbstractDocumentMapper<Docs, DocumentDTO<DesadvDTO>> {

    @Override
    public boolean checkType(Docs docs) {
        return false;
    }

    public String getMapperDocumentType() {
        return DocumentType.DESADVDTO.name();
    }
}
