package com.api.memoriaviva.entity;

import com.api.memoriaviva.dto.PostDTO;
import com.api.memoriaviva.enums.Category;
import com.api.memoriaviva.enums.Emoji;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Esta é a entidade gerenciada pelo JPA que representa a tabela de postagens no banco de dados.
 */

@Entity //Indica que classe representa uma entidade
@Table(name = "posts") //Define o nome da tabela no banco de dados
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 25)
    private String title;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Category category;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Emoji emoji;
    @Column(nullable = false)
    private boolean sensitiveContent;
    private String imgUrl;
    private LocalDate datePost;
    private LocalTime hourModifiedPost;

    //Construtor padrão
    public Post() {
    }

    //Construtor padrão
    public Post(String title, String description, Category category, Emoji emoji, boolean sensitiveContent,
                String imgUrl, LocalDate datePost, LocalTime hourModifiedPost) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.emoji = emoji;
        this.sensitiveContent = sensitiveContent;
        this.imgUrl = imgUrl;
        this.datePost = datePost;
        this.hourModifiedPost = hourModifiedPost;
    }

    //Construtor que recebe um objeto PostDTO
    public Post(PostDTO postDTO) {
        title = postDTO.getTitle();
        description = postDTO.getDescription();
        category = postDTO.getCategory();
        emoji = postDTO.getEmoji();
        sensitiveContent = postDTO.isSensitiveContent();
        imgUrl = postDTO.getImgUrl();
        datePost = postDTO.getDatePost();
        hourModifiedPost = postDTO.getHourModifiedPost();
    }

    /**
     * Encapsulamento
     * A partir daqui os atributos privados são acessados soemente por métodos públicos.
     */
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

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    public boolean isSensitiveContent() {
        return sensitiveContent;
    }

    public void setSensitiveContent(boolean sensiveContent) {
        this.sensitiveContent = sensiveContent;
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

    public LocalTime getHourModifiedPost() {
        return hourModifiedPost;
    }

    public void setHourModifiedPost(LocalTime hourModifiedPost) {
        this.hourModifiedPost = hourModifiedPost;
    }
}
