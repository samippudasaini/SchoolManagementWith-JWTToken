package np.schoolmanagementsystem.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import np.schoolmanagementsystem.Enum.Role;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@Table(name = "Staff_table")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Staff_id")
    private Long staffId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_Name")
    private String middleName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "email_Address", unique = true, nullable = false)
    private String email;

    @Column(name = "pnone_no")
    private Long phone;

    @Column(name = "position")
    private String position;

    @Column(name = "hire_Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date hireDate;

    @Column(name = "staff_Salary")
    private Double salary;

    @Column(name = "User_Name")
    private String userName;

    @Column(name = "Password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
