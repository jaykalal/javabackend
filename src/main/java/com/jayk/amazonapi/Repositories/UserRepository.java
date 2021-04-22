package com.jayk.amazonapi.Repositories;

import com.jayk.amazonapi.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    User findByemail(String email);
}
