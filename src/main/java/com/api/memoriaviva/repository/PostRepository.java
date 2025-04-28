package com.api.memoriaviva.repository;

import com.api.memoriaviva.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta é a interface do repositório que estende JpaRepository, permitindo operações CRUD para a entidade Post.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
