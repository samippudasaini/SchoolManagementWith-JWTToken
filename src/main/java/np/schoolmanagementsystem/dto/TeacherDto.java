package np.schoolmanagementsystem.dto;


import lombok.*;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Subject;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TeacherDto {
    private Long teacherId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Long phone;
//    private String subjectName;
    private List<ClassroomDto> grade;

    private String userName;
    private String password;
    private List<SubjectDto> subject;
    private Role role;

//    private Long classroomId;
//    private String subjectId;



}
