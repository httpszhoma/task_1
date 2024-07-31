package zhoma.practice_1.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {

    private String FIO;
    private String turbine;
    private String car_model;
    private String phone_number;
    private LocalDate acception_date;
    private LocalDate creation_date;
    private LocalDate update_date;
    private Integer visit_number;
}
