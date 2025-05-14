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
public class EquipmentDto {

    Long id;
    String equipmentName;
    String model;
    String manufacturer;
    LocalDateTime installationDate;
    Long statusId;
    Long techPassportImageId;
}
