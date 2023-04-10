package com.fl.skill.model.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserSkillsReq {

    private int userId;
    private int skillId;
}
