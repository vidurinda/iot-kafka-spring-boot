package com.ices.iotvisualizer.repository;


import com.ices.iotvisualizer.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends MongoRepository<AppUser, UUID> {

    AppUser findByUsername(@Param("username") String username);
}
