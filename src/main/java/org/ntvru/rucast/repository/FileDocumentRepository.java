package org.ntvru.rucast.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.ntvru.rucast.model.FileDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface FileDocumentRepository extends CrudRepository<FileDocument, Long> {

}
