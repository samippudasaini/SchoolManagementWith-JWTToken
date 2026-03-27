package np.schoolmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.*;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.dto.ClassroomDto;
import np.schoolmanagementsystem.dto.SubjectDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Teacher_table")
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "user_Name")
    private String userName;

    @Column(name = "password")
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "teacher_classrooms",
//            joinColumns = @JoinColumn(name = "teacher_id"),
//            inverseJoinColumns = @JoinColumn(name = "classroom_id")
//    )
//
//    private List<Classroom> grade;  // or classrooms
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "teacher_subjects",
//            joinColumns = @JoinColumn(name = "teacher_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id")
//    )
//    private List<Subject> subject;

    @Column(name = "grade")
    private String grade;

 @Column(name = "subject_name")
    private String subjectName;

}



