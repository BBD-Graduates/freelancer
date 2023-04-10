package com.fl.skill.model.response;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillRes {
    private int skillId;
    private String skillName;
    private int categoryId;
    private boolean isDeleted;
    private String createdDate;
}
