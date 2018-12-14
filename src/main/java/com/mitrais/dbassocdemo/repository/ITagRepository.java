package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.Post;
import com.mitrais.dbassocdemo.entity.Tag;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITagRepository extends IBaseRepository<Tag> {

//    List<Post> findAllPostByTag(Tag tag);
}
