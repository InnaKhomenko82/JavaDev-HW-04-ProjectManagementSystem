package ua.goit.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.models.BaseEntity;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProjectDto implements BaseEntity<Long> {

    private static final long serialVersionUID = 3647320658144968472L;

    private Long id;
    private Timestamp start;
    private String name;
    private Integer quantityDevs;

    @Override
    public String toString() {
        DateTimeFormatter formatForDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "start=" + start.toLocalDateTime().toLocalDate().format(formatForDate) +
                ", name=" + name + ", quantityDevs=" + quantityDevs;
    }
}
