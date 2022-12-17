package uz.developers.main.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.developers.main.entity.State;
import uz.developers.main.entity.Users;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HiringPartnerDto {
    private Integer id;
    @NotNull
    private String title;
    @NotNull
    private String  description;
    private State state = State.WAITING;
    private ResponseUser user;
    @NotNull
    private Double startPrice = 0.0;
    private Double price;
    private Set<PartnerDto> partners;
    private Set<HashTagDto> tags;
    private Timestamp creationTime;
    private boolean isActive = true;
}
