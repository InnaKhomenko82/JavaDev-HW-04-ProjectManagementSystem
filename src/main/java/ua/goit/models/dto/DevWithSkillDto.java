package ua.goit.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.models.BaseEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DevWithSkillDto implements BaseEntity<Long> {

    private static final long serialVersionUID = 587584735297206760L;

    private Long id;
    private String skillName;
    private String devName;

    @Override
    public String toString() {
        return "skillName=" + skillName + ", devName=" + devName;
    }
}
