package com.mrthinkj.kythucac.repository.user;

import com.mrthinkj.kythucac.model.user.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);
}
