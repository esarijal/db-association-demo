package com.mitrais.dbassocdemo.repository;

import com.mitrais.dbassocdemo.entity.BaseAuditModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBaseRepository<T extends BaseAuditModel> extends JpaRepository<T, Long> {
}
