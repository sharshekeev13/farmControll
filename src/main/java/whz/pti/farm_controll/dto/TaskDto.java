package whz.pti.farm_controll.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import whz.pti.farm_controll.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TaskStatus taskStatus;
    private String taskStatusName;
    private Long userId;
    private String userName;
    private Long locationId;
    private String locationName;
    private Set<Long> equipmentIds = new HashSet<>();
}
