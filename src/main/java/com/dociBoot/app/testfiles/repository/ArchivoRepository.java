package com.dociBoot.app.testfiles.repository;

import com.dociBoot.app.testfiles.entity.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo,Long> {
}
