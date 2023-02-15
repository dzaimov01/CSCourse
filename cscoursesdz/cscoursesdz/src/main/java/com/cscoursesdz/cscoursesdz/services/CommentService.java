package com.cscoursesdz.cscoursesdz.services;

import com.cscoursesdz.cscoursesdz.autentication.CustomUserDetails;
import com.cscoursesdz.cscoursesdz.dto.CommentDTO;
import com.cscoursesdz.cscoursesdz.exceptions.BadRequestException;
import com.cscoursesdz.cscoursesdz.models.BlogPost;
import com.cscoursesdz.cscoursesdz.models.Comment;
import com.cscoursesdz.cscoursesdz.models.User;
import com.cscoursesdz.cscoursesdz.repositories.BlogPostRepository;
import com.cscoursesdz.cscoursesdz.repositories.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogPostRepository blogPostRepository;

    public Optional<Comment> findForId(UUID id) {
        return commentRepository.findById(id);
    }

    public Optional<BlogPost> findPostForId(UUID id) {
        return blogPostRepository.findById(id);
    }

    public Optional<List<Comment>> findCommentsByPostId(UUID id) {
        return commentRepository.findByPostId(id);
    }

    public CommentDTO registerComment(CommentDTO commentDto, CustomUserDetails customUserDetails) {
        Optional<BlogPost> postForId = this.findPostForId(commentDto.getPostId());
        if (!postForId.isPresent()) {
            throw new BadRequestException("Post does not exist.");
        }

        Comment newComment = new Comment();
        newComment.setBody(commentDto.getBody());
        newComment.setPost(postForId.get());
        newComment.setUser(User.builder()
                        .id(customUserDetails.getId())
                        .userName(customUserDetails.getName())
                .build());
        commentRepository.saveAndFlush(newComment);

        return CommentDTO.builder()
                .id(newComment.getId())
                .userName(newComment.getUser().getUserName())
                .body(newComment.getBody())
                .postId(newComment.getPost().getId())
                .userId(newComment.getUser().getId())
                .createdDate(newComment.getCreatedDate())
                .lastModifiedDate(newComment.getLastModifiedDate()).build();
    }

    public CommentDTO editPost(CommentDTO editCommentDto) {
        Optional<Comment> comment = this.findForId(editCommentDto.getId())
                .map(commentInfo -> {
                    commentInfo.setBody(editCommentDto.getBody());
                    return commentInfo;
                });
        if(comment.isPresent()) {
            throw new BadRequestException("Not exist post.");
        }
        Comment commentData = comment.get();
        return CommentDTO.builder()
                .id(commentData.getId())
                .userName(commentData.getUser().getUserName())
                .body(commentData.getBody())
                .postId(commentData.getPost().getId())
                .userId(commentData.getUser().getId())
                .createdDate(commentData.getCreatedDate())
                .lastModifiedDate(commentData.getLastModifiedDate()).build();
    }

    public void deletePost(UUID id) {
        commentRepository.findById(id).ifPresent(commentRepository::delete);
    }
}
