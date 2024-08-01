package zhoma.practice_1.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDTO {

    private String FIO;
    private String turbine;
    private String car_model;
    private String phone_number;
}
