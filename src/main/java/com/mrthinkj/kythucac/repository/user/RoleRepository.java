package com.mrthinkj.kythucac.repository.user;

import com.mrthinkj.kythucac.model.user.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role getRoleByName(String name);
}
