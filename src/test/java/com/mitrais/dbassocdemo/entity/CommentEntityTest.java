package com.mitrais.dbassocdemo.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CommentEntityTest {

    @Test
    public void testEqualsValue_Basic(){
        Post post = new Post("Post 1");

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setReview("Ini Review");
        comment.setPost(post);

        Comment comment2 = new Comment();
        comment2.setId(1L);
        comment2.setReview("Ini Review");
        comment2.setPost(post);

        assertEquals(comment, comment2);
    }

    @Test
    public void testNotEqualsValue_Basic(){
        Post post = new Post("Post 1");

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setReview("Ini Review");
        comment.setPost(post);

        Comment comment2 = new Comment();
        comment2.setId(1L);
        comment2.setReview("Ini Review 2");
        comment2.setPost(post);

        assertNotEquals(comment, comment2);
    }
}
