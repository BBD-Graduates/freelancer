package com.fl.skill.model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import jakarta.ws.rs.DefaultValue;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySkillList {

    private int categoryId;

    private String categoryName;

    @DefaultValue("")
    private String logoURl;

    private boolean IsDeleted;

    private String CreatedDate;

    private List<SkillRes> skillRes;
}
