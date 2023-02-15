package com.cscoursesdz.cscoursesdz.services;

import com.cscoursesdz.cscoursesdz.autentication.CustomUserDetails;
import com.cscoursesdz.cscoursesdz.common.SecurityUtil;
import com.cscoursesdz.cscoursesdz.dto.BlogPostDTO;
import com.cscoursesdz.cscoursesdz.exceptions.BadRequestException;
import com.cscoursesdz.cscoursesdz.models.BlogPost;
import com.cscoursesdz.cscoursesdz.models.User;
import com.cscoursesdz.cscoursesdz.repositories.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;

    public Optional<BlogPost> findForId(UUID id) {
        return blogPostRepository.findById(id);
    }

    public BlogPostDTO registerPost(BlogPostDTO postDto, CustomUserDetails customUserDetails) {
        BlogPost newPost = new BlogPost();
        newPost.setTitle(postDto.getTitle());
        newPost.setBody(postDto.getBody());
        newPost.setCreatedBy(customUserDetails.getName());
        newPost.setCreatedDate(LocalDateTime.now());
        newPost.setUser(User.builder()
                .id(customUserDetails.getId())
                .userName(customUserDetails.getName())
                .build()); // temporary code

        blogPostRepository.saveAndFlush(newPost);

        return BlogPostDTO.builder()
                .id(newPost.getId())
                .title(newPost.getTitle())
                .body(newPost.getBody())
                .userId(newPost.getUser().getId())
                .userName(newPost.getUser().getUserName())
                .createdBy(newPost.getCreatedBy())
                .createdDate(newPost.getCreatedDate())
                .lastModifiedBy(newPost.getLastModifiedBy())
                .lastModifiedDate(newPost.getLastModifiedDate())
                .build();
    }

    public Optional<BlogPost> editPost(BlogPostDTO editPostDto) {
        return this.findForId(editPostDto.getId())
                .map(p -> {
                    if (p.getUser().getId() != SecurityUtil.getCurrentUserLogin().get().getId()) {
                        throw new BadRequestException("It's not a writer.");
                    }
                    p.setTitle(editPostDto.getTitle());
                    p.setBody(editPostDto.getBody());
                    return p;
                });
    }

    public Page<BlogPost> findByUserOrderedByCreatedDatePageable(User user, Pageable pageable) {
        return blogPostRepository.findByUserOrderByCreatedDateDesc(user, pageable);
    }

    public Page<BlogPost> findAllByOrderByCreatedDateDescPageable(Pageable pageable) {
        return blogPostRepository.findAllByOrderByCreatedDateDesc(pageable);
    }

    public void deletePost(UUID id) {
        blogPostRepository.findById(id).ifPresent(p -> {
            if (p.getUser().getId() != SecurityUtil.getCurrentUserLogin().get().getId()) {
                throw new BadRequestException("It's not a writer.");
            }
            blogPostRepository.delete(p);
        });
    }
}
