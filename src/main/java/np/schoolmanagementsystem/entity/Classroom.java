package np.schoolmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name="Classroom_Table")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="class_room_id",nullable=false)
   private Long id;

    @Column(name="room_Number")
   private Long room_No;

    @Column(name="grade")
    private String grade;

    //  Relation or mapping between Classroom and Student
    @OneToMany(mappedBy = "classroom",cascade = CascadeType.ALL)
    private List<Student> students ;
//    private List<Student> students = new ArrayList<>();
    //    Relation or mapping between Classroom and Teacher
    @OneToMany(mappedBy = "classroom",cascade = CascadeType.ALL)
    private List<Teacher> teachers;
//private List<Teacher> teachers = new ArrayList<>();

    public Classroom(Long id, Long room_No, String grade) {
        this.id = id;
        this.room_No = room_No;
        this.grade = grade;
    }

}
