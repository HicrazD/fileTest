package com.dociBoot.app.testfiles.service;

import com.dociBoot.app.testfiles.entity.FileTest;
import com.dociBoot.app.testfiles.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileTestImpl implements FileTestService {

    @Autowired
    private FileRepository repository;


    @Override
    public FileTest saveImage(MultipartFile file) throws IOException {
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file.getResource().getURI());
        FileTest fileTest =  new FileTest().builder()
                .name(file.getOriginalFilename())
                .data(file.getBytes()).build();
        return repository.save(fileTest);
    }

    @Override
    public Optional<FileTest> getImageById(Long id) {
        return repository.findById(id);
    }
}
