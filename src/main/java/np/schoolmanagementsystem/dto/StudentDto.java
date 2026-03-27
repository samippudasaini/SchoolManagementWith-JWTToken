package np.schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.entity.Classroom;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class StudentDto {
    private  Long studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone_no;
    private String gender;
    private String address;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date enrollmentDate;
    private  String grade;
    private String userName;
    private String password;
    private  String parentContact;
    private Role role;
//    private  Classroom classroom;
    private Long classroomId;

}


