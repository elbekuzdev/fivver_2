package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HiringResponse {
    private int id;
    private String title;
    private String description;
    private String state;
    private Double startPrice;
    private Date creationTime;
    private Set<HashTagDto> tags;

    public HiringResponse(int id, String title, String description, Double startPrice, String state, Date creationTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startPrice=startPrice;
        this.state = state;
        this.creationTime = creationTime;
    }
}
