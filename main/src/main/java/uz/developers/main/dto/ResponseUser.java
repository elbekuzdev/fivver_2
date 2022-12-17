package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
}
