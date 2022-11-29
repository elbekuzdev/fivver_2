package uz.developers.auth.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

    public String encodePassword(String password) { // TODO security qo'shilganda buni o'zgaritib qoyiladi, yani, encodelash uchun
        return password;
    }

    public Boolean match(String newPassword, String oldPassword) { // TODO bu ham shunday
        return newPassword.equals(oldPassword);
    }
}
