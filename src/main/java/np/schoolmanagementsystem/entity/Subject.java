package np.schoolmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "Subject_Table")
public class Subject {

    @Id
//    @GeneratedValue(strategy =GenerationType.AUTO)
//    @Column(name = "subject_Id", unique = true, nullable = false)
//    private Long subjectId;
    @Column(name = "subject_id")
    private String subjectId;


    @Column(name = "subject_Name")
    private String subjectName;

//    @ManyToMany(mappedBy = "subjects")
//   @JoinTable(name="teacher")

//    featch type annotation

//    private List<Teacher> teachers;

//    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
//    private List<Teacher> teachers;

    @OneToOne()
    private Teacher teachers;
}
