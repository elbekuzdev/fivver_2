package uz.developers.main.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HiringPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String  description;
    @Enumerated(EnumType.STRING)
    private State state = State.WAITING;
    @ManyToOne
    private Users user;
    private Double StartPrice = 0.0;
    private Double price;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE, CascadeType.PERSIST }, orphanRemoval = true)
    private Set<Partner> partners;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE, CascadeType.PERSIST }, orphanRemoval = true)
    private Set<Hashtag> tags;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp creationTime;
    private Boolean isActive = true;
}
