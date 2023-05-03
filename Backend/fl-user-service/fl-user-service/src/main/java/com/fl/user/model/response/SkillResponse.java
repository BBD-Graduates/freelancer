package com.fl.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SkillResponse {

    private int skillId;
    private String skillName;
    private int categoryId;
    private boolean isDeleted;
    private String createdDate;
}
