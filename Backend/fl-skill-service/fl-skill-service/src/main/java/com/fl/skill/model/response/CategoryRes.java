package com.fl.skill.model.response;

import jakarta.ws.rs.DefaultValue;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRes {
    private  int categoryId;
    private String categoryName;
    @DefaultValue("")
    private String logoURl;
    private boolean isDeleted;
    private String createdDate;
    private int skillId;
    private String skillName;

}
