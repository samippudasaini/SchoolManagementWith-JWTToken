package np.schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.Enum.Role;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class StaffDto {
    private Long staffId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Long phone;
    private String position;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date hireDate;
    private Double salary;
    private String userName;
    private String password;
    private Role role;
}
