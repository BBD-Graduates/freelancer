package com.fl.skill.model.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
//import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
public class Category {

    @NotNull(message = "Please enter Skill")
    @Size(min=5, message = "Name should be atleast 5 characters")
    @NotBlank(message = "Please enter Skill")
    private String name;
}
