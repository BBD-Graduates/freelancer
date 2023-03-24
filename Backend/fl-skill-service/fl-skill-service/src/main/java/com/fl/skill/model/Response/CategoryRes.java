package com.fl.skill.model.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRes {
    private  int categoryId;
    private String categoryName;
    private String logoURl;
    private boolean IsDeleted;
    private String CreatedDate;
}
