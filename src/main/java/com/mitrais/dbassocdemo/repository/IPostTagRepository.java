package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostTagRepository extends JpaRepository<PostTag, Long> {
}
