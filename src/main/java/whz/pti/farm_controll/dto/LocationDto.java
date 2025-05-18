package whz.pti.farm_controll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String coordinates;

    private List<Long> equipmentIds = new ArrayList<>();
    private List<String> equipmentNames = new ArrayList<>();

    private List<Long> taskIds = new ArrayList<>();
    private List<String> taskNames = new ArrayList<>();
}
