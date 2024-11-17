package np.schoolmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@AllArgsConstructor
@NoArgsConstructor
//@Data
@Getter
@Setter
@Table(name = "Teacher_table")
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacherId", unique = true, nullable = false, insertable = false, updatable = false)
    private Long teacherId;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "middle_Name")
    private String middleName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "email_address", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "phone_Number")
    private Long phone;

    @Column(name = "teach_Subject_Name")
    private String subjectName;


    @Column(name = "teach_Grade")
    private String grade;

    @Column(name = "user_Name")
    private String userName;

    @Column(name = "password")
    private String password;


//    @ManyToMany
//    @JoinTable(
//            name = "Teacher_subject", // Name of the join table
//            joinColumns = @JoinColumn(name = "teacherId"),
//            inverseJoinColumns = @JoinColumn(name = "subjectId")
//    )
//    private List<Subject> subjects;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="subjectId")
    private Subject subjects;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classroomid")
    private Classroom classroom;

    public Teacher(Long teacherId, String firstName, String middleName, String lastName, String email
            , Long phone, String subjectName, String grade, String userName, String password,
                   Subject subjects, Classroom classroom) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.subjectName = subjectName;
        this.grade = grade;
        this.userName = userName;
        this.password = password;
        this.subjects = subjects;
        this.classroom = classroom;
    }
}



