package com.fl.skill.model.Response;

import com.fl.skill.model.Request.Skill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SkillList {
    private List<SkillRes> skills;

}
