package np.schoolmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.Enum.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingTeacherDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Long phone;
    private String userName;
    private String password;
    private Role role;


    private List<String> grade;
    private List<String> subject;


}
