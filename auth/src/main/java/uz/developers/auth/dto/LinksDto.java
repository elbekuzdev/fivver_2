package uz.developers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinksDto {
    private Integer id;
    private String name;
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        LinksDto linksDto = (LinksDto) o;
        return Objects.equals(name.toLowerCase(), linksDto.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}
