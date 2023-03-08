package com.mrthinkj.kythucac.repository.user;

import com.mrthinkj.kythucac.model.user.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
