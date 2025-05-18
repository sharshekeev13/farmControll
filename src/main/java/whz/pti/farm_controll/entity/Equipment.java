package whz.pti.farm_controll.entity;

//Добавить техпаспорт оборудования
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import whz.pti.farm_controll.enums.EquipmentStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="equipment_name")
    private String equipmentName;

    @Column(name="model")
    private String model;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="installationDate")
    private LocalDateTime installationDate;

    @Column(name="equipment_status")
    @Enumerated(EnumType.STRING)
    private EquipmentStatus equipmentStatus;

    @ManyToMany(mappedBy = "equipments")
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "equipment_spare_parts",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_part_id")
    )
    private Set<SparePart> spareParts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
