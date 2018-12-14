package com.mitrais.dbassocdemo.service;

import com.mitrais.dbassocdemo.entity.Comment;
import com.mitrais.dbassocdemo.entity.Post;
import com.mitrais.dbassocdemo.entity.PostDetails;
import com.mitrais.dbassocdemo.repository.ICommentRepository;
import com.mitrais.dbassocdemo.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    private IPostRepository postRepository;
    private ICommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Autowired
    public void setPostRepository(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public boolean savePost(Post post) {
        try{
            postRepository.save(post);
        } catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public boolean deletePost(Long postId) {
        try{
            postRepository.deleteById(postId);
        } catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Optional<Post> findPostById(Long postId) {
        return Optional.of(postRepository.findOne(postId));
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public void addDetailToPost(Post post, PostDetails details) {
        post.setDetails(details);
        savePost(post);
    }

    @Override
    @Transactional
    public boolean addCommentInAPost(Comment comment, Post post) {
        if(comment == null) {
            return false;
        }

        comment.setPost(post);
        try{
            commentRepository.save(comment);
        } catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public boolean removeCommentInAPost(Comment comment, Post post) {
        if(comment == null) {
            return false;
        }

        try{
            commentRepository.delete(comment);
        } catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<Comment> fetchAllCommentsInAPost(Post post) {
        return commentRepository.findAllByPostId(post.getId());
    }

    @Override
    public Page<Comment> fetchAllCommentsInAPost(Post post, Pageable pageable) {
        return commentRepository.findAllByPostId(post.getId(), pageable);
    }


}
