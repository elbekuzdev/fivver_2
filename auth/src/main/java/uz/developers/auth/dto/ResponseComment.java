package uz.developers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseComment {
    private Integer id;
    private String text;
    private ResponseUserDto from;
    private ResponseUserDto to;
    private Timestamp creationTime;
}
