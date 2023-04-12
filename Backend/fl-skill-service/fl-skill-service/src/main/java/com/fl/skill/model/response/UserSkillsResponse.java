package com.fl.skill.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSkillsResponse {
    private int UserId;
    @Builder.Default
    List<SkillRes> skills =new ArrayList<>();

}