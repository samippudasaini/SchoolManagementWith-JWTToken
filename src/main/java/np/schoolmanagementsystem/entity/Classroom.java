package np.schoolmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Classroom_Table")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Long classroomId;

    @Column(name = "room_Number")
    private Long roomNo;

    @Column(name = "grade")
    private String grade;

    //       Relation or mapping between Classroom and Teacher
    @OneToMany(mappedBy = "classroom")
    private List<Teacher> teachers;


    public Classroom(Long classroomId, Long roomNo, String grade) {
        this.classroomId = classroomId;
        this.roomNo = roomNo;
        this.grade = grade;
    }
}
