package com.cscoursesdz.cscoursesdz.repositories;

import com.cscoursesdz.cscoursesdz.models.BlogPost;
import com.cscoursesdz.cscoursesdz.models.User;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {
    Page<BlogPost> findByUserOrderByCreatedDateDesc(User user, Pageable pageable);

    default Page<BlogPost> findAllByOrderByCreatedDateDesc(Pageable pageable) {
        return null;
    }

    @NonNull Optional<BlogPost> findById(@NonNull UUID id);

    void delete(@NonNull BlogPost post);
}
