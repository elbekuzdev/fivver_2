package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.developers.main.entity.State;
import uz.developers.main.entity.Users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HiringDto {
    private Integer id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Description is mandatory")
    private String  description;
    private StateDto stateDto = StateDto.WAITING;
    private Users user;
    @NotNull(message = "Start price is mandatory")
    private Double startPrice = 0.0;
    private Double price;
    private Set<HashTagDto> tags;
    private Timestamp creationTime;
    private boolean isActive = true;
}
