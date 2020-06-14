package com.testxstream.server.service.mapper.impl;

import com.testxstream.server.constant.DocumentType;
import com.testxstream.server.dto.DesadvDTO;
import com.testxstream.server.dto.OrderDTO;
import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.service.mapper.AbstractDocumentMapper;
import com.testxstream.server.web.model.DocumentDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(OrderDocumentMapperDecorator.class)
public abstract class OrderDocumentMapper implements AbstractDocumentMapper<Docs, DocumentDTO<OrderDTO>> {

    public String getMapperDocumentType() {
        return DocumentType.ORDERDTO.name();
    }

    @Override
    public boolean checkType(Docs docs) {
        return false;
    }
}
