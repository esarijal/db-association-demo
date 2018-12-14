package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.PostDetails;
import org.springframework.data.jpa.repository.Query;

public interface IPostDetailsRepository extends IBaseRepository<PostDetails> {
    @Query("select d from PostDetails d where d.post.id = ?1")
    PostDetails findByPostId(Long postId);
}
