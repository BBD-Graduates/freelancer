package com.fl.project.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ProjectRequest {
    private int clientId;
    @NotNull(message = "Please enter project name")
    private String projectName;
    @NotNull(message = "Please enter project description")
    @Size(min = 10,message = "Project Description Should be atleast 10 characters")
    private String projectDescription;
    @NotNull(message = "Please select isconfidential status")
    private Boolean isConfidential;
    @NotNull(message = "Please select payment method")
    private int paymentTypeId;
   //@NotNull(message = "Please select bid start date ")
    private Date bidStartDate;
   // @NotNull(message = "Please select bid end date")
    private Date bidEndDate;
    @NotNull(message = "Please enter minimum price for your project")
    private Float minPrice;
    @NotNull(message = "Please enter maximum price for your project ")
    private Float maxPrice;

}
