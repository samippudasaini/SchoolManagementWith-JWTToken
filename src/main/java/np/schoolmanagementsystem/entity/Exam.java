package np.schoolmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    public Exam(Integer examId, Integer subjectId, Integer classroomId, Date examDate) {
        this.examId = examId;
        this.subjectId = subjectId;
        this.classroomId = classroomId;
        this.examDate = examDate;
    }

//    public Integer getExamId() {
//        return examId;
//    }
//
//    public Integer SubjectId() {
//        return subjectId;
//    }
//
//    public Integer getClassroomId() {
//        return classroomId;
//    }
//
//    public Date getExamDate() {
//        return examDate;
//    }
}
