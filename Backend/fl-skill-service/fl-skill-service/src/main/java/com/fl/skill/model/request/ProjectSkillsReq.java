package com.fl.skill.model.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor

public class ProjectSkillsReq {
    private int projectId;
    private int skillId;
}
