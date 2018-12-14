package com.mitrais.dbassocdemo.service;

import com.mitrais.dbassocdemo.entity.Comment;
import com.mitrais.dbassocdemo.entity.Post;
import com.mitrais.dbassocdemo.entity.PostDetails;
import com.mitrais.dbassocdemo.repository.ICommentRepository;
import com.mitrais.dbassocdemo.repository.IPostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTests {
    @InjectMocks
    private PostService postService;
    @Mock
    private IPostRepository postRepository;
    @Mock
    private ICommentRepository commentRepository;

    @Mock
    private Post post;

    @Before
    public void setUp() {
        post = new Post("Post 1");
        post.setDescription("Description 1");
        post.setContent("Content Long very is here");
    }

    @Test
    public void testSavePost_Basic(){
        when(postRepository.save(any())).thenReturn(post);
        assertTrue(postService.savePost(post));
    }

    @Test
    public void testSavePost_ThrownException(){
        when(postRepository.save(any())).thenThrow(new DataIntegrityViolationException("Data is " +
                "exists"));
        assertFalse(postService.savePost(post));
    }

    @Test
    public void testDeletePost_Basic(){
        post.setId(1L);
        doNothing().when(postRepository).deleteById(post.getId());

        assertTrue(postService.deletePost(post.getId()));

        verify(postRepository).deleteById(post.getId());
    }

    @Test
    public void testDeletePost_ThrownException(){
        post.setId(1L);
        doThrow(new DataIntegrityViolationException("Not Found"))
                .when(postRepository).deleteById(anyLong());

        assertFalse(postService.deletePost(post.getId()));

        verify(postRepository).deleteById(anyLong());
    }

    @Test
    public void testAddDetailsToPost(){
        PostDetails details = new PostDetails("Metainfo1");
        postService.addDetailToPost(post, details);

        verify(postRepository).save(post);
        assertEquals(details, post.getDetails());
    }

    @Test
    public void testAddDetailsToPost_nullValues(){
        postService.addDetailToPost(post, null);

        verify(postRepository).save(post);
    }

    @Test
    public void testAddCommentInAPost(){
        Comment comment = new Comment("Comment 1");
        when(commentRepository.save(any())).thenReturn(comment);

        boolean result = postService.addCommentInAPost(comment, post);
        assertTrue(result);
        assertEquals(post, comment.getPost());
    }

    @Test
    public void testAddCommentInAPost_NullComment(){
        boolean result = postService.addCommentInAPost(null, post);
        assertFalse(result);
        verify(commentRepository, never()).save(any());
    }

    @Test
    public void testRemoveCommentInAPost(){
        Comment comment = new Comment("Comment 1");
        doNothing().when(commentRepository).delete(any());

        boolean result = postService.removeCommentInAPost(comment, post);
        assertTrue(result);
    }

    @Test
    public void testRemoveCommentInAPost_nullValue(){
        boolean result = postService.removeCommentInAPost(null, post);
        assertFalse(result);
        verify(commentRepository, never()).delete(any());
    }

}
