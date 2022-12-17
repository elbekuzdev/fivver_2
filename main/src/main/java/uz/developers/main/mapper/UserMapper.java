package uz.developers.main.mapper;

import uz.developers.main.dto.ResponseUser;
import uz.developers.main.entity.Users;

public class UserMapper {
    public static ResponseUser toDto(Users user){

        return user != null ? new ResponseUser(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail()): null;
    }
}
