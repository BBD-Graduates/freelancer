package com.fl.skill.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @NotNull(message = "Please enter skill")
    @Size(min=1, message = "Name Should not be blank")

    private String skillName;
    @NotNull(message = "Please enter category")
    private int categoryId;

}
