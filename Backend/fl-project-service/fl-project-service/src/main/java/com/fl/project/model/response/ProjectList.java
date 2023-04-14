package com.fl.project.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProjectList {
    private List<ProjectResponse> projects;
}
