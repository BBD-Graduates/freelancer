package com.fl.project.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ProjectRequest {
    private int clientId;
    @NotNull(message = "Please enter project name")
    private String projectName;
    @NotNull(message = "Please enter project description")
    @Size(min = 10,message = "Project Description Should be at least 10 characters")
    private String projectDescription;
    @NotNull(message = "Please select is confidential status")
    private Boolean isConfidential;
    private Date bidStartDate;
    private Date bidEndDate;
    @NotNull(message = "Please enter minimum price for your project")
    private Float minPrice;
    @NotNull(message = "Please enter maximum price for your project ")
    private Float maxPrice;
    private List<Integer> skillIds;

}
