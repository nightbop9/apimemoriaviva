package com.api.memoriaviva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.memoriaviva.dto.PostDTO;
import com.api.memoriaviva.service.PostService;
import com.api.memoriaviva.util.ResponseBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    PostService service;

    @GetMapping("/list")
    public ResponseEntity<List<PostDTO>> list() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody PostDTO postDTO) {
    	service.create(postDTO);
    	return ResponseBuilder.buildMessage(HttpStatus.OK, "Post criado com sucesso.");
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO) {
    	service.update(id, postDTO);

    	return ResponseBuilder.buildMessage(HttpStatus.OK, "Post atualizado com sucesso.");
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
    	service.delete(id);
    	return ResponseBuilder.buildMessage(HttpStatus.OK, "Post deletado com sucesso.");
    }
 
}
