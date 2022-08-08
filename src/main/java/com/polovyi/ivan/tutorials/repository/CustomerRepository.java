package com.polovyi.ivan.tutorials.repository;

import com.polovyi.ivan.tutorials.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {


}
