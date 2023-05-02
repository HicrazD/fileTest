package com.dociBoot.app.testfiles.controller;

import com.dociBoot.app.testfiles.entity.Archivo;
import com.dociBoot.app.testfiles.entity.FileTest;
import com.dociBoot.app.testfiles.exceptions.ResourceNotFoundException;
import com.dociBoot.app.testfiles.service.ArchivoService;
import com.dociBoot.app.testfiles.service.FileTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/file")
public class FileTestController {

    @Autowired
    private FileTestService service;

    @Autowired
    private ArchivoService archivoService;

    @PostMapping("/images")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        FileTest fileTest = service.saveImage(file);
        return ResponseEntity.ok(fileTest);
    }

    @PostMapping("/archivos")
    public ResponseEntity<?> uploadArchivo(@RequestParam("file") MultipartFile file) throws IOException{

        if(file.isEmpty())  throw new IllegalArgumentException("El archivo esta vacio");

        Archivo archivo = archivoService.createArchivo(file);

        return ResponseEntity.ok(archivo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Long id){

        Optional<FileTest> o = service.getImageById(id);

        if(o.isPresent()){
            FileTest fileTest = o.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(fileTest.getData());
        }
        else return ResponseEntity.notFound().build();

    }

    @GetMapping("/doc/{id}")
    public ResponseEntity<?> getArchivoById(@PathVariable Long id){

        Archivo archivo = archivoService.getArchivoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el archivo"));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(archivo.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + archivo.getName() + "\"")
                .body(archivo.getData());
    }
}
