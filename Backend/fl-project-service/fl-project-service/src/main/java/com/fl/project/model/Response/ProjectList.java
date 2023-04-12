package com.fl.project.model.Response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class ProjectList {
    private List<ProjectRes> projects;
}
