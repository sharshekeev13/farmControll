package whz.pti.farm_controll.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import whz.pti.farm_controll.enums.EquipmentStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDto {

    private Long id;
    private String equipmentName;
    private String model;
    private String manufacturer;
    private LocalDateTime installationDate;
    private EquipmentStatus equipmentStatus;
    private String equipmentStatusName;
    private Set<Long> taskId = new HashSet<>();
    private List<String> taskNames = new ArrayList<>();
    private Long locationId;
    private String locationName;
    private Set<Long> sparePartIds = new HashSet<>();
    private List<String> sparePartNames = new ArrayList<>();

}
