package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IDAO<T> extends JpaRepository<T, String>,JpaSpecificationExecutor<T>{
	
}
