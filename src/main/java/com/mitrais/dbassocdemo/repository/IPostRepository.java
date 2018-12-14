package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.Post;
import org.springframework.data.jpa.repository.Query;

public interface IPostRepository extends IBaseRepository<Post> {

    @Query("select p from Post p where p.id = ?1")
    Post findOne(Long id);
}
