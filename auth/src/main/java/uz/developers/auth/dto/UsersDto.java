package uz.developers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private Integer Id;
    @NotBlank(message = "FirstName is mandatory")
    private String firstname;
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @NotBlank(message = "Profession is mandatory")
    private String profession;
    private String summary;
    @Length(min = 8)
    private String password;
    private RegionDto region;
    private DistrictDto district;
    private String phoneNumber;
    @Email(message = "Email is not valid")
    private String email;
    private Set<LinksDto> links;
    private ImageDto profilePicture;
    private Boolean isAuthorized;
    private Boolean isActive;


}
