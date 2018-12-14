package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.Comment;
import com.mitrais.dbassocdemo.entity.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepoTest {

    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IPostRepository postRepository;

    private Long postId;
    private Long postId1;

    @Before
    public void setUp() throws Exception {

        Post post = postRepository.save(new Post("Post 1"));
        postId = post.getId();
        Post post1 = postRepository.save(new Post("Post 2"));
        postId1 = post1.getId();

        List<Comment> comments = Stream.of(
                new Comment("Review 1"),
                new Comment("Review 2"),
                new Comment("Review 3"))
                .peek(comment -> comment.setPost(post))
                .collect(Collectors.toList());

        commentRepository.saveAll(comments);

        List<Comment> comments1 = Stream.of(
                new Comment("Review 4"),
                new Comment("Review 5"))
                .peek(comment -> comment.setPost(post1))
                .collect(Collectors.toList());

        commentRepository.saveAll(comments1);
    }

    @Test
    public void whenFindByPostId_thenReturnAllCommentsRelated(){
        List<Comment> firstByPostId = commentRepository.findAllByPostId(postId);
        assertEquals(3, firstByPostId.size());

        List<Comment> firstByPostId1 = commentRepository.findAllByPostId(postId1);
        assertEquals(2, firstByPostId1.size());
    }

    @Test
    @Transactional
    public void testDeleteComment(){

        List<Comment> comments = commentRepository.findAllByPostId(postId);
        int size = comments.size();
        assertEquals(3, size);

        commentRepository.delete(comments.get(0));

        int now = commentRepository.countAllByPostId(postId);
        assertEquals(2, now);

    }

    @Test
    public void testDeletePost(){
        postRepository.deleteById(postId);

        assertEquals(0, commentRepository.countAllByPostId(postId));
    }

}
