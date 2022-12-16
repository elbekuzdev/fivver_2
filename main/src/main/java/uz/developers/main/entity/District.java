package uz.developers.main.entity;

import javax.persistence.*;
import java.util.HashMap;

@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    private Region region;
}
