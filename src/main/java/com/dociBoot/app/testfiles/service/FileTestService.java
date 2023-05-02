package com.dociBoot.app.testfiles.service;

import com.dociBoot.app.testfiles.entity.FileTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FileTestService {

    FileTest saveImage(MultipartFile file) throws IOException;
    Optional<FileTest> getImageById(Long id);
}
