package com.fl.skill.model.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectSkills {
    private int projectId;
    private int skillId;
    private String skillName;
    private int categoryId;

}
