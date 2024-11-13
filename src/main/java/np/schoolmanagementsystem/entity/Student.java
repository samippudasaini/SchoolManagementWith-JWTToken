package np.schoolmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.Enum.Role;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="StudentsTable")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "studentId", unique = true, nullable = false,insertable = false,updatable = false)
    private Long studentId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    @Column( name= "email_address",unique=true, nullable=false, length=100)
    private String email;

    @Column(name="phone_number")
    private Long phone_no;

    @Column(name="gender")
    private String gender;

    @Column(name="address")
    private String address;

    @Column(name="enrollement_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date enrollmentDate;


    @Column(name="grade")
    private String grade;


    @Column(name="parent_contact")
    private Long parentContact;


    @Column(name="user_name")
    private  String userName;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//@ManyToMany
//@JoinTable(
//            name="StudentsTable",
//            joinColumns = @JoinColumn(name="studentId"),
//            inverseJoinColumns = @JoinColumn(name="id")
//
//    )

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    private Classroom classroom;
}