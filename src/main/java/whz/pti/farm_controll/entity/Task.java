package whz.pti.farm_controll.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    TaskStatus taskStatus;
    @OneToOne
    User user;

}
