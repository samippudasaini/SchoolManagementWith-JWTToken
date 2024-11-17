package np.schoolmanagementsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Subject;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class TeacherDto {
    private Long teacherId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Long phone;
    private String subjectName;
    private String grade;
    private String userName;
    private String password;
    private Classroom classroom;
    private Subject subject;

}
