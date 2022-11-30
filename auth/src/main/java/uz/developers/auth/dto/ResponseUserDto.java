package uz.developers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String profession;
    private String summary;
    private String region;
    private String district;
    private String phoneNumber;
    private String email;
    private Set<LinksDto> links;
}
