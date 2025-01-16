package np.schoolmanagementsystem.dto;


import lombok.*;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Subject;

@AllArgsConstructor
@Data
@Getter
@Setter
@NoArgsConstructor

public class TeacherDto {
    private Long teacherId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Long phone;
//    private String subjectName;
    private String grade;
    private String userName;
    private String password;
    private Role role;
    private Long classroomId;
    private String subjectId;



}
