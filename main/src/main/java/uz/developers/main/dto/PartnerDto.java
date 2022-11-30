package uz.developers.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDto {
    private Integer id;
    @NotNull
    private String profession;
    @NotNull
    private byte count = 1;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PartnerDto)) {
            return false;
        }
        return profession.toLowerCase().equals(((PartnerDto)other).profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profession.toLowerCase());
    }

}
