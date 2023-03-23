package com.fl.skill.model.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SkillRes {

    private int skillId;
    private String skillName;
    private String categoryId;
    private String createdDate;
}
