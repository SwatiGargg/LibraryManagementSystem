package com.example.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsqlRepository<T,ID> extends CrudRepository<T, ID>, JpaSpecificationExecutor<T>{

}
