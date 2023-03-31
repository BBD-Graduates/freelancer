package com.fl.skill.model.response;

import jakarta.ws.rs.DefaultValue;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorySkills {

    private int categoryId;
    private String categoryName;
    @DefaultValue("")
    private String logoURl;
    private boolean IsDeleted;
    private String CreatedDate;
    private List<SkillRes> skillRes;
}
