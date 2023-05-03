package com.fl.user.model.response;

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

    private int userId;
    @Builder.Default
    List<SkillResponse> skills =new ArrayList<>();
}
