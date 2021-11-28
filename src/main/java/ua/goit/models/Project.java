package ua.goit.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project implements BaseEntity<Long> {

    private static final long serialVersionUID = -8831832807197146954L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start")
    private Timestamp projectStart;

    @Column(name = "cost")
    private Integer cost;

    @SneakyThrows
    public Project(String ... parameters){
        this.id = Long.valueOf(parameters[0]);
        this.name = parameters[1];

        TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(utcTimeZone);

        DateTimeFormatter formatForDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String timestampAsString = parameters[2];
        Timestamp timestamp = Timestamp.valueOf(LocalDate.
                parse(timestampAsString, formatForDate).atStartOfDay());
        this.projectStart = timestamp;

        this.cost = Integer.valueOf(parameters[3]);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatForDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectStart=" +
                projectStart.toLocalDateTime().toLocalDate().format(formatForDate) +
                ", cost=" + cost +
                '}';
    }
}
