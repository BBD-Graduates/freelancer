package com.fl.user.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "Please enter firstName")
    @NotBlank(message = "Please enter firstName")
    private String firstName;
    @NotNull(message = "Please enter lastName")
    @NotBlank(message = "Please enter lastName")
    private String lastName;
    private String headLine;
    private String summary;
    private String company;
    @NotNull(message = "Please enter email")
    @NotBlank(message = "Please enter email")
    private String email;
    private String phNo;
    @NotNull(message = "Please enter photoUrl")
    @NotBlank(message = "Please enter photoUrl")
    private String photoUrl;
}
