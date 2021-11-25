package ua.goit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "companies")
public class Company implements BaseEntity<Long>{

    private static final long serialVersionUID = 7990325554609608506L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity_staff")
    private Long quantityStaff;

    public Company(String ... parameters){
        this.id = Long.valueOf(parameters[0]);
        this.name = parameters[1];
        this.quantityStaff = Long.valueOf(parameters[2]);
    }
}
