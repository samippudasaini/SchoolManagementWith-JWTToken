package np.schoolmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Exam_Table")
public class Exam {
    @Id
    private Integer examId;
    private Integer subjectId;
    private Integer classroomId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date examDate;
    private String examType;
    private Integer fullMarks;
    private Integer passMark;
    @Lob
    @Column(name = "routine",columnDefinition = "LONGBLOB")
    private byte[] routine;
    private String routineMimeType;

    @Transient
    private byte[] base64Routine;
}
