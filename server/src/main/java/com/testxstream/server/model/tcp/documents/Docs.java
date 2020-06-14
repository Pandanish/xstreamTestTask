package com.testxstream.server.model.tcp.documents;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Docs implements Persistable<UUID> {

    @Nullable
    private UUID id;

    private String docId;
    private String docBody;
    private String sender;
    private String recipient;
    private String file_name;

    public String getDocId() {
        return docId;
    }    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "uuid", updatable = false)
    // мапится как GEOMETRY без этого указания. https://hibernate.atlassian.net/browse/HHH-11490
    @Nullable
    public UUID getId() {
        return id;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }    public void setId(@Nullable UUID id) {
        this.id = id;
    }

    public String getDocBody() {
        return docBody;
    }    /**
     * Must be {@link Transient} in order to ensure that no JPA provider complains because of a missing setter.
     *
     * @see Persistable#isNew()
     */
    @Transient // DATAJPA-622
    public boolean isNew() {
        return null == getId();
    }

    public void setDocBody(String docBody) {
        this.docBody = docBody;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }







}
