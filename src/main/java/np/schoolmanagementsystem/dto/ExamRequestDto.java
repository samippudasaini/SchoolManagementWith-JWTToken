package np.schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ExamRequestDto {
    @NotNull(message="ID is required")
    private Integer id;
    @NotNull(message = "Subject Id is required")
    private Integer subjectId;

    @JsonProperty("classroomId")
    @NotNull(message = "calssroom id is not null")
    private Integer classroomId;
    @NotNull(message="Exam date is not null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date examDate;
    @NotNull(message="Exam type is not null")
    private String examType;
    @NotNull(message="fullMark is not null")
    private Integer fullMarks;
    @NotNull(message="passMark is required")
    private Integer passMark;

//    public Object getExamId() {
//        return examId;
//    }
}
