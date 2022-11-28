package uz.developers.auth.dto;

import javax.validation.constraints.NotNull;


public class SkillDto {
    private Integer id;
    @NotNull
    private String name;
}
