package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.developers.main.entity.State;
import uz.developers.main.entity.Users;

import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHiringDto {
    private Integer id;
    private String title;
    private String  description;
    private State state;
    private Users user;
    private Double startPrice;
    private Double price;
    private Set<HashTagDto> tags;
    private Timestamp creationTime;
}
