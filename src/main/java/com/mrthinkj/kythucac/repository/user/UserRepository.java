package com.mrthinkj.kythucac.repository.user;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.User;
import com.mrthinkj.kythucac.modelDTO.book.BookReadDTO;
import com.mrthinkj.kythucac.modelDTO.user.UserDTO;
import com.mrthinkj.kythucac.service.mapper.BookReadDTOMapper;
import com.mrthinkj.kythucac.service.mapper.UserDTOMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findAll();
    User findByAccount(Account account);
    User findById(int id);

    @Query(value = "select account.id, user_name, user_avatar, gender, user_address, user_description,(select count(*) from book_read where account_id = ?1),(select count(*) from bookshelf where account_id = ?1) from account join user on account.id = user.id  where account.id = ?1", nativeQuery = true)
    Object[] findUserById(int id);

    default UserDTO findUserByIdConvert(int id){
        Object[] result = findUserById(id);
        return UserDTOMapper.map(result);
    }
}
