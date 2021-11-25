package ua.goit.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.models.BaseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProjectDto implements BaseEntity<Long> {

    private static final long serialVersionUID = 3647320658144968472L;

    private Long id;
    private Date start;
    private String name;
    private Integer quantityDevs;

    @Override
    public String toString() {
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yyyy");
        return "start=" + formatForDate.format(start) +
                ", name=" + name + ", quantityDevs=" + quantityDevs;
    }
}
