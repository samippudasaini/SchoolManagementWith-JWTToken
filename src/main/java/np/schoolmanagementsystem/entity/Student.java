package np.schoolmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Enum.StudentStatus;
import np.schoolmanagementsystem.dto.ClassroomDto;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Students_Table")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId", unique = true)
    private Long studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Column(name = "email_address", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number")
    private String phone_no;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "enrollement_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date enrollmentDate;


    @Column(name = "grade")
    private String grade;


    @Column(name = "parent_contact")
    private String parentContact;


    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StudentStatus status;

    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "classroom_id", nullable = true)
    private Classroom classroom;


    @OneToMany(mappedBy = "student")
    private List<Fee> fee;

//    public Student(Long studentId, String firstName, String middleName, String lastName, String email,
//                   String phone_no, String gender, String address, Date enrollmentDate, String grade,
//                   String parentContact, String userName, String password, Role role,Classroom classroom) {
//        this.studentId = studentId;
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.email = email;
//        this.phone_no = phone_no;
//        this.gender = gender;
//        this.address = address;
//        this.enrollmentDate = enrollmentDate;
//        this.grade = grade;
//        this.parentContact = parentContact;
//        this.userName = userName;
//        this.password = password;
//        this.role = role;
//        this.classroom = classroom;
//    }
//

    public Student(Long studentId) {
        this.studentId = studentId;
    }



    public Student get()
    {
        return this;
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }



}
