package np.schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class StudentDto {
    Long studentId;
    String firstName;
    String middleName;
    String lastName;
    String email;
    String phone_no;
    String gender;
    String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    Date enrollmentDate;
    String grade;

    String username;

    String password;

    Long parentContact;

}


