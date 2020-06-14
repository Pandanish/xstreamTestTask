package com.testxstream.server.dao;

import com.testxstream.server.model.tcp.documents.Docs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Docs, UUID> {

     Optional<Docs> findById(UUID id);
}
