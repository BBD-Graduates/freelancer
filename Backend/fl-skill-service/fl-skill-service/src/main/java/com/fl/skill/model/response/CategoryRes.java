package com.fl.skill.model.response;

import jakarta.ws.rs.DefaultValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRes {
    private  int categoryId;
    private String categoryName;
    @DefaultValue("")
    private String logoURl;
    private boolean isDeleted;
    private String createdDate;
    private List<SkillRes> skillRes;

}
