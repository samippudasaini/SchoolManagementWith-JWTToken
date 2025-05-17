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
    @Column(name = "subject_id")
    private String subjectId;


    @Column(name = "subject_Name")
    private String subjectName;

    @OneToOne(mappedBy="subjects")
    private Teacher teachers;
}
