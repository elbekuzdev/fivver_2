package uz.developers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUsersDto {
    private Integer Id;
    @NotBlank(message = "FirstName is mandatory")
    private String firstname;
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @NotBlank(message = "Profession is mandatory")
    private String profession;
    private String summary;
    @Length(min = 8)
    @NotNull
    private String password;
    @NotNull
    private Integer regionId;
    @NotNull
    private Integer districtId;
    private String phoneNumber;
    @Email(message = "Email is not valid")
    @NotNull
    private String email;
    private Set<LinksDto> links;
    private ImageDto profilePicture;
    private Boolean isAuthorized = false;
    private Boolean isActive = true;


}
