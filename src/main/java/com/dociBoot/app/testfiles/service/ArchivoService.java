package com.dociBoot.app.testfiles.service;

import com.dociBoot.app.testfiles.entity.Archivo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ArchivoService {

    Archivo createArchivo(MultipartFile file) throws IOException;

    Optional<Archivo> getArchivoById(Long id);
}
