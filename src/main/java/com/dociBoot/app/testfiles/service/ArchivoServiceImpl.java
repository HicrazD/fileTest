package com.dociBoot.app.testfiles.service;

import com.dociBoot.app.testfiles.entity.Archivo;
import com.dociBoot.app.testfiles.repository.ArchivoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ArchivoServiceImpl implements ArchivoService{

    @Autowired
    private ArchivoRepository repository;


    private final Logger log = LoggerFactory.getLogger(ArchivoServiceImpl.class);

    @Override
    public Archivo createArchivo(MultipartFile file) throws IOException {

        Archivo archivo = new Archivo().builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(file.getBytes())
                .build();

        return repository.save(archivo);
    }

    @Override
    public Optional<Archivo> getArchivoById(Long id) {
        return repository.findById(id);
    }
}
