package com.testxstream.server.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO<T> {

    private T document;
    private String docId;
    private String docBody;
    private String sender;
    private String recipient;
    private String file_name;
}
