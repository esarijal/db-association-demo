package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.Post;
import com.mitrais.dbassocdemo.entity.PostDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostTests {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IPostDetailsRepository IPostDetailsRepository;

    private Post post;

    @Before
    public void setUp() throws Exception {
        post = new Post("Post 1");
        post.setDescription("Description 1");
        post.setContent("Content Long very is here");

        postRepository.save(post);
    }


    @Test
    public void testPostFindOne_Basic(){
        Post one = postRepository.findOne(post.getId());
        assertEquals(post.getId(), one.getId());
        assertNull(post.getDetails());
    }

    @Test
    public void testPostSaveWithDetails_Basic(){
        long count = IPostDetailsRepository.count();
        // assert details counter before save
        assertEquals(0, count);

        post.setDetails(new PostDetails("Metainfo 1"));
        postRepository.save(post);
        count = IPostDetailsRepository.count();
        // assert details counter after save
        assertEquals(1, count);
    }

    @Test
    public void testPostSaveWithDetails_nullValues(){
        post.setDetails(null);
        postRepository.save(post);
        long count = IPostDetailsRepository.count();
        // assert details counter after save
        assertEquals(0, count);
    }

    @Test
    public void testOrphanRemovalPostDetails_Basic(){
        // PREPS
        Post one = postRepository.findOne(post.getId());
        PostDetails details = new PostDetails("Metainfo 1");
        one.setDetails(details);
        postRepository.save(one);

        long count = IPostDetailsRepository.count();
        assertEquals(1, count);

        // ACTION
        postRepository.deleteById(one.getId());

        // ASSERTION
        count = IPostDetailsRepository.count();
        assertEquals(0, count);
    }

    @Test//(expected = DataIntegrityViolationException.class) // use this expectation if using
    // @MapsId
    public void testCannotChangeDetails_ThrownException(){

        post.setDetails(new PostDetails("Metainfo 1"));
        postRepository.save(post);
        Post one = postRepository.findOne(post.getId());
        assertEquals("Metainfo 1", one.getDetails().getMetainfo());

        post.setDetails(new PostDetails("Metainfo 2"));
        postRepository.save(post);
        one = postRepository.findOne(post.getId());
        assertEquals("Metainfo 2", one.getDetails().getMetainfo());
    }

    @Test
    public void testChangeDetailsInfo_Basic(){
        PostDetails details = new PostDetails("Metainfo 1");
        details.setMetainfo("Metainfo 1");

        post.setDetails(details);
        postRepository.save(post);

        post.getDetails().setMetainfo("Metainfo 2");
        postRepository.save(post);

        PostDetails details1 = IPostDetailsRepository.findByPostId(post.getId());
        assertEquals("Metainfo 2", details1.getMetainfo());
    }


}
