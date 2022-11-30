package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashTagDto {
    private int id;
    private String name;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof HashTagDto)) {
            return false;
        }
        return name.toLowerCase().equals(((HashTagDto)other).name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}
