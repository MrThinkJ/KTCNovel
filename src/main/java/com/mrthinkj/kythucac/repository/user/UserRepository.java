package com.mrthinkj.kythucac.repository.user;

import com.mrthinkj.kythucac.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
