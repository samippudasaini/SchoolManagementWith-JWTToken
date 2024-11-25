package np.schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import np.schoolmanagementsystem.Enum.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor

//@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class StudentDto {
    private  Long studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Long phone_no;
    private String gender;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date enrollmentDate;
    private  String grade;
    private String userName;
    private String password;
    private  Long parentContact;
    private Role role;
    private Long classroomId;

}


