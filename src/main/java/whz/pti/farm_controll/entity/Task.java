package whz.pti.farm_controll.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import whz.pti.farm_controll.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="name")
    String name;
    @Column(name="description")
    String description;
    @Column(name="start_time")
    LocalDateTime startTime;
    @Column(name="end_time")
    LocalDateTime endTime;

    @Column(name="task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToMany
    @JoinTable(
            name = "task_equipment",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
