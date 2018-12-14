package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBaseRepository<T extends BaseModel> extends JpaRepository<T, Long> {
}
