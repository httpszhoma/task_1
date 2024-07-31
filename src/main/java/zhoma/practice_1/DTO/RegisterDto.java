package zhoma.practice_1.DTO;


import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String FIO;
    private String turbine;
    private String car_model;
    private String phone_number;
}
