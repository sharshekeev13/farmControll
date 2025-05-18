package whz.pti.farm_controll.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="address")
    private String address;

    @Column(name="coordinates")
    private String coordinates;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Equipment> equipments = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}
