package com.mitrais.dbassocdemo.service;

import com.mitrais.dbassocdemo.entity.Comment;
import com.mitrais.dbassocdemo.entity.Post;
import com.mitrais.dbassocdemo.entity.PostDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    boolean savePost(Post post);
    boolean deletePost(Long postId);
    Optional<Post> findPostById(Long postId);
    List<Post> findAllPost();
    void addDetailToPost(Post post, PostDetails details);

    boolean addCommentInAPost(Comment comment, Post post);
    boolean removeCommentInAPost(Comment comment, Post post);
    List<Comment> fetchAllCommentsInAPost(Post post);
    Page<Comment> fetchAllCommentsInAPost(Post post, Pageable pageable);
}
