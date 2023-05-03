package com.fl.skill.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSkills {

    private int userId;
    private int skillId;
    private String skillName;
    private int categoryId;
}
