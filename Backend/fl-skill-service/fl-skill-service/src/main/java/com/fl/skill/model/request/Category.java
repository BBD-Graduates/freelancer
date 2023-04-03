package com.fl.skill.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
public class Category {

    @NotNull(message = "Please enter Skill")
    @Size(min=1, message = "Name should be atleast 5 characters")
    @NotBlank(message = "Please enter Skill")
    private String name;
}
