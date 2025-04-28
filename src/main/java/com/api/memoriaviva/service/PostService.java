package com.api.memoriaviva.service;

import com.api.memoriaviva.dto.PostDTO;
import com.api.memoriaviva.entity.Post;
import com.api.memoriaviva.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    @Autowired
    PostRepository repository;
    /**
     * Este método seta a variável sensitiveContent como false se for diferente de um true explícito
     * @param postDTO
     */
    
    public void verifySensitiveContent(PostDTO postDTO) {
    	if(!Objects.equals(postDTO.isSensitiveContent(), true)) {
    		 postDTO.setSensitiveContent(false);;
    	}
    } 
    

    /**
     * Método para listar todas as postagens.
     * @return lista de dtos.
     */
    public List<PostDTO> list() {
        return repository.findAll().stream()
                .map(PostDTO::new)
                .toList();
    }

    /**
     * Método para criar uma nova postagem.
     * @param postDTO
     * @return
     */
    public void create(PostDTO postDTO) {
    	verifySensitiveContent(postDTO);
		postDTO.setDatePost(LocalDate.now());
		postDTO.setHourPost(LocalTime.now());
    	Post post = new Post(postDTO);
//    	if(!Objects.equals(postDTO.isSensitiveContent(), true)) {
//    		post.setSensitiveContent(false);
//    	}

    	repository.save(post);   	
    }
    
    public void update(Long id, PostDTO postDTO) {
    	Post post = repository.findById(id).orElseThrow(() -> 
    		new IllegalArgumentException("Postagem não encontrada para atualização."));
		verifySensitiveContent(postDTO);
    	post.setTitle(postDTO.getTitle());
    	post.setDescription(postDTO.getDescription());
    	post.setCategory(postDTO.getCategory());
    	post.setSensitiveContent(postDTO.isSensitiveContent());;
    	post.setImgUrl(postDTO.getImgUrl());
		repository.save(post);
    }
    
    public void delete(Long id) {
    	Post post = repository.findById(id).orElseThrow(() -> 
			new IllegalArgumentException("Postagem não encontrada para exclusão."));
    	repository.delete(post);
    }
    
    
}
