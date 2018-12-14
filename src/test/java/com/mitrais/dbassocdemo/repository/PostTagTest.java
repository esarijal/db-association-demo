package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.Post;
import com.mitrais.dbassocdemo.entity.Tag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostTagTest {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ITagRepository tagRepository;
    @Autowired
    private IPostTagRepository iPostTagRepository;

    @Before
    public void setUp() throws Exception {
        Post post = new Post("Post 1");
        post.setDescription("Description 1");
        post.setContent("Content Long very is here");
        postRepository.save(post);

        post = new Post("Post 2");
        post.setDescription("Description 2");
        post.setContent("Content Long very is here");
        postRepository.save(post);

        post = new Post("Post 3");
        post.setDescription("Description 3");
        post.setContent("Content Long very is here");
        postRepository.save(post);
    }

    @Test
    @Transactional
    public void testLifecycle(){
        List<Post> all = postRepository.findAll();
        Post post = all.get(0);

        post.addTag(new Tag("it"));
        post.addTag(new Tag("computer"));
        post.addTag(new Tag("engineering"));
        postRepository.save(post);

        List<Tag> tags = tagRepository.findAll();
        assertEquals(3, tags.size());

        Tag tag = tags.get(0);
        assertEquals(1, tag.getPosts().size());



    }
}
