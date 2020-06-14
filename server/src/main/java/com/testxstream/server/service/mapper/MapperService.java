package com.testxstream.server.service.mapper;

import com.testxstream.server.model.tcp.documents.Docs;
import com.testxstream.server.web.model.DocumentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperService {

    private final List<AbstractDocumentMapper<Docs, ? extends DocumentDTO>> documentMappers;

    public MapperService(List<AbstractDocumentMapper<Docs, ? extends DocumentDTO>> documentMappers) {
        this.documentMappers = documentMappers;
    }

    public AbstractDocumentMapper<Docs, ? extends DocumentDTO> getMapperByDoc(Docs docs) {
        return documentMappers.stream()
                .filter(mapper -> mapper.checkType(docs))
                .findFirst().get();

    }
}
