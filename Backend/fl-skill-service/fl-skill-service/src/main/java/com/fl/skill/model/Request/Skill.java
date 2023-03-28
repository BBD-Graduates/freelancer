package com.fl.skill.model.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Skill {

    @NotNull(message = "Please enter skill")
    @Size(min=1, message = "Name Should not be blank")

    private String skillName;
    @NotNull(message = "Please enter category")
    private int categoryId;

}
