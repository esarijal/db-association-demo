package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICommentRepository extends IBaseRepository<Comment> {
    List<Comment> findAllByPostId(Long postId);
    Page<Comment> findAllByPostId(Long postId, Pageable pageable);
    int countAllByPostId(Long postId);
}
