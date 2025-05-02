package com.api.memoriaviva.service;

import com.api.memoriaviva.dto.PostDTO;
import com.api.memoriaviva.entity.Post;
import com.api.memoriaviva.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    @Autowired
    PostRepository repository;

    /**
     * Método para verificar se o conteúdo é sensível.
     * seta a variável sensitiveContent como false se for diferente de um true explícito
     *
     * @param postDTO
     */
    private void verifySensitiveContent(PostDTO postDTO) {
        if (!Objects.equals(postDTO.isSensitiveContent(), true)) {
            postDTO.setSensitiveContent(false);
        }
    }

    /**
     * Método para remover nanosegundos da hora.
     *
     * @param hourPost a ser formatada
     * @return hora formatada
     */
    private LocalTime formatterHour(LocalTime hourPost) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(hourPost.format(formatter));
    }

    @Autowired
    ImageService imageService;

    //Todos os métodos daqui em diante são HTTP demandados a api

    /**
     * Método para listar todas as postagens.
     *
     * @return lista das postagens.
     */
    public List<PostDTO> list() {
        return repository.findAll().stream()
                .map(PostDTO::new)
                .toList();
    }

    /**
     * Método para criar uma nova postagem.
     *
     * @param postDTO com os dados da postagem a ser criada.
     * @param file    arquivo de imagem
     */
    @Transactional
    public void create(PostDTO postDTO, MultipartFile file) {
        verifySensitiveContent(postDTO);
        postDTO.setDatePost(LocalDate.now());
        postDTO.setHourModifiedPost(formatterHour(LocalTime.now()));
        // Chama o serviço para fazer upload da imagem e obter a URL
        if (file != null && !file.isEmpty()) {
            postDTO.setImgUrl(imageService.uploadImage(file));
        } else {
            postDTO.setImgUrl(null);
        }
        repository.save(new Post(postDTO));
    }

    /**
     * Método para atualizar uma postagem.
     *
     * @param id      da entidade a ser atualizada
     * @param postDTO com os novos dados da entidade
     * @param file    arquivo de imagem
     */
    @Transactional
    public void update(Long id, PostDTO postDTO, MultipartFile file) {
        Post post = repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Postagem não encontrada para atualização."));
        verifySensitiveContent(postDTO);
        post.setTitle(postDTO.getTitle().strip());
        post.setDescription(postDTO.getDescription());
        post.setCategory(postDTO.getCategory());
        post.setEmoji(postDTO.getEmoji());
        post.setSensitiveContent(postDTO.isSensitiveContent());
        post.setDatePost(LocalDate.now());
        post.setHourModifiedPost(formatterHour(LocalTime.now()));
        String imgUrl = post.getImgUrl();
        if (file != null && !file.isEmpty()) {
            if (imgUrl == null || imgUrl.isBlank()) {
                post.setImgUrl(imageService.uploadImage(file));
            } else {
                post.setImgUrl(imageService.updateImage(imgUrl, file));
            }
        } else {
            if (imgUrl != null) {
                imageService.deleteImage(imgUrl);
            }
            post.setImgUrl(null);
        }
    }

    /**
     * Método para excluir uma postagem.
     *
     * @param id da entidade a ser excluída.
     */
    @Transactional
    public void delete(Long id) {
        Post post = repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Postagem não encontrada para exclusão."));
        if (post.getImgUrl() != null) {
            imageService.deleteImage(post.getImgUrl());
        }
        repository.delete(post);
    }
}
