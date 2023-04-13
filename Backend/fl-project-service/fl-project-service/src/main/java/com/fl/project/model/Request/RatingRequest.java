package com.fl.project.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {

    @NotNull(message = "Please enter userId")
    private int userId;

    @NotNull(message = "Please enter projectId")
    private int projectId;
    
    @NotNull(message = "Please enter ratingDescription")
    @Size(min=1, message = "Name should be atleast 5 characters")
    @NotBlank(message = "Please enter ratingDescription")
    private String ratingDescription;

    @Min(value = 0)
    @Max(value = 5, message = "Value must between 0-5")
    private int rating;
}
