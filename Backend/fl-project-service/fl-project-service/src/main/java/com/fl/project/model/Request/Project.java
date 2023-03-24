package com.fl.project.model.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Project {
    private int clientId;
    private String projectName;
    private String projectDescription;
    private Boolean isConfidential;
    private int paymentTypeId;
    private Date bidStartDate;
    private Date bidEndDate;
    private Float minPrice;
    private Float maxPrice;
}
