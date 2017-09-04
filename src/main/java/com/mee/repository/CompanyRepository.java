package com.mee.repository;

import com.mee.entity.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Optional<Company> findByEmail(String email);
    Optional<Company> findById(String id);
    List<Company> findByNameIsLike(String name, Pageable pageable);
}
