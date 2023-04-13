package com.fl.project.model.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSkillsResponse {
    private int projectId;
    @Builder.Default
    List<Skill> skills =new ArrayList();
}
