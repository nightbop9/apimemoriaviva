package com.api.memoriaviva.controller;

import java.util.List;
import java.util.Map;

import com.api.memoriaviva.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.memoriaviva.dto.PostDTO;
import com.api.memoriaviva.service.PostService;
import com.api.memoriaviva.util.ResponseBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    PostService service;

    @Autowired
    ImageService imageService;

    @GetMapping("/list")
    public ResponseEntity<List<PostDTO>> list() {
        return ResponseEntity.ok(service.list());
    }

    /* @Valid - Ativa a validação dos campos do DTO conforme suas anotações de validação
     *
     * @ModelAttribute - Permite receber dados de formulário multipart/form-data
     *
     * @RequestParam - Captura parâmetros da requisição HTTP (query string ou form-data)
     *
     * @PathVariable - Extrai valores das variáveis presentes na URL
     */

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @ModelAttribute PostDTO postDTO,
                                         @RequestParam(value = "image", required = false) MultipartFile file) {
        service.create(postDTO, file);
        return ResponseBuilder.buildMessage(HttpStatus.OK, "Post criado com sucesso.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @ModelAttribute PostDTO postDTO,
                                         @RequestParam(value = "image", required = false) MultipartFile file) {
        service.update(id, postDTO, file);
        return ResponseBuilder.buildMessage(HttpStatus.OK, "Post atualizado com sucesso.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseBuilder.buildMessage(HttpStatus.OK, "Post excluído com sucesso.");
    }

}
