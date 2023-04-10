package com.fl.project.model.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectRes {
    private int projectId;
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
    private List<Skill> skills;

    public void setSkill(List<Skill> skills){
        this.skills = skills;
    }

}
