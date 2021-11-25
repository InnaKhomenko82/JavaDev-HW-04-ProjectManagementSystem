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

public class SalaryByProjectNameDto implements BaseEntity<Long> {

    private static final long serialVersionUID = 7812484287119684041L;

    private Long id;
    private String name;
    private Integer totalSalary;

    @Override
    public String toString() {
        return "name=" + name + ", totalSalary=" + totalSalary;
    }
}
