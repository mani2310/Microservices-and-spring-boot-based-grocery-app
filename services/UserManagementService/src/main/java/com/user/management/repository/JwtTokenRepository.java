package com.user.management.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.user.management.model.JwtToken;

@Repository
public interface JwtTokenRepository extends MongoRepository<JwtToken,String> {
}
