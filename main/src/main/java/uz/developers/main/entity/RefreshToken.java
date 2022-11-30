package uz.developers.main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @ManyToOne
    private Users user;
    private String refreshToken;
    private Date expireDate;

}
