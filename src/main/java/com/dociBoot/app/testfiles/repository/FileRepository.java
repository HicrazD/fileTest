package com.dociBoot.app.testfiles.repository;

import com.dociBoot.app.testfiles.entity.FileTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileTest,Long> {
}
