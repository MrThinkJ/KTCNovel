package com.mrthinkj.kythucac.modelDTO.user;

import com.mrthinkj.kythucac.model.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String avatar;
    private String gender;
    private String address;
    private String description;
    private Integer numberInBookShelf;
    private Integer numberBookRead;
}
