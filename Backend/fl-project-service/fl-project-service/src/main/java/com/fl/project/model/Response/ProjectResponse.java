package com.fl.project.model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRes {
    private Integer projectId;
    private int clientId;
    private String projectName;
    private String projectDescription;
    private Boolean isConfidential;
    private int paymentTypeId;
    private Date bidStartDate;
    private Date bidEndDate;
    private Float minPrice;
    private Float maxPrice;
    private Date createdDate;
    private String status;
    @Builder.Default
    List<Skill> skills = new ArrayList<>();

}
