package com.fl.skill.model.response;

import jakarta.ws.rs.DefaultValue;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorySkillsResponse {

    private int categoryId;
    private String categoryName;
    @DefaultValue("")
    private String logoURl;
    @Builder.Default
    List<SkillRes> skills =new ArrayList<>();
}
