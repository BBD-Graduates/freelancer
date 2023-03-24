package com.fl.skill.model.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CategorySkillList {

//    private CategoryRes categoryRes;
private  int categoryId;
    private String categoryName;
    private String logoURl;
    private boolean IsDeleted;
    private String CreatedDate;
    private List<SkillRes> skillRes;
}
