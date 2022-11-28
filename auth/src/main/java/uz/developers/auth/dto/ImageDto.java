package uz.developers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private int id;

    private String name;
    private String type;
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;
}
