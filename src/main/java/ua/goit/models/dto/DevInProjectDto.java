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
public class DevInProjectDto implements BaseEntity<Long> {

    private static final long serialVersionUID = 2849719235899865577L;

    private Long id;
    private String projectName;
    private String devName;

    @Override
    public String toString() {
        return "projectName=" + projectName + ", devName=" + devName;
    }
}
