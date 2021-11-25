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

public class DevWithLevelDto implements BaseEntity<Long> {

    private static final long serialVersionUID = 1163492194194553295L;

    private Long id;
    private String level;
    private String devName;

    @Override
    public String toString() {
        return "level=" + level +", devName=" + devName;
    }
}
