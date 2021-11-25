package ua.goit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
    private LocalDate projectStart;

    @Column(name = "cost")
    private Integer cost;

    public Project(String ... parameters){
        this.id = Long.valueOf(parameters[0]);
        this.name = parameters[1];
        this.projectStart = LocalDate.parse(parameters[2]);
        this.cost = Integer.valueOf(parameters[3]);
    }
}
