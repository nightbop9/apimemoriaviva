package com.api.memoriaviva.dto;

import com.api.memoriaviva.entity.Post;
import com.api.memoriaviva.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Esta é a classe DTO (Data Transfer Object) que representa os dados de uma postagem, separando camadas de
 * responsabilidade.
 */

public class PostDTO {
    private Long id;
    @NotBlank(message = "O título é obrigatório.")
    @Size(min = 5, max = 30, message = "O título deve ter entre 5 e 30 caracteres.")
    private String title;
    @NotBlank(message = "A descrição é obrigatória.")
    @Size(min = 20, max = 200, message = "A descrição deve ter entre 20 e 200 caracteres.")
    private String description;
    @NotNull(message = "Escolha uma categoria.")
    private Category category;
    private boolean sensitiveContent;
    private String imgUrl;
    private LocalDate datePost;
    private LocalTime hourPost;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String description, Category category, boolean sensiveContent, String imgUrl, LocalDate datePost, LocalTime hourPost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.sensitiveContent = sensiveContent;
        this.imgUrl = imgUrl;
        this.datePost = datePost;
        this.hourPost = hourPost;
    }

    public PostDTO(Post post) {
        id = post.getId();
        title = post.getTitle();
        description = post.getDescription();
        category = post.getCategory();
        sensitiveContent = post.isSensitiveContent();
        imgUrl = post.getImgUrl();
        datePost = post.getDatePost();
        hourPost = post.getHourPost();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isSensitiveContent() {
        return sensitiveContent;
    }

    public void setSensitiveContent(boolean sensitiveContent) {
        this.sensitiveContent = sensitiveContent;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDate getDatePost() {
        return datePost;
    }

    public void setDatePost(LocalDate datePost) {
        this.datePost = datePost;
    }

    public LocalTime getHourPost() {
        return hourPost;
    }

    public void setHourPost(LocalTime hourPost) {
        this.hourPost = hourPost;
    }
}
