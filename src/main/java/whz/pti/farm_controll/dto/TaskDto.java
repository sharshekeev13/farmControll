package whz.pti.farm_controll.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {
    Long id;
    String name;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Long taskStatusId;
    Long userId;
}
