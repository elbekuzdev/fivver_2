package uz.developers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDto {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Integer regionId;
}
