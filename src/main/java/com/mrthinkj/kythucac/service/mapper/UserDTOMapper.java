package com.mrthinkj.kythucac.service.mapper;

import com.mrthinkj.kythucac.model.user.Gender;
import com.mrthinkj.kythucac.modelDTO.book.BookReadDTO;
import com.mrthinkj.kythucac.modelDTO.user.UserDTO;
import com.mrthinkj.kythucac.service.book.ChapterService;
import com.mrthinkj.kythucac.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDTOMapper {
    @Autowired
    UserService userService;

    public static UserDTO map(Object[] rows) {
        Object[] row = (Object[]) rows[0];
        Integer id = (Integer) row[0];
        String username = (String) row[1];
        String avatar = (String) row[2];
        String gender = Gender.getGenderFromInt((Integer) row[3]);
        String address = (String) row[4];
        String description = (String) row[5];
        Integer booksRead = ((BigInteger) row[6]).intValue();
        Integer booksSaved = ((BigInteger) row[7]).intValue();
        UserDTO userDTO = new UserDTO(id, username, avatar, gender, address, description, booksRead, booksSaved);
        return userDTO;
    }

}
